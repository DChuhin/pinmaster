locals {
  lbc_service_account_name = "aws-load-balancer-controller"
}

module "eks_al2023" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 20.0"

  cluster_name    = "pinmaster"
  cluster_version = "1.30"

  # Enable public access to connect with kubectl
  cluster_endpoint_public_access  = true

  # EKS Addons
  cluster_addons = {
    coredns                = {}
    eks-pod-identity-agent = {}
    kube-proxy             = {}
    vpc-cni                = {}
  }

  vpc_id     = var.vpc_id
  subnet_ids = var.vpc_private_subnets

  enable_cluster_creator_admin_permissions = true

  self_managed_node_groups = {
    pinmaster = {
      ami_type      = "AL2023_x86_64_STANDARD"
      instance_type = "t3a.small"

      min_size = 3
      max_size = 3
      desired_size = 3
    }
  }

  tags = {
    Terraform = "true"
  }
}

resource "aws_iam_policy" "alb_controller_policy" {
  name        = "AWSLoadBalancerControllerIAMPolicy"
  description = "ALB Controller Policy"

  # ALB policy based on https://docs.aws.amazon.com/eks/latest/userguide/lbc-helm.html
  policy = data.local_file.alb_controller_iam_policy.content

  tags = {
    Terraform = "true"
  }
}

resource "null_resource" "iam_service_account" {

  triggers = {
    cluster_arn = module.eks_al2023.cluster_arn
  }

  provisioner "local-exec" {
    command = <<EOF

    aws eks update-kubeconfig --name $CLUSTER_NAME

    eksctl create iamserviceaccount \
      --cluster=$CLUSTER_NAME \
      --namespace=kube-system \
      --name=$SA_NAME \
      --role-name AmazonEKSLoadBalancerControllerRole \
      --attach-policy-arn=$POLICY_ARN \
      --approve
    EOF

    environment = {
      CLUSTER_NAME = module.eks_al2023.cluster_name,
      POLICY_ARN = aws_iam_policy.alb_controller_policy.arn
      SA_NAME = local.lbc_service_account_name
    }
  }

  depends_on = [module.eks_al2023, aws_iam_policy.alb_controller_policy]
}

resource "null_resource" "load_balancer_controller" {

  triggers = {
    cluster_arn = module.eks_al2023.cluster_arn
  }

  provisioner "local-exec" {
    command = <<EOF
    helm install aws-load-balancer-controller eks/aws-load-balancer-controller \
      -n kube-system \
      --set clusterName=$CLUSTER_NAME \
      --set serviceAccount.create=false \
      --set serviceAccount.name=$SA_NAME
    EOF

    environment = {
      CLUSTER_NAME = module.eks_al2023.cluster_name,
      SA_NAME = local.lbc_service_account_name
    }
  }

  depends_on = [null_resource.iam_service_account]
}
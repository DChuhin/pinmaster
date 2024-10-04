module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = "pinmaster-vpc"
  cidr = "10.0.0.0/16"

  azs             = var.azs
  private_subnets = ["10.0.1.0/24", "10.0.2.0/24"]
  // Based on https://docs.aws.amazon.com/eks/latest/userguide/network-reqs.html
  public_subnet_tags = {
    "kubernetes.io/role/elb": 1,
    Terraform = "true"
  }
  public_subnets  = ["10.0.101.0/24", "10.0.102.0/24"]

  enable_nat_gateway = true

  tags = {
    Terraform = "true"
  }
}
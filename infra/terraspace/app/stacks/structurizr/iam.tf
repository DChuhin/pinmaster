/*# Base on https://registry.terraform.io/providers/hashicorp/aws/5.54.1/docs/resources/iam_role_policy_attachment
data "aws_iam_policy_document" "assume_role_policy" {
  version = "2012-10-17"
  statement {
    effect = "Allow"
    actions = ["sts:AssumeRoleWithWebIdentity"]
    principals {
      type        = "Federated"
      identifiers = [var.oidc_provider_arn]
    }
    condition {
      test     = "StringEquals"
      variable = "${var.oidc_provider}:sub"
      values   = ["system:serviceaccount:${var.k8s_namespace}:${local.sa_name}"]
    }
  }
}

data "aws_iam_policy_document" "structurizr_policy" {
  version = "2012-10-17"
  statement {
    effect  = "Allow"
    actions = [
      "s3:ListAllMyBuckets",
      "s3:GetBucketLocation",
      "s3:ListBucket",
      "s3:ListBucketVersions"
    ]
    resources = [
      "*"
    ]
  }
  statement {
    effect  = "Allow"
    actions = [
      "s3:*"
    ]
    resources = [
      module.s3_bucket.s3_bucket_arn,
      "${module.s3_bucket.s3_bucket_arn}/*"
    ]
  }
}

resource "aws_iam_role" "role" {
  name                 = local.app_name
  assume_role_policy = data.aws_iam_policy_document.assume_role_policy.json
  tags = {
    Terraform = "true"
  }
}

resource "aws_iam_policy" "policy" {
  name   = local.app_name
  policy = data.aws_iam_policy_document.structurizr_policy.json
  tags = {
    Terraform = "true"
  }
}

resource "aws_iam_role_policy_attachment" "role_policy_attachments" {
  policy_arn = aws_iam_policy.policy.arn
  role       = aws_iam_role.role.name
}*/
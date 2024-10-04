locals {
  app_name = "structurizr"
  sa_name = local.app_name
}

module "s3_bucket" {
  source = "terraform-aws-modules/s3-bucket/aws"

  bucket = "${local.app_name}-${var.aws_account_id}"
  acl    = "private"

  control_object_ownership = true
  object_ownership         = "ObjectWriter"

  versioning = {
    enabled = false
  }

  tags = {
    Terraform = "true"
  }
}

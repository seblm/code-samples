terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }

  required_version = ">= 0.14.9"
}

provider "aws" {
  profile = "default"
  region  = "eu-west-3"
}

resource "aws_s3_bucket" "internal-metrics" {
  bucket = "internal-metrics-bucket1"
  acl    = "private"

  tags = {
    Name = "InternalMetricsBucket"
  }
}

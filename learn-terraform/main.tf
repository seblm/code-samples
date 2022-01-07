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

resource "aws_lambda_function" "internal-metrics-to-elastic" {
  filename = "lambda/index.js"
  description = "An Amazon S3 trigger that retrieves metadata for the object that has been updated."
  package_type = "Zip"
  runtime = "nodejs12.x"
  memory_size = 128
  handler = "index.handler"
  source_code_hash = "X4VCueag0xLsvEmTQMu3UwC3TqdglStobyWkfsb3IMg="
  tags = {
    "lambda-console:blueprint" = "s3-get-object"
  }
  role = "arn:aws:iam::169700443223:role/service-role/internal-metrics-to-elastic-role"
  function_name = "internal-metrics-to-elastic"
}

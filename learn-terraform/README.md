A sandbox to discover terraform on AWS.

### Initialization

```shell
brew tap hashicorp/tap
brew install hashicorp/tap/terraform
brew install awscli
# create access key: https://console.aws.amazon.com/iam/home?#/security_credentials
aws configure
terraform init
terraform fmt
terraform validate
terraform plan
terraform apply
terraform show
terraform state list
```

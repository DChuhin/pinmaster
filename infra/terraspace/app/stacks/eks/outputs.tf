output "cluster_arn" {
  value = module.eks_al2023.cluster_arn
}

output "oidc_provider" {
  value = module.eks_al2023.oidc_provider
}

output "oidc_provider_arn" {
  value = module.eks_al2023.oidc_provider_arn
}
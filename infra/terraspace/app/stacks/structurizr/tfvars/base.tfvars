aws_account_id    = "<%= expansion(':ACCOUNT') %>"
k8s_namespace     = "default"
cluster_arn       = <%= output('eks.cluster_arn') %>
oidc_provider     = <%= output('eks.oidc_provider') %>
oidc_provider_arn = <%= output('eks.oidc_provider_arn') %>

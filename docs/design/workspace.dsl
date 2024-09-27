workspace "PinMaster" {

    !adrs adr
    !docs techdesign
    #!identifiers hierarchical

    model {
        properties {
            "structurizr.groupSeparator" "/"
        }

        user = person "User"

        device = element "Device" "" "Arduino Platform" {
            tags "device"
        }
        mobileApp = element "Mobile App" {
            tags "mobileApp"
        }
        pinmaster = softwareSystem "PinMaster" {

            appService = container "Application Service"
            appStorage = container "Application Storage" {
                tags "Database"
            }
            deviceService = container "Device Service"
            deviceStorage = container "Device Storage" {
                tags "Database"
            }
            certsStorage = container "Certificates Storage" {
                tags "Database"
            }
        }

        user -> appService "Web UI"
        user -> deviceService "Web UI"
        user -> mobileApp "Uses"
        user -> device

        mobileApp -> deviceService "Mobile SDK"

        deviceService -> device "Embedded SDK"
        appService -> appStorage
        deviceService -> deviceStorage
        deviceService -> certsStorage

        #-------- Deployment ------------

        pinMasterDeployment = deploymentEnvironment "PinMaster Deployment" {
            deploymentNode "AWS" {
                tags "Amazon Web Services - Organizations"

                certificatesBucket = infrastructureNode "Certificates Bucket" {
                    tags "Amazon Web Services - Simple Storage Service Bucket"
                }

                alb = infrastructureNode "ALB" {
                    tags "Amazon Web Services - Elastic Load Balancing Application Load Balancer"
                }

                route53 = infrastructureNode "Route53 Domain" {
                    tags "Amazon Web Services - Elastic Load Balancing Application Load Balancer"
                }

                acm = infrastructureNode "ACM Certificate" {
                    tags "Amazon Web Services - Elastic Load Balancing Application Load Balancer"
                }

                cloudWatch = infrastructureNode "CloudWatch" {
                    tags "Amazon Web Services - CloudWatch"
                }

                kubernetes = deploymentNode "Kubernetes Cluster" {
                    tags "Amazon Web Services - EKS Cloud"

                    albController = infrastructureNode "ALB Controller" "" "Deployment" {
                        tags "Kubernetes - ep"
                    }
                    deviceDeployment = infrastructureNode "Device Service" "" "Deployment" {
                        tags "Kubernetes - pod"
                    }
                    appDeployment = infrastructureNode "Application Service" "" "Deployment" {
                        tags "Kubernetes - pod"
                    }
                    mongoDb = infrastructureNode "MongoDb" "" "Stateful Set" {
                        tags "Kubernetes - pod"
                    }
                    prometheus = infrastructureNode "Prometheus" "" "Stateful Set" {
                        tags "Kubernetes - pod"
                    }
                    grafana = infrastructureNode "Grafana" "" "Deployment" {
                        tags "Kubernetes - pod"
                    }
                }
            }

            deploymentNode "Google" {
                tags "Google Cloud Platform - Compute Engine"

                googleSSO = infrastructureNode "Google SSO" {
                    tags "Google Cloud Platform - Cloud IAM"
                }
            }
        }

        alb -> googleSSO
        alb -> albController
        albController -> deviceDeployment
        albController -> appDeployment
        albController -> route53
        albController -> acm
        deviceDeployment -> mongoDb
        deviceDeployment -> certificatesBucket
        appDeployment -> mongoDb
        kubernetes -> cloudwatch
        grafana -> prometheus
    }

    views {
        systemContext pinmaster "Scope" {
            include *
            #autolayout lr
            exclude "user -> device"
        }

        container pinmaster "BuildingBlocksView" {
            include *
            #autolayout lr
            exclude "user -> device"
        }

        dynamic pinmaster "CreateApp" {
            title "Create new Application"
            user -> appService "Create new application request"
            appService -> user "Credentials for newly created application"
            user -> mobileApp "Embed credentials into Mobile App"
            mobileApp -> deviceService "Init Mobile SDK with credentials. Use Mobile SDK to manage application devices"
            #autolayout lr
        }

        dynamic pinmaster "RegisterDevice" {
            title "Register Device"
            user -> deviceService "Register device request for specified application"
            deviceService -> user "Certificate for newly registered device"
            user -> device "Upload certificate to device"
            device -> deviceService "Init Embedded SDK with certificate. Use Embedded SDK to keep state up to date"
            #autolayout lr
        }

        dynamic pinmaster "UpdateDeviceState" {
            title "Update Device State with Mobile App"
            user -> mobileApp "Change device state"
            mobileApp -> deviceService "Upload changed state via Mobile SDK"
            deviceService -> deviceStorage "Store updated state"
            deviceService -> device "Send update state to device. Device will get updte event emmitted by Embedded SDK"
            #autolayout lr
        }

        deployment pinmaster "PinMasterDeployment" "PinMasterDeployment" {
            include *
            #autoLayout lr
        }

        styles {
            element "mobileApp" {
                shape MobileDevicePortrait
                width 300
            }
            element "webUI" {
                shape WebBrowser
                width 300
            }
            element "Database" {
                shape Cylinder
                width 300
            }
        }

        themes default "https://static.structurizr.com/themes/microsoft-azure-2021.01.26/theme.json"
        theme https://static.structurizr.com/themes/amazon-web-services-2023.01.31/theme.json
        theme https://static.structurizr.com/themes/google-cloud-platform-v1.5/theme.json
        theme https://static.structurizr.com/themes/kubernetes-v0.3/theme.json
    }

    configuration {
        scope softwaresystem
    }

}
# PinMaster Design Document

## Introduction and Goals

This document outlines the architecture for PinMaster software system.

### Requirements Overview

The overall goal PinMaster is to speed up prototyping of IoT solutions, improve development experience. Requirements are
described in details in SRS document.

### Quality Goals

The primary goals for PinMaster are:

| Goal                     | Description                                                                                                                                          |
|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Usability**            | Ease of adoption for users. Development experience of users.                                                                                         |
| **Extensibility**        | Ease of adding new functionality.                                                                                                                    |
| **Maintainability**      | Ease of maintaining, managing dependencies and evolving the system.                                                                                  |
| **Performance**          | Real-time state propagation. Horizontal scalability.                                                                                                 |
| **Security**             | Compliance with industry security standards on network, infrastructure, application layers. Authentication, encryption, fine-grained access control. |
| **Reliability**          | 99% availability SLA. 99.999% Durability SLA. Fault tolerance.                                                                                       |
| **Ease of construction** | Due to time constraints, it must be relatively easy to construct the system                                                                          |

### Stakeholders

| Who                | Concern                                                                                                                                                                                                                                                                                                                                                             |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Daniil Chukhin** | **Ease of construction**: It should not take a lot of effort to build system. <br/> **Extensibility**: It should be easy to add new function when we will get enough information<br/> **Maintainability**: Development experience for PinMaster development team<br/> **Security**: The system must be secure-by-design and comply with industry security standards |
| **User**           | **Usability**: System onboarding must be straightforward. Learning curve must be minimal<br/> **Performance**: Device and state management must be real-time<br/> **Reliability**: System must be available and should not lose data                                                                                                                                |

---

## Constraints

| Constraint                   | Description                                                                                                                                     |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| **Operational cost**         | System must generate minimal bills in cloud.                                                                                                    |
| **Incremental Evolvability** | The system must be built incrementally, ensuring its complexity aligns with current needs, avoiding overengineering or unnecessary constraints. |
| **Arduino Platform**         | System functionality must be compatible with Arduino platform devices.                                                                          |

## Context

![](embed:Scope)

| Neighbor       | Description                                                               |
|----------------|---------------------------------------------------------------------------|
| **User**       | Registers devices in the system. Manages device state.                    |
| **Device**     | Consumes device state and applies it.                                     |
| **Mobile App** | Mobile application using PinMaster Mobile SDK and manage state of devices |

## Solution Strategy

### Introduction to strategy

| Quality goal                          | Solution approach                                                                                                                                                                             |
|---------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Usability**                         | 1. Straightforward device registration. <br/> 2. JSON as state format <br/> 3. Google SSO for authentication                                                                                  |
| **Extensibility and Maintainability** | 1. Use microservice architecture style <br/> 2. Use Hexagonal architecture <br/> 3. Use DDD                                                                                                   |
| **Performance**                       | 1. Use GRPC for data real-time streaming to devices. <br/> 2. Enforce horizontal scalability by using kubernetes load balancing and stateless service implementation                          |
| **Security**                          | 1. mTLS for device authentication <br/> 2. SSO to authenticate users <br/> 3. Implement infrastructure access control <br/> 4. Enforce encryption at rest                                     |
| **Reliability**                       | 1. HA by redundancy for services. <br/> 2. Standby replica for database <br/> 3. Ack mechanism for state delivering                                                                           |
| **Ease of construction**              | 1. Using Spring Boot for application development <br/> 2. Using terraspace for infrastructure management <br/> 3. Using React for frontend <br/> 4. Using React-native for mobile development |

## Building Block View

![](embed:BuildingBlocksView)

### Contained blocks

| Block                    | Description                                              |
|--------------------------|----------------------------------------------------------|
| **Application Service**  | Service to manage user created applications.             |
| **Application Storage**  | Stores created applications and application credentials. |
| **Device Service**       | Service to manage user created devices.                  |
| **Device Storage**       | Stores created devices.                                  |
| **Certificates Storage** | Stores created devices certificates.                     |
| **Device**               | Arduino device, using PinMaster Arduino SDK.             |

### Interfaces

| Interface        | Description                                          |
|------------------|------------------------------------------------------|
| **Web UI**       | REST API for web-backend communication               |
| **Mobile SDK**   | Client for REST API for mobile-backend communication |
| **Embedded SDK** | Client for GRPC API for device-backend communication |

## Runtime View

### Application Creation

![](embed:CreateApp)

### Device Registration

![](embed:RegisterDevice)

### Device State Management

![](embed:UpdateDeviceState)

## Deployment View

![](embed:PinMasterDeployment)

| Node / Artifact         | Description                                                                                                |
|-------------------------|------------------------------------------------------------------------------------------------------------|
| **Google SSO**          | Google SSO as authentication method on ALB enforces Google SSO authentication for for accessing resources. |
| **ALB**                 | ALB with public IP to expose services to public.                                                           |
| **ALB Controller**      | EKS Load Balancer Controller to use ingress for exposing services.                                         | |
| **Route53**             | Use route53 domain to access application.                                                                  | |
| **ACM**                 | Use ACM TLS certificate to enforce TLS usage on ALB.                                                       | |
| **Application Service** | Service to manage applications.                                                                            | |
| **Device Service**      | Service to manage devices.                                                                                 | |
| **Certificates Bucket** | Bucket to store device certificates.                                                                       | |
| **MongoDb**             | MongoDB instance to store data.                                                                            | |
| **Prometheus**          | Prometheus instance for storing monitoring data.                                                           | |
| **Grafana**             | Grafana to access prometheus data and provide monitoring insights.                                         | |
| **CloudWatch**          | Use AWS CloudWatch for centralized logging.                                                                | |

## Cross-cutting Concepts

### Security

#### Infrastructure Access

- Infrastructure Access is managed by AWS IAM
- AWS IAM roles are managed with terraspace and can be updated with code review only

#### Public Access

- Public access for infrastructure must be prevented.
- Public access public interfaces are exposed only via authentication.

#### Encryption

- **Encryption at rest** must be enforced for all interfaces
- **Encryption in transfer** must be enforced for all public interfaces

### Backend Services Architecture style

- **Domain Driven Design**: Business domain is decomposed based on DDD principles to control system coupling and
  complexity.
- **Hexagonal Architecture**: Hexagonal Architecture enforces that business layer will not have infrastructure
  dependencies

### Testing approach

- **Backend:**
    - Unit test coverage
    - Testcontainers integration tests coverage is required
    - Tests must be run on CI
- **Mobile SDK**
    - Unit tests coverage
    - Create sample application with manual testing
- **Arduino SDK**
    - Setup sample device, test manually

### Monitoring and Observability approach

- **Centralized Logging:**
    - Use AWS CloudWatch for centralized logging for all system components
- **Metrics:**
    - All server components must be monitored
    - All client component must be monitor in their server usage
- **Health Checks**:
    - Self-hosted infrastructure must be monitored with health checks.
    - Cloud watch and grafana alerts can be considered.
- **Alerting:**
    - There must be alerts on any component failure.

### Documentation approach

- **Document decisions and component**:
    - All component and significant decision must be documented in corresponding way.
- **Documentation as a code**:
    - documentation is written in markdown format and stored in git. Baseline approach: readme file.

## Architectural Decisions

Every engineering team member must be familiar with architecture decisions.

ADRs are listed on the [Decisions](/workspace/decisions) page

## Risks and Technical Debt

### Risks

- **Arduino Platform Limitations**: Arduino platform has limited set of implemented libraries, protocols.
- **Arduino Hardware Limitations**: Arduino based devices may be limited in resources that can cause issues for running
  PinMaster Arduino SDK.
- **Time limitation**: Lack of resources in provided time constraints

## Glossary and Links

### Glossary

| Term                                        | Definition                                                                                                                                    |
|---------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| **IoT (Internet of Things)**                | A network of physical devices embedded with sensors, software, and other technologies to connect and exchange data.                           |
| **SaaS (Software as a Service)**            | A software distribution model in which applications are hosted by a service provider and made available over the internet.                    |
| **API (Application Programming Interface)** | A set of functions that allow applications to access data or interact with external software components, operating systems, or microservices. |
| **SSO (Single Sign-On)**                    | A user authentication process that allows access to multiple applications with a single set of login credentials.                             |
| **RBAC (Role-Based Access Control)**        | A method of restricting system access to authorized users based on their roles.                                                               |
| **SLA (Service Level Agreement)**           | A contract that defines quality characteristic of a service.                                                                                  |
| **TLS (Transport Layer Security)**          | A cryptographic protocol designed to provide communications security over a computer network.                                                 |
| **mTLS (Mutual TLS)**                       | A version of TLS where both the client and server authenticate each other through certificates.                                               |
| **Hexagonal Architecture**                  | A software design pattern that decouples the business logic from external factors like databases and UI via ports and adapters.               |
| **DDD (Domain-Driven Design)**              | A design approach that focuses on modeling software based on the domain of the problem being solved.                                          |

### Links

- **[Google OpenId Connect](https://developers.google.com/identity/openid-connect/openid-connect)**
- **[AWS Load Balancer Controller](https://docs.aws.amazon.com/eks/latest/userguide/aws-load-balancer-controller.html)**
- **[ACM Validation](https://docs.aws.amazon.com/acm/latest/userguide/dns-validation.html)**


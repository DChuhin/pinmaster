# 2. Use AWS as Cloud provider

Date: 2024-09-20

## Status

Accepted

## Context

We need to define deployment environment for backend.

### Quality Attributes

- **Ease of adoption**: How easy is to create initial setup to start development?
- **Maintainability**: How easy it will be to maintain and onboard new services.
- **Security**: Ability to implement fine-grained access management.

### Considered Options

#### AWS

| Characteristic       | Rating (★) | Notes                                                                     |
|----------------------|------------|---------------------------------------------------------------------------|
| **Ease of adoption** | ★★★☆☆      | AWS has higher learning curve comparing to other clouds.                  |
| **Maintainability**  | ★★★☆☆      | AWS usually has higher boilerplate to setup new services, infrastructure. |
| **Security**         | ★★★★★      | AWS has very fine-grained and flexible IAM.                               |

#### GCP

| Characteristic       | Rating (★) | Notes                                                     |
|----------------------|------------|-----------------------------------------------------------|
| **Ease of adoption** | ★★★★★      | GCP has low learning curve to start development.          |
| **Maintainability**  | ★★★★☆      | GCP resources are more straightforward and usable.        |
| **Security**         | ★★★★☆      | GCP has fine-grained IAM though not such flexible as AWS. |

#### Azure

| Characteristic       | Rating (★) | Notes                                      |
|----------------------|------------|--------------------------------------------|
| **Ease of adoption** | ★★★☆☆      | Requires additional learning.              |
| **Maintainability**  | ★★★★☆      | Well-structured resource hierarchy system. |
| **Security**         | ★★★★☆      | Fine-grained access control system.        |

## Decision

We will use AWS as cloud provider to host backend infrastructure.

## Consequences

- **Ease of adoption**: AWS has higher entry level and higher effort required to setup account
- **Maintainability**: It will require some effort to work with AWS services during integrations.
- **Security**: Great fine-grained access controlled to infrastructure can be enforced

# 7. Use Kubernetes as container orchestrator

Date: 2024-09-20

## Status

Accepted

## Context

We need to define deployment option for backend components.

### Quality Attributes

- **Ease of adoption**: How easy is to learn and start using technology?
- **Extensibility**: How easy is to add new features?
- **Maintainability**: How easy is to maintain solution?

### Considered options

#### Kubernetes

| Characteristic       | Rating (★) | Notes                                                                                             |
|----------------------|------------|---------------------------------------------------------------------------------------------------|
| **Ease of adoption** | ★★★★☆      | Kubernetes requires learning, though has comprehensive documentation and great community support. |
| **Extensibility**    | ★★★★☆      | Proven standard to deploy different types of infrastructure.                                      |
| **Maintainability**  | ★★★★☆      | Declarative manifests and helm improve maintainability and enforce IaC.                           |

#### AWS ECS

| Characteristic       | Rating (★) | Notes                                                               |
|----------------------|------------|---------------------------------------------------------------------|
| **Ease of adoption** | ★★★☆☆      | Less community support and lower documentation quality.             |
| **Extensibility**    | ★★☆☆☆      | Has more limitations.                                               |
| **Maintainability**  | ★★★☆☆      | Requires to build custom CI/CD to create tasks and update services. |

#### AWS Elastic Beanstalk

| Characteristic       | Rating (★) | Notes                                                            |
|----------------------|------------|------------------------------------------------------------------|
| **Ease of adoption** | ★★★★☆      | Managed by AWS. Reduced operational overhead                     |
| **Extensibility**    | ★★☆☆☆      | Focused on running simple applications.                          |
| **Maintainability**  | ★★★★☆      | Less operational overheads leads to more simple CI/CD processes. |

## Decision

We will use kubernetes for containerized applications.

## Consequences

- **Ease of adoption**: 
  - No extra knowledge required as we already have required kubernetes experience
  - Load balancing out-of-the box
  - Service discovery out-of-the box
  - Externalized configuration out-of-the box
  - Self-healing out-of-the-box
- **Extensibility**: Kubernetes is well supported by community with rich set of out-of-the box helm chart and
  installation guides
- **Maintainability**: Kubernetes declarative manifests will be stored in git.

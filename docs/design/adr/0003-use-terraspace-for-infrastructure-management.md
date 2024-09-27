# 3. Use Terraspace for infrastructure management

Date: 2024-09-20

## Status

Accepted

## Context

We need standard approach to provision and manage infrastructure in cloud.

### Quality Attributes

- **Extensibility**: How easy is to add new infrastructure and meet newly appearing requirements?
- **Maintainability**: How easy is to manage infrastructure?

### Considered options

#### Terraform

| Characteristic      | Rating (★) | Notes                                      |
|---------------------|------------|--------------------------------------------|
| **Extensibility**   | ★★★★★      | Flexibility of custom terraform structure. |
| **Maintainability** | ★★★★☆      | Depends on chosen management approach.     |

#### Terraspace

| Characteristic      | Rating (★) | Notes                                              |
|---------------------|------------|----------------------------------------------------|
| **Extensibility**   | ★★★★☆      | Requires using required structure.                 |
| **Maintainability** | ★★★★★      | Out-of-the box env diff and dependency management. |

## Decision

Terraspace will be used. Idiomatic project structure of terraspace will be used.

## Consequences

- **Extensibility**:
    - Modularity is enforced
- **Maintainability**:
    - IaC is enforced
    - Environment diff management out-of-the box
    - Terraspace adds limitation to project structure
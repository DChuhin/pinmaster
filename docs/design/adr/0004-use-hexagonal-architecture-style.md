# 4. Use Hexagonal Architecture Style

Date: 2024-09-20

## Status

Accepted

## Context

We need to define common architecture style for services.

### Quality Attributes

- **Ease of adoption**: How easy is to learn and start using approach?
- **Extensibility**: How easy is to add new features?
- **Maintainability**: How easy is to manage and enforce design constraints?

### Considered Options

#### Clean Architecture

| Characteristic       | Rating (★) | Notes                                                                              |
|----------------------|------------|------------------------------------------------------------------------------------|
| **Ease of adoption** | ★★★★☆      | Well-known easy to adopt approach.                                                 |
| **Extensibility**    | ★★★★☆      | Extensible by avoiding coupling and splitting system into components based on DDD. |
| **Maintainability**  | ★★★★☆      | Code can be split into package with predefined cross-package dependency rules.     |

#### Hexagonal Architecture

| Characteristic       | Rating (★) | Notes                                                                                         |
|----------------------|------------|-----------------------------------------------------------------------------------------------|
| **Ease of adoption** | ★★★☆☆      | More formal rules to follow will require more learning                                        |
| **Extensibility**    | ★★★★☆      | Extensible by avoiding coupling and splitting system into components (hexagons) based on DDD. |
| **Maintainability**  | ★★★★★      | More formal rules for packages make it more easily controllable with architecture tests.      |

## Decision

Heaxagonal Architecture will be used. Each module will have own hexagon. Modules are created based on DDD.

## Consequences

- **Ease of adoption**:
    - Some effort on learning is required.
- **Extensibility**:
    - Modularity based on DDD is enforced.
    - Some boilerplate and overhead introduced by hexagonal architecture
- **Maintainability**:
    - Constraints are enforced with architecture tests
    - All services follow unified architecture standard

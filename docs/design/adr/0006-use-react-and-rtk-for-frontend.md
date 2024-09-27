# 6. Use React and RTK for frontend

Date: 2024-09-20

## Status

Accepted

## Context

The issue motivating this decision, and any context that influences or constrains the decision.

### Quality Attributes

- **Ease of adoption**: How easy is to learn and start using technology?
- **Extensibility**: How easy is to add new features?
- **Maintainability**: How easy is to maintain solution?

### Considered options

#### ReactJs

| Characteristic       | Rating (★) | Notes                                                            |
|----------------------|------------|------------------------------------------------------------------|
| **Ease of adoption** | ★★★★★      | Easy-to-go lib with straightforward comprehensive documentation. |
| **Extensibility**    | ★★★★☆      | Well-scalable component oriented approach.                       |
| **Maintainability**  | ★★★★☆      | Best-practices of structuring project out-of-the bo.             |

#### Angular

| Characteristic       | Rating (★) | Notes                                              |
|----------------------|------------|----------------------------------------------------|
| **Ease of adoption** | ★★☆☆☆      | High learning curve.                               |
| **Extensibility**    | ★★★☆☆      | More coupled to framework ecosystem.               |
| **Maintainability**  | ★★★★☆      | Set of practices to structure, develop, test apps. |

#### VueJs

| Characteristic       | Rating (★) | Notes                                      |
|----------------------|------------|--------------------------------------------|
| **Ease of adoption** | ★★★☆☆      | Requires learning new framework.           |
| **Extensibility**    | ★★★★☆      | Well-scalable component oriented approach. |
| **Maintainability**  | ★★★☆☆      | Less community support.                    |

## Decision

React with RTK and typescript will be used. Default project structure by official RTK vite template must be used.

## Consequences

- **Ease of adoption**: No extra knowledge required. Comprehensive concise react and RTK documentation is in place.
- **Extensibility**: Well-scalable component oriented approach.
- **Maintainability**: Great community support and set of best-practices

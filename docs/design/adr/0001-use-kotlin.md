# 1. Use Kotlin and Spring Boot as backend framework

Date: 2024-09-20

## Status

Accepted

## Context

We need to decide on backend implementation stack which will meet our quality goals: extensibility, maintainability,
performance, ease of adoption.

### Quality Attributes

- **Ease of adoption**: How easy is to adopt stack. Do we have required knowledge and people?
- **Maintainability**: How easy it will be to maintain and extend system with new functionality.
- **Cost of construction**: How long will it take to build solution? How much components needs to be constructed from
  scratch and how much we can get out-of-the box?

### Considered Options

#### Golang

| Characteristic           | Rating (★) | Notes                                                                                                                                                    |
|--------------------------|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Ease of adoption**     | ★★★★☆      | Go is relatively easy language with low learning curve.                                                                                                  |
| **Maintainability**      | ★★★☆☆      | Go has pros nad cons for maintainability. Functional style and concurrency implementation improves it, but OOP implementation, pointers produce overhead |
| **Cost of construction** | ★★☆☆☆      | Go does not offer a lot of out-of-the box solutions. There is no comprehensive framework like Spring Boot.                                               |

#### Java Spring Boot

| Characteristic           | Rating (★) | Notes                                                                                                       |
|--------------------------|------------|-------------------------------------------------------------------------------------------------------------|
| **Ease of adoption**     | ★★★★☆      | Though Spring Boot learning curve is high, we already have required knowledge and experience.               |
| **Maintainability**      | ★★★★☆      | Java and Spring Boot are targeted for enterprise scale project with modularity and maintainability in mind. |
| **Cost of construction** | ★★★★☆      | Framework with the richest set of integrations and out-of-the-box functionality.                            |

#### Kotlin Spring Boot

| Characteristic           | Rating (★) | Notes                                                                            |
|--------------------------|------------|----------------------------------------------------------------------------------|
| **Ease of adoption**     | ★★★☆☆      | Extra knowledge in transition from java is required.                             |
| **Maintainability**      | ★★★★★      | Kotlin features make code more concise and readable.                             |
| **Cost of construction** | ★★★★☆      | Framework with the richest set of integrations and out-of-the-box functionality. |

#### NodeJs Express

| Characteristic           | Rating (★) | Notes                                                               |
|--------------------------|------------|---------------------------------------------------------------------|
| **Ease of adoption**     | ★★★☆☆      | Relatively low learning curve, but some learning is still required. |
| **Maintainability**      | ★★★★☆      | Typescript enables more maintainable code.                          |
| **Cost of construction** | ★★★★☆      | Great community support can speed up development.                   |

## Decision

Kotlin with Spring Boot will be used to implement backend.

## Consequences

- **Ease of adoption**: We already have knowledge and experience in Spring Boot. Though we will need to lean Kotlin.
- **Maintainability**: Concise and readable enforced by standards.
- **Cost of construction**: Rich set of Spring Boot integrations will speed up development and reduce construction
  costs.

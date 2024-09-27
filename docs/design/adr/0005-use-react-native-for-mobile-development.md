# 5. Use React-native for mobile development

Date: 2024-09-20

## Status

Accepted

## Context

As we are going to have mobile application, development platform must be defined

### Quality Attributes

- **Ease of adoption**: How easy is to learn and start using technology?
- **Extensibility**: How easy is to add new features?
- **Maintainability**: How easy is to maintain solution?
- **Cost of construction**: How time-consuming is to build solution?

### Considered options

#### Native mobile development

| Characteristic           | Rating (★) | Notes                                                                        |
|--------------------------|------------|------------------------------------------------------------------------------|
| **Ease of adoption**     | ★★☆☆☆      | Requires learning of mobile SDKs from scratch.                               |
| **Extensibility**        | ★★★★★      | Full access to mobile OS APIs. Flexibility of adjustments for each platform. |
| **Maintainability**      | ★★★☆☆      | Requires to maintain 2 projects.                                             |
| **Cost of construction** | ★★☆☆☆      | Higher cost of construction due to double work .                             |

#### Flutter

| Characteristic           | Rating (★) | Notes                                  |
|--------------------------|------------|----------------------------------------|
| **Ease of adoption**     | ★★★☆☆      | Requires learning of Flutter.          |
| **Extensibility**        | ★★★☆☆      | Limited access to native capabilities. |
| **Maintainability**      | ★★★★☆      | Single implementation.                 |
| **Cost of construction** | ★★★★☆      | Single implementation.                 |

#### React Native

| Characteristic           | Rating (★) | Notes                                  |
|--------------------------|------------|----------------------------------------|
| **Ease of adoption**     | ★★★★☆      | More friendly for React devs.          |
| **Extensibility**        | ★★★☆☆      | Limited access to native capabilities. |
| **Maintainability**      | ★★★★☆      | Single implementation.                 |
| **Cost of construction** | ★★★★☆      | Single implementation.                 |


## Decision

React native will be used to build single cross-platform application

## Consequences

- **Ease of adoption**: Lower Learning curve for React developers
- **Extensibility**: Limitations of React-native
- **Maintainability**: Single implementation on IOS, Android
- **Cost of construction**: Single implementation on IOS, Android

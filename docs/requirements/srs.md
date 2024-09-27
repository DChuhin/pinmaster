# PinMaster Software Requirements Specification

## For PinMaster IoT Prototyping Platform

Version 0.1  
Prepared by Daniil Chukhin  
PinMaster  
2024-09-18

---

## Table of Contents

1. [Revision History](#revision-history)
2. [Introduction](#1-introduction)
    1. [Document Purpose](#11-document-purpose)
    2. [Product Scope](#12-product-scope)
    3. [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
3. [Product Overview](#2-product-overview)
    1. [Product Perspective](#21-product-perspective)
    2. [Product Functions](#22-product-functions)
    3. [Product Constraints](#23-product-constraints)
    4. [User Characteristics](#24-user-characteristics)
    5. [Assumptions and Dependencies](#25-assumptions-and-dependencies)
4. [Requirements](#3-requirements)
    1. [External Interfaces](#31-external-interfaces)
        1. [User Interfaces](#311-user-interfaces)
        2. [Hardware Interfaces](#312-hardware-interfaces)
        3. [Software Interfaces](#313-software-interfaces)
    2. [Functional](#32-functional-requirements)
    3. [Quality of Service](#33-quality-of-service)
    4. [Compliance](#34-compliance)
    5. [Design and Implementation](#35-design-and-implementation)
        1. [Installation](#351-installation)
        2. [Distribution](#352-distribution)
        3. [Maintainability](#353-maintainability)
        4. [Reusability](#354-reusability)
        5. [Portability](#355-portability)
        6. [Cost](#356-cost)
        7. [Proof of Concept](#357-proof-of-concept)
5. [Verification](#4-verification)

---

## Revision History

| Name    | Date       | Reason for Changes | Version |
|---------|------------|--------------------|---------|
| initial | 2024-09-18 | Initial creation   | 0.1     |

---

## 1. Introduction

### 1.1 Document Purpose

The purpose of this document is to define the software requirements for the **PinMaster IoT Prototyping Platform**. This
SRS serves as a guideline for the internal development team and potential investors or business partners. It outlines
the product’s scope, features, constraints, and requirements for the revised version of the platform.

### 1.2 Product Scope

PinMaster aims to provide a platform for prototyping IoT projects by enabling clients to control and manage
embedded devices through a SaaS backend. In its first release, the platform will be focused on supporting Arduino
devices. The platform will manage the state of devices, allowing developers to connect their devices to the system,
update device states, and synchronize states through the provided Mobile SDK or web UI.

The primary goal is to accelerate the IoT prototyping process, improve the feedback loop, and provide a secure and
scalable solution for managing fleets of IoT devices.

### 1.3 Definitions, Acronyms, and Abbreviations

- **IoT**: Internet of Things.
- **SaaS**: Software as a Service.
- **API**: Application Programming Interface.
- **SSO**: Single Sign-On.
- **RBAC**: Role-Based Access Control.
- **SLA**: Service Level Agreement.
- **TLS**: Transport Layer Security.
- **Mobile SDK**: Library used by mobile application to communicate to backend
- **Embedded SDK**: Library used by devices to communicate to backend

---

## 2. Product Overview

### 2.1 Product Perspective

The **PinMaster IoT Prototyping Platform** is designed to streamline the development and management of IoT projects.
Users can log in via SSO, create applications, and manage fleets of devices under each application. The platform
provides web UI for device and application management and mobile SDK to work with device state in mobile applications.
Devices communicate securely with the backend using identity generated during device registration.

The **Mobile SDK** will be used by mobile clients to interact with the backend, allowing the mobile app to access
devices
associated with the correct application based on provided credentials. Each device created within an application will
get identity, which will be embedded into the device for secure communication.

The **Embedded SDK** will be used by devices to interact with the backend, allowing it to get actual state and apply it
in real-time.

### 2.2 Product Functions

- **User Authentication**: SSO-based login for users to access the platform.
- **Application Management**: Users can create and manage applications. Each application has its own credentials.
- **Device Management**: Users can register and manage devices within an application.
    - Devices will have TLS certificates generated upon creation for secure communication.
- **Mobile SDK**: The Mobile SDK will allow the mobile app to interact with devices within a specific application,
  authenticated using application credentials.
- **Embedded SDK**: The Embedded SDK will allow the device to get relevant state from backend or send information to the
  backed so it can be observed by users.
- **Event-based State Changes**: Devices can report state changes and receive updates from the backend in near
  real-time.

### 2.3 Product Constraints

- Support for **Arduino-based devices** only in the initial release.
- The system must support **real-time synchronization** with a delay tolerance of up to 1 second.
- The backend must meet a **99% availability SLA**.

### 2.4 User Characteristics

- **Developers**: Users who are building IoT prototypes. They may be amateurs or professionals, depending on the
  complexity of their project.
- **IoT Fleet Managers**: Responsible for managing and overseeing the operation of IoT devices across multiple
  applications.

### 2.5 Assumptions and Dependencies

- The backend and libraries will depend on external libraries and tools for secure communication and authentication
  using **OpenID Connect** for SSO.
- TLS certificates will be issued and stored securely on the backend and embedded into devices for future use.
- **Mobile SDK** will be used to interact with backend APIs.
- The system is designed for future extensibility, including support for platforms beyond Arduino.

---

## 3. Requirements

### 3.1 External Interfaces

#### 3.1.1 User Interfaces

- **Web UI**: Users can create and manage applications, devices, and credentials. The UI will allow viewing and editing
  device states.

#### 3.1.2 Software Interfaces

- **Arduino SDK**: Provides communication between Arduino devices and the backend, including state synchronization and
  event handling.
- **Mobile SDK**: Allows mobile apps to manage and control devices within the context of an application.
- **REST API**: The backend will expose a REST API for managing devices, applications, and handling event-based
  interactions between devices and the backend.

### 3.2 Functional Requirements

- **User Authentication**: Users can log in via SSO to manage their applications.
- **Application Management**: Users can create applications, manage their devices, and generate application credentials.
- **Device Registration**: Devices can be registered under an application, and TLS certificates will be generated and
  stored securely.
- **Device Communication**: Devices communicate securely with the backend using identity issued during device
  registration.
- **Mobile SDK Functionality**: The Mobile SDK allows mobile apps to communicate with the backend using application
  credentials to interact with devices in the application.

### 3.3 Quality of Service

#### 3.3.1 Usability

- The system must have a **low learning curve** for users.

#### 3.3.2 Maintainability

- The system must be **easily extensible** to add new features and support additional IoT platforms.

#### 3.3.3 Performance

- The system must support **real-time state synchronization** with a delay tolerance of up to 1 second.

#### 3.3.4 Security

- All communication between devices and the backend must be secured via **TLS** using the certificates generated during
  device registration.
- The **Mobile SDK** must securely communicate with the backend using application credentials.
- **SSO** will be used for user authentication, and **RBAC** will be implemented in future releases.

#### 3.3.5 Reliability

- The system must ensure reliable synchronization between devices and the backend.
- A **99% availability SLA** must be met in the initial release, with a target of **99.9% availability** in future
  versions.

### 3.4 Compliance

- No specific regulatory compliance is required for the initial release.

### 3.5 Design and Implementation

#### 3.5.1 Installation

- The backend will be deployed as a **SaaS platform** with client-side libraries for Arduino and Mobile SDKs.

#### 3.5.2 Distribution

- Distribution of the Arduino SDK and the Mobile SDK will be via a package manager.

#### 3.5.3 Maintainability

- The system will follow a **modular design** to ensure maintainability and extensibility.

#### 3.5.4 Reusability

- The backend should be designed to support **future platforms** beyond Arduino with minimal changes.

#### 3.5.5 Portability

- The system must be **agnostic** of specific hardware and should be portable to other IoT platforms in future releases.

#### 3.5.6 Cost

- The cost structure will be detailed in the project plan and budget.

#### 3.5.7 Proof of Concept

- A prototype will be developed for internal testing before public release.

---

## 4. Verification

- The system will be verified through **automated tests**, including unit tests and integration tests.
- **Performance monitoring** will be implemented to ensure that real-time synchronization and SLA targets are met.
- **Security tests** will verify that TLS and certificate-based communication is correctly implemented.

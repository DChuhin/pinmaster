package com.pinmaster.iot_application_service.application.ports.input

import com.pinmaster.iot_application_service.domain.IoTApplication

data class CreateIoTApplicationCommand(
    val applicationName: String
)

data class UpdateIoTApplicationCommand(
    val applicationId: String,
    val applicationName: String
)

class ApplicationNotFoundException(message: String) : Exception(message)

interface IoTApplicationService {
    fun createApplication(command: CreateIoTApplicationCommand): IoTApplication

    fun updateApplication(command: UpdateIoTApplicationCommand): IoTApplication

    fun listUserApplications(): List<IoTApplication>

    fun getApplication(applicationId: String): IoTApplication

    fun deleteApplication(applicationId: String)
}
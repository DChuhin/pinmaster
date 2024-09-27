package com.pinmaster.iot_application_service.application.ports.output

import com.pinmaster.iot_application_service.domain.IoTApplication

interface IoTApplicationStorage {

    fun save(app: IoTApplication)

    fun get(id: String): IoTApplication

    fun listUserApplications(userId: String): List<IoTApplication>

    fun delete(id: String)

}
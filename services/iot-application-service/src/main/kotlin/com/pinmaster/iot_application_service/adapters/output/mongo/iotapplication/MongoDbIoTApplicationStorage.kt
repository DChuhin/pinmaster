package com.pinmaster.iot_application_service.adapters.output.mongo.iotapplication

import com.pinmaster.iot_application_service.application.ports.input.ApplicationNotFoundException
import com.pinmaster.iot_application_service.application.ports.output.IoTApplicationStorage
import com.pinmaster.iot_application_service.domain.IoTApplication
import org.springframework.stereotype.Component

@Component
class MongoDbIoTApplicationStorage(
    private val repository: IoTApplicationRepository
) : IoTApplicationStorage {

    override fun save(app: IoTApplication) {
        val document = toDocument(app)
        repository.save(document)
    }

    override fun get(id: String): IoTApplication {
        val document = repository.findById(id)
            .orElseThrow { ApplicationNotFoundException("Could not find application $id") }
        return fromDocument(document)
    }

    override fun listUserApplications(userId: String): List<IoTApplication> {
        val documents = repository.findByUserId(userId)
        return documents.map { fromDocument(it) }
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    private fun toDocument(app: IoTApplication) =
        IoTApplicationDocument(
            id = app.id,
            name = app.name,
            userId = app.userId,
            credentials = app.credentials,
        )

    private fun fromDocument(document: IoTApplicationDocument) =
        IoTApplication(
            id = document.id,
            name = document.name,
            userId = document.userId,
            credentials = document.credentials,
        )
}
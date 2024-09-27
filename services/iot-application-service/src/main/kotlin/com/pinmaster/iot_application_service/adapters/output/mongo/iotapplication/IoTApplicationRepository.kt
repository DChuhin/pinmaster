package com.pinmaster.iot_application_service.adapters.output.mongo.iotapplication

import org.springframework.data.mongodb.repository.MongoRepository

interface IoTApplicationRepository : MongoRepository<IoTApplicationDocument, String> {

    fun findByUserId(userId: String): List<IoTApplicationDocument>

}
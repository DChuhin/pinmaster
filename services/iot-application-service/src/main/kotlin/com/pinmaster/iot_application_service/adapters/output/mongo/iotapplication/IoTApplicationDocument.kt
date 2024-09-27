package com.pinmaster.iot_application_service.adapters.output.mongo.iotapplication

import com.pinmaster.iot_application_service.domain.ApplicationCredentials
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("applications")
data class IoTApplicationDocument(
    @Id
    val id: String,
    val name: String,
    @Indexed
    val userId: String,
    val credentials: ApplicationCredentials
)
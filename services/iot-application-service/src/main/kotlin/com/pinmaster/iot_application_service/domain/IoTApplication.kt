package com.pinmaster.iot_application_service.domain

data class IoTApplication(
    val id: String,
    var name: String,
    val userId: String,
    val credentials: ApplicationCredentials
)

data class ApplicationCredentials(
    val clientId: String,
    val clientSecret: String
)
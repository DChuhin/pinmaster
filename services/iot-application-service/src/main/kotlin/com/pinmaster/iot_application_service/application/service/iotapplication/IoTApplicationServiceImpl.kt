package com.pinmaster.iot_application_service.application.service.iotapplication

import com.pinmaster.iot_application_service.application.ports.input.*
import com.pinmaster.iot_application_service.application.ports.output.IoTApplicationStorage
import com.pinmaster.iot_application_service.domain.ApplicationCredentials
import com.pinmaster.iot_application_service.domain.IoTApplication
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*

@Service
class IoTApplicationServiceImpl(
    private val appStorage: IoTApplicationStorage,
    private val userService: UserService
) : IoTApplicationService {

    val secureRandom: SecureRandom = SecureRandom()

    override fun createApplication(command: CreateIoTApplicationCommand): IoTApplication {
        val user = userService.getCurrentUser()
        val app = IoTApplication(
            id = UUID.randomUUID().toString(),
            name = command.applicationName,
            userId = user.userId,
            credentials = generateApplicationCredentials()
        )
        appStorage.save(app)
        return app
    }

    override fun updateApplication(command: UpdateIoTApplicationCommand): IoTApplication {
        val app = appStorage.get(command.applicationId)
        app.name = command.applicationName
        appStorage.save(app)
        return app
    }

    override fun listUserApplications(): List<IoTApplication> {
        val user = userService.getCurrentUser()
        return appStorage.listUserApplications(user.userId)
    }

    override fun getApplication(applicationId: String): IoTApplication {
        val user = userService.getCurrentUser()
        val app = appStorage.get(applicationId)
        if (app.userId != user.userId) {
            throw ApplicationNotFoundException("Could not find application $applicationId")
        }
        return app
    }

    override fun deleteApplication(applicationId: String) {
        appStorage.delete(applicationId)
    }

    private fun generateApplicationCredentials(): ApplicationCredentials {
        val clientId = UUID.randomUUID().toString()

        val clientSecretBytes = ByteArray(16)
        secureRandom.nextBytes(clientSecretBytes)
        val clientSecret = Base64.getEncoder().encodeToString(clientSecretBytes)

        return ApplicationCredentials(
            clientId = clientId,
            clientSecret = clientSecret
        )
    }
}
package com.pinmaster.iot_application_service.integration

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureMockMvc
class IotApplicationServiceIntegrationTest {
    companion object {
        val environment = DockerComposeContainer(File("./docker-compose.yaml"))
            .withExposedService("mongodb", 27017)

        init {
            environment.start()
        }
    }
}
package com.pinmaster.iot_application_service.integration.rest

import com.jayway.jsonpath.JsonPath
import com.pinmaster.iot_application_service.integration.IotApplicationServiceIntegrationTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class IoTApplicationRestApiIntegrationTest : IotApplicationServiceIntegrationTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `It should create application and allow read, update, delete`() {
        val userId = "testUser"
        val applicationName = "testApplication"

        // create

        val creteApplicationRequest = """
            {
                "applicationName": "$applicationName"
            }
        """.trimIndent()

        val createResponse = mockMvc.post("/api/v1/applications") {
            contentType = MediaType.APPLICATION_JSON
            content = creteApplicationRequest
            header("x-amzn-oidc-identity", userId)
        }.andExpect {
            MockMvcResultMatchers.status().isOk
            jsonPath("$.id", notNullValue())
            jsonPath("$.name", equalTo(applicationName))
            jsonPath("$.userId", equalTo(userId))
            jsonPath("$.credentials.clientId", notNullValue())
            jsonPath("$.credentials.clientSecret", notNullValue())
        }.andReturn()


        // read

        val applicationId = JsonPath.read<String>(createResponse.response.contentAsString, "$.id")
        mockMvc.get("/api/v1/applications/$applicationId") {
            header("x-amzn-oidc-identity", userId)
        }.andExpect {
            MockMvcResultMatchers.status().isOk
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(createResponse.response.contentAsString)
            }
        }

        // update

        val updatedName = "updated"
        val updateApplicationRequest = """
            {
                "applicationName": "$updatedName"
            }
        """.trimIndent()

        mockMvc.post("/api/v1/applications/$applicationId") {
            contentType = MediaType.APPLICATION_JSON
            content = updateApplicationRequest
            header("x-amzn-oidc-identity", userId)
        }.andExpect {
            MockMvcResultMatchers.status().isOk
            jsonPath("$.id", notNullValue())
            jsonPath("$.name", equalTo(updatedName))
            jsonPath("$.userId", equalTo(userId))
            jsonPath("$.credentials.clientId", notNullValue())
            jsonPath("$.credentials.clientSecret", notNullValue())
        }.andReturn()

        // delete

        mockMvc.delete("/api/v1/applications/$applicationId") {
            header("x-amzn-oidc-identity", userId)
        }.andExpect {
            MockMvcResultMatchers.status().isOk
        }.andReturn()

        mockMvc.get("/api/v1/applications/$applicationId") {
            header("x-amzn-oidc-identity", userId)
        }.andExpect {
            MockMvcResultMatchers.status().isNotFound
        }
    }

    @Test
    fun `It should show only owned applications`() {
        val userId = "testUser1"
        val applicationName = "testApplication1"
        val creteApplicationRequest = """
            {
                "applicationName": "$applicationName"
            }
        """.trimIndent()

        val createResponse = mockMvc.post("/api/v1/applications") {
            contentType = MediaType.APPLICATION_JSON
            content = creteApplicationRequest
            header("x-amzn-oidc-identity", userId)
        }.andExpect {
            MockMvcResultMatchers.status().isOk
            jsonPath("$.id", notNullValue())
            jsonPath("$.name", equalTo(applicationName))
            jsonPath("$.userId", equalTo(userId))
            jsonPath("$.credentials.clientId", notNullValue())
            jsonPath("$.credentials.clientSecret", notNullValue())
        }.andReturn()

        val applicationId = JsonPath.read<String>(createResponse.response.contentAsString, "$.id")

        mockMvc.get("/api/v1/applications/$applicationId") {
            header("x-amzn-oidc-identity", "testUser2")
        }.andExpect {
            MockMvcResultMatchers.status().isNotFound
        }

        mockMvc.get("/api/v1/applications") {
            header("x-amzn-oidc-identity", "testUser2")
        }.andExpect {
            MockMvcResultMatchers.status().isOk
            content {
                contentType(MediaType.APPLICATION_JSON)
                json("[]")
            }
        }
    }
}
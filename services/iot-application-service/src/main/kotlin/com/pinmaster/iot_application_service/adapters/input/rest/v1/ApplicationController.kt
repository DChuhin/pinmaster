package com.pinmaster.iot_application_service.adapters.input.rest.v1

import com.pinmaster.iot_application_service.application.ports.input.CreateIoTApplicationCommand
import com.pinmaster.iot_application_service.application.ports.input.IoTApplicationService
import com.pinmaster.iot_application_service.application.ports.input.UpdateIoTApplicationCommand
import com.pinmaster.iot_application_service.domain.IoTApplication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["api/v1/applications"])
class ApplicationController(
    private val appService: IoTApplicationService
) {

    @PostMapping
    fun createApplication(@RequestBody command: CreateIoTApplicationCommand): IoTApplication {
        return appService.createApplication(command);
    }

    @GetMapping
    fun listApplications(): List<IoTApplication> {
        return appService.listUserApplications();
    }

    @GetMapping("/{id}")
    fun getApplication(@PathVariable id: String): IoTApplication {
        return appService.getApplication(id);
    }

    @PostMapping("/{id}")
    fun updateApplication(@PathVariable id: String, @RequestBody request: UpdateApplicationRequest): IoTApplication {
        val command = UpdateIoTApplicationCommand(
            id, request.applicationName
        )
        return appService.updateApplication(command);
    }


    @DeleteMapping("/{id}")
    fun deleteApplication(@PathVariable id: String) {
        appService.deleteApplication(id);
    }
}

data class UpdateApplicationRequest(
    val applicationName: String
)
package com.pinmaster.iot_application_service.adapters.input.rest.v1

import com.pinmaster.iot_application_service.application.ports.input.ApplicationNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ErrorDto(
    private val errorMessage: String
)

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ApplicationNotFoundException::class)
    fun handleApplicationNotFound(e: Exception): ResponseEntity<ErrorDto> {
        val message = if (e.message != null) e.message!! else "not found"
        return ResponseEntity(ErrorDto(message), HttpStatus.NOT_FOUND)
    }

}
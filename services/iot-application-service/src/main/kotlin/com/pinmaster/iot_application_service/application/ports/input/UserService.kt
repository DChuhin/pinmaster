package com.pinmaster.iot_application_service.application.ports.input

import com.pinmaster.iot_application_service.domain.User

interface UserService {

    fun getCurrentUser(): User

}
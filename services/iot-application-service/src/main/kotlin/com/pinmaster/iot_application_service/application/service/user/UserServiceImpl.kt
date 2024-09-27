package com.pinmaster.iot_application_service.application.service.user

import com.pinmaster.iot_application_service.application.ports.input.UserService
import com.pinmaster.iot_application_service.domain.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.isAuthenticated) {
            return authentication.principal as User
        }
        throw SecurityException("Unauthenticated operation attempt")
    }
}
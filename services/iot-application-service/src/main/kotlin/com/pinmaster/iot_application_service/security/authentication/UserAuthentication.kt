package com.pinmaster.iot_application_service.security.authentication

import com.pinmaster.iot_application_service.domain.User
import org.springframework.security.authentication.AbstractAuthenticationToken

class UserAuthentication(
    private val user: User
) : AbstractAuthenticationToken(listOf()) {
    override fun getCredentials(): Any {
        return listOf<String>()
    }

    override fun getPrincipal(): User = user

    override fun isAuthenticated(): Boolean = true

}
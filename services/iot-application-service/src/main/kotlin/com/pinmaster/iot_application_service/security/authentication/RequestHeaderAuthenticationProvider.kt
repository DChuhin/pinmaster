package com.pinmaster.iot_application_service.security.authentication

import com.pinmaster.iot_application_service.domain.User
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component

@Component
class RequestHeaderAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val user = User(
            userId = authentication.principal as String
        )
        return UserAuthentication(user)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == PreAuthenticatedAuthenticationToken::class.java
    }
}
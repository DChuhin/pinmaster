package com.pinmaster.iot_application_service.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter
import org.springframework.security.web.header.HeaderWriterFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value("\${security.authentication.oidcIdentityHeader}") var oidcIdentityHeader: String,
    val provider: AuthenticationProvider
) {


    @Bean
    fun filterChain(
        http: HttpSecurity,
        authenticationManager: AuthenticationManager
    ): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(STATELESS) }
            .addFilterAfter(
                requestHeaderAuthenticationFilter(),
                HeaderWriterFilter::class.java
            )
            .authorizeHttpRequests { it.anyRequest().authenticated() }
        return http.build()
    }

    @Bean
    fun requestHeaderAuthenticationFilter(): RequestHeaderAuthenticationFilter {
        val filter = RequestHeaderAuthenticationFilter()
        filter.setPrincipalRequestHeader(oidcIdentityHeader)
        filter.setExceptionIfHeaderMissing(false)
        filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/**"))
        filter.setAuthenticationManager(authenticationManager())
        return filter
    }

    @Bean
    protected fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf(provider))
    }
}

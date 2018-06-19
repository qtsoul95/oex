package com.example.oex.web.services.security

import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
final class CustomizedAuthenticationProvider(
    passwordEncoder: PasswordEncoder,
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : DaoAuthenticationProvider() {

    init {
        setPasswordEncoder(passwordEncoder)
        userDetailsService = userDetailsServiceImpl
    }
}

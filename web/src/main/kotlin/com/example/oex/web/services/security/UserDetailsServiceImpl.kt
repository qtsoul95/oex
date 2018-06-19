package com.example.oex.web.services.security

import com.example.oex.common.db.basic.OexUserDaoImpl
import com.example.oex.common.domain.security.LoginUser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val oexUserDaoImpl: OexUserDaoImpl,
    private val objectMapper: ObjectMapper
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): LoginUser {
        val user = username?.let {
            oexUserDaoImpl.findUserByUsername(username)
        } ?: throw UsernameNotFoundException("error")

        return LoginUser(
            user,
            objectMapper.readValue(user.roleJson)
        )
    }

    fun tryLoadUserByUsername(username: String?): LoginUser? =
        try {
            loadUserByUsername(username)
        } catch (_: UsernameNotFoundException) {
            null
        }
}

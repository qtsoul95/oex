package com.example.oex.common.domain.security

import com.example.oex.common.domain.basic.AppUser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

open class LoginUser(
    val user: AppUser,
    val objectMapper: ObjectMapper
) : User(
    user.userName,
    user.password,
    user.password.isNotEmpty(),
    true,
    true,
    true,
    AuthorityUtils.createAuthorityList(*(
        listOf(user.roleJson)
            .filter(String::isNotEmpty)
            .flatMap { objectMapper.readValue<List<String>>(it) }
            .map { "ROLE_$it" }
        ).toTypedArray())
)

package com.example.oex.common.domain.security

import com.example.oex.common.Role
import com.example.oex.common.domain.basic.OexUser
import com.example.oex.common.utils.security.createAuthorityList
import com.example.oex.common.utils.security.toRoleString
import org.springframework.security.core.userdetails.User

open class LoginUser(
    val user: OexUser,
    val userRoles: Set<Role>
) : User(
    user.username,
    user.password,
    user.password.isNotEmpty(),
    true,
    true,
    true,
    (userRoles.map { it.toRoleString() }).createAuthorityList()
)

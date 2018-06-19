package com.example.oex.common.utils.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Suppress("NOTHING_TO_INLINE")
inline fun Enum<*>.toRoleString(): String = "ROLE_$name"

fun Iterable<String>.createAuthorityList(): List<GrantedAuthority> =
    map { SimpleGrantedAuthority(it) }

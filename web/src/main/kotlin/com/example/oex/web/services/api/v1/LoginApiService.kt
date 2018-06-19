package com.example.oex.web.services.api.v1

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.oex.common.db.basic.OexUserDao
import com.example.oex.common.db.basic.OexUserDaoImpl
import com.example.oex.common.domain.security.LoginUser
import com.example.oex.web.models.api.v1.AccessTokenRes
import com.example.oex.web.models.api.v1.LoginReq
import com.example.oex.web.services.security.CustomizedAuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class LoginApiService(
    private val authenticationProvider: CustomizedAuthenticationProvider
){
    fun login(request: LoginReq): Pair<AccessTokenRes, LoginUser> {
        val authenticationToken = UsernamePasswordAuthenticationToken(request.username, request.password)
        val principal = (authenticationProvider.authenticate(authenticationToken).principal as LoginUser?)
            ?: throw Exception("authentication failed")
        val user = principal.user

        // expired in 300 min
        val expireDate = Date(System.currentTimeMillis() + 1000 * 60 * 300)

        val token = JWT.create()
            .withIssuer("auth0")
            .withClaim("userId", user.userId.toString())
            .withClaim("username", request.username)
            .withClaim("expireDate", SimpleDateFormat("y/MM/dd HH:mm:ss").format(expireDate))
            .sign(Algorithm.HMAC256("3FIkZpfcJo3YDbxPsNsMBsgVsUn4B6NjPalpVZ1rTqiv3143Xdd6V5eqNOP7WTk"))
        return AccessTokenRes(token, true) to principal
    }
}

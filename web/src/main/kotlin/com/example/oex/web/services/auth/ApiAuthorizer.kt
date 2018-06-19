package com.example.oex.web.services.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class ApiAuthorizer {
    fun auth(accessToken: String?): AuthResult {
        val userId: String
        val username: String
        val expireDateString: String
        val expireDate: Date

        val jwt = JWT.decode(accessToken)
        try {
            userId = jwt.getClaim("userId").asString()
            username = jwt.getClaim("username").asString()
            expireDateString = jwt.getClaim("expireDate").asString()
            expireDate = SimpleDateFormat("y/MM/dd HH:mm:ss").parse(expireDateString)
        } catch (e: IllegalStateException) {
            throw JWTVerificationException("")
        }

        val nowDate = Date()
        val diffDate: Int = nowDate.compareTo(expireDate)
        if (diffDate > 0) {
            throw JWTVerificationException("expired")
        }

        val verifier = JWT.require(Algorithm.HMAC256("3FIkZpfcJo3YDbxPsNsMBsgVsUn4B6NjPalpVZ1rTqiv3143Xdd6V5eqNOP7WTk"))
            .withIssuer("auth0")
            .withClaim("userId", userId)
            .withClaim("username", username)
            .withClaim("expireDate", expireDateString)
            .build()
        verifier.verify(accessToken)

        return AuthResult(userId.toLong(), username)
    }

    class AuthResult(
        val userId: Long,
        val username: String
    )
}

package com.example.oex.web.controllers.api.v1

import com.example.oex.web.models.api.v1.AccessTokenRes
import com.example.oex.web.models.api.v1.LoginReq
import com.example.oex.web.models.api.v1.ResultRes
import com.example.oex.web.services.api.v1.LoginApiService
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RequestMapping("/api/v1/login")
@RestController
class LoginApiController(
    private val loginApiService: LoginApiService
){
    @PostMapping
    fun login(@RequestBody @Validated data: LoginReq, request: HttpServletRequest): AccessTokenRes {
        val (accessTokenRes, _) = loginApiService.login(data)
        return accessTokenRes
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException() = ResultRes(false, "required")

    @ExceptionHandler(UsernameNotFoundException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUsernameNotFoundException() = ResultRes(false, "authentication failure, username not found")

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleException() = ResultRes(false, "authentication failure")
}

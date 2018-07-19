package com.example.oex.web.controllers.migrate

import com.example.oex.web.services.api.v1.UserApiService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/migrate/sample")
@RestController
class SampleController(
    private val userApiService: UserApiService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun migrateData() {
        userApiService.createUser("admin", "password", "['ADMIN']")
    }
}
package com.example.oex.web.models.api.v1

import javax.validation.constraints.NotBlank

class LoginReq(
    @NotBlank
    var username: String,
    @NotBlank
    var password: String
)

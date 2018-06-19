package com.example.oex.web.models.api.v1

import org.springframework.validation.ObjectError

class ResultRes(
    var result: Boolean,
    var reason: String,
    var errors: MutableList<ObjectError>? = null
)

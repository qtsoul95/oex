package com.example.oex.web.services.api.v1

import com.example.oex.common.db.basic.OexUserDaoImpl
import com.example.oex.common.domain.basic.OexUser
import org.springframework.stereotype.Service

@Service
class UserApiService(
    private val oexUserDaoImpl: OexUserDaoImpl
) {
    fun createUser(user: OexUser) = oexUserDaoImpl.save(user)
}

package com.example.oex.web.services.api.v1

import com.example.oex.common.db.basic.OexUserDao
import com.example.oex.common.domain.basic.OexUser
import org.springframework.stereotype.Service

@Service
class UserApiService(
    private val oexUserDao: OexUserDao
) {
    fun createUser(username: String, password: String, roleJson: String) = oexUserDao.save(OexUser(username, password, roleJson))
}

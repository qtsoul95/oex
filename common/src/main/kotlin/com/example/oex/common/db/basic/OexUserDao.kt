package com.example.oex.common.db.basic

import com.example.oex.common.domain.basic.OexUser
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository

interface OexUserDao {
    fun findUserByUsername(username: String): OexUser?
}

@Repository
class OexUserDaoImpl(
    private val sessionFactory: SessionFactory
) : OexUserDao {
    override fun findUserByUsername(username: String): OexUser? {
        return sessionFactory.currentSession.get(OexUser::class.java, username)
    }
}

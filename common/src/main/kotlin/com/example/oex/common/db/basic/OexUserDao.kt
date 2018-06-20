package com.example.oex.common.db.basic

import com.example.oex.common.domain.basic.OexUser
import org.hibernate.SessionFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.io.Serializable

interface OexUserDao {
    fun findUserByUsername(username: String): OexUser?
}

@Repository
class OexUserDaoImpl(
    private val sessionFactory: SessionFactory,
    private val passwordEncoder: PasswordEncoder
) : OexUserDao {
    override fun findUserByUsername(username: String): OexUser? {
        return sessionFactory.currentSession.get(OexUser::class.java, username)
    }

    fun save(user: OexUser): Serializable? {
        user.encodePassword(passwordEncoder)
        return sessionFactory.currentSession.save(user)
    }
}

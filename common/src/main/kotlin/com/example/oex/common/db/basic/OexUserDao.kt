package com.example.oex.common.db.basic

import com.example.oex.common.domain.basic.OexUser
import org.hibernate.SessionFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable

interface OexUserDao {
    fun findUserByUsername(username: String): OexUser?
    fun save(user: OexUser): Serializable?
}

@Repository
class OexUserDaoImpl(
    private val sessionFactory: SessionFactory,
    private val passwordEncoder: PasswordEncoder
) : OexUserDao {
    override fun findUserByUsername(username: String): OexUser? {
        return sessionFactory.currentSession.get(OexUser::class.java, username)
    }

    @Transactional
    override fun save(user: OexUser): Serializable? {
        user.encodePassword(passwordEncoder)
        return sessionFactory.currentSession.save(user)
    }
}

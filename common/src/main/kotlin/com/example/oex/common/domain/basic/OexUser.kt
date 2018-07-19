package com.example.oex.common.domain.basic

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.crypto.password.PasswordEncoder
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class OexUser(
    var username: String,
    var password: String,
    @Column(name = "role_json")
    var roleJson: String
) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null

    @field:CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: LocalDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: LocalDateTime

    fun encodePassword(passwordEncoder: PasswordEncoder){
        password = passwordEncoder.encode(password)
    }
}

package com.example.oex.common.domain.basic

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class OexUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long,

    var username: String,

    var password: String,

    var roleJson: String,

    @CreationTimestamp
    var createdAt: LocalDateTime,

    @UpdateTimestamp
    var updatedAt: LocalDateTime
) : Serializable

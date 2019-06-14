package ru.danil42russia.pasta.domain

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        var google_id: String? = null,

        var name: String? = null,

        var email: String? = null,

        val token: UUID? = null
) : Serializable
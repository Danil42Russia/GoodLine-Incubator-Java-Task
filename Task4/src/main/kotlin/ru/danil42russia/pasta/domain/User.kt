package ru.danil42russia.pasta.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        var id: String? = null,

        var name: String? = null,

        var userpic: String? = null,

        var email: String? = null,

        var gender: String? = null,

        var locale: String? = null,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var lastVisit: LocalDateTime? = null
) : Serializable
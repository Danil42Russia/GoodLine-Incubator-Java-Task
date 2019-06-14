package ru.danil42russia.pasta.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import ru.danil42russia.pasta.view.UserView
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonView(UserView.ID::class)
        var id: Long? = null,

        @JsonView(UserView.GoogleID::class)
        var google_id: String? = null,

        @JsonView(UserView.Name::class)
        var name: String? = null,

        @JsonView(UserView.Email::class)
        var email: String? = null,

        @JsonProperty("pasta_token")
        @JsonView(UserView.Token::class)
        val token: String? = null
)
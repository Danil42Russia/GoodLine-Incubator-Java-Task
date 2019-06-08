package ru.danil42russia.pasta.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import ru.danil42russia.pasta.model.Private
import java.util.*
import javax.persistence.*

@Entity
@Table
class Pasta(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonView(Views.ID::class)
        var id: Long? = null,

        @JsonProperty("pasta_hash")
        @JsonView(Views.Hash::class)
        var hash: UUID? = null,

        @JsonProperty("pasta_title")
        @JsonView(Views.Title::class)
        var title: String? = null,

        @JsonProperty("pasta_date")
        @JsonView(Views.CreationDate::class)
        var creationDate: Long? = null,

        @JsonProperty("pasta_expire_date")
        @JsonView(Views.ExpireDate::class)
        var expireDate: Long? = null,

        @JsonProperty("pasta_private")
        @JsonView(Views.Private::class)
        var private: Private? = null,

        @JsonProperty("pasta_text")
        @JsonView(Views.Text::class)
        @Column(columnDefinition = "Text")
        var text: String? = null
)
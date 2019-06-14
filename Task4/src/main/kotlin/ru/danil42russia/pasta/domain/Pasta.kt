package ru.danil42russia.pasta.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import ru.danil42russia.pasta.view.PastaView
import ru.danil42russia.pasta.model.Private
import java.util.*
import javax.persistence.*

@Entity
@Table
class Pasta(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonView(PastaView.ID::class)
        var id: Long? = null,

        @JsonProperty("pasta_hash")
        @JsonView(PastaView.Hash::class)
        var hash: String? = null,

        @JsonProperty("pasta_title")
        @JsonView(PastaView.Title::class)
        var title: String? = null,

        @JsonProperty("pasta_date")
        @JsonView(PastaView.CreationDate::class)
        var creationDate: Long? = null,

        @JsonProperty("pasta_expire_date")
        @JsonView(PastaView.ExpireDate::class)
        var expireDate: Long? = null,

        @JsonProperty("pasta_private")
        @JsonView(PastaView.Private::class)
        var private: Private? = null,

        @JsonProperty("pasta_text")
        @JsonView(PastaView.Text::class)
        @Column(columnDefinition = "Text")
        var text: String? = null,

        @ManyToOne
        @JsonView(PastaView.User::class)
        var author: User? = null
)
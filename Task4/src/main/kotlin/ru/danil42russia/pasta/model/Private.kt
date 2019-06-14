package ru.danil42russia.pasta.model

import com.fasterxml.jackson.annotation.JsonValue

enum class Private(val code: Int) {
    PUBLIC(0),
    UNLISTED(1),
    PRIVATE(2);

    @JsonValue
    fun getCodes(): Int {
        return code
    }
}
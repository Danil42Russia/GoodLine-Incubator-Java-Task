package ru.danil42russia.pasta.service

import org.springframework.stereotype.Service
import ru.danil42russia.pasta.exceptions.InvalidPrivateException
import ru.danil42russia.pasta.exceptions.InvalidTimeException
import ru.danil42russia.pasta.exceptions.PastaNotFoundException
import ru.danil42russia.pasta.model.Private
import ru.danil42russia.pasta.model.Time
import java.util.*

@Service
class PastaService {
    fun expireDate(creationDate: Long, expireDate: Time): Long {
        val calendar = Calendar.getInstance()
        calendar.time = Date(creationDate * 1000)

        val newDate: Long

        newDate = when (expireDate) {
            Time.TEN_MINUTE -> {
                calendar.add(Calendar.MINUTE, 10)
                calendar.timeInMillis
            }
            Time.ONE_HOUR -> {
                calendar.add(Calendar.HOUR, 1)
                calendar.timeInMillis
            }
            Time.THREE_HOUR -> {
                calendar.add(Calendar.HOUR, 3)
                calendar.timeInMillis
            }
            Time.ONE_DAY -> {
                calendar.add(Calendar.DATE, 1)
                calendar.timeInMillis
            }
            Time.ONE_WEEK -> {
                calendar.add(Calendar.DATE, 7)
                calendar.timeInMillis
            }
            Time.ONE_MONTH -> {
                calendar.add(Calendar.MONTH, 1)
                calendar.timeInMillis
            }
            Time.NEVER -> 0
        }

        return newDate / 1000L
    }

    fun getTitle(title: String?): String {
        return title ?: "Untitled"
    }

    fun codeToPrivate(source: String): Private {
        return Private.values().find { it.code.toString() == source } ?: throw InvalidPrivateException()
    }

    fun abbreviationToTime(abbreviation: String): Time {
        return Time.values().find { it.abbreviation == abbreviation } ?: throw InvalidTimeException()
    }

    fun parseHash(uuid: String): UUID {
        try {
            return UUID.fromString(uuid)
        } catch (ex: IllegalArgumentException) {
            throw PastaNotFoundException()
        }
    }
}
package ru.danil42russia.pasta.controller

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.*
import ru.danil42russia.pasta.domain.MyError
import ru.danil42russia.pasta.domain.Pasta
import ru.danil42russia.pasta.domain.User
import ru.danil42russia.pasta.exceptions.*
import ru.danil42russia.pasta.view.PastaView
import ru.danil42russia.pasta.model.Private
import ru.danil42russia.pasta.repository.PastaRepository
import ru.danil42russia.pasta.repository.UserRepository
import ru.danil42russia.pasta.service.PastaService
import ru.danil42russia.pasta.service.UserService
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("api")
class PastaController(
        private val pastaRepository: PastaRepository,
        private val userRepository: UserRepository,
        private val pastaService: PastaService,
        private val userService: UserService
) {
    @GetMapping
    @JsonView(PastaView.PastaList::class)
    fun list(): List<Pasta> {
        val date = Instant.now().epochSecond
        return pastaRepository.findAllNotPublic(date)
    }

    @GetMapping("{hash}")
    @JsonView(PastaView.PastaOne::class)
    fun getPastaFromHash(
            @PathVariable("hash") hash: String
    ): Pasta {
        val date = Instant.now().epochSecond
        return pastaRepository.findByHash(date, pastaService.parseHash(hash)) ?: throw PastaNotFoundException()
    }

    @PostMapping("/search")
    @JsonView(PastaView.PastaOne::class)
    fun search(
            @RequestParam(name = "text") text: String
    ): List<Pasta> {
        if (text.isEmpty()) {
            throw PastaNotFoundException()
        }

        val date = Instant.now().epochSecond
        return pastaRepository.findByTextOrTitle(date, text)
    }

    @PostMapping
    @JsonView(PastaView.PastaOne::class)
    fun addPasta(
            @RequestParam(name = "pasta_title", defaultValue = "Untitled") title: String,
            @RequestParam(name = "pasta_private") code: String,
            @RequestParam(name = "pasta_expire_date") expireDate: String,
            @RequestParam(name = "pasta_text") text: String,
            @RequestParam(name = "pasta_token", required = false) token: String?
    ): Pasta {
        val date = Instant.now().epochSecond
        val private = pastaService.codeToPrivate(code)
        var author: User? = null

        if (token != null) {
            val user = userRepository.findByToken(token)

            if (user.isPresent) {
                author = user.get()
            } else {
                throw InvalidTokenException()
            }
        } else {
            if (private == Private.PRIVATE) {
                throw InvalidTokenException()
            }
        }

        if (text.isEmpty()) {
            throw TextEmptyException()
        }

        val pasta = Pasta(
                title = title,
                private = private,
                creationDate = date,
                expireDate = pastaService.expireDate(date, pastaService.abbreviationToTime(expireDate)),
                text = text,
                hash = UUID.randomUUID(),
                author = author
        )

        return pastaRepository.save(pasta)
    }

    @GetMapping("/my_pasta")
    @JsonView(PastaView.PastaOne::class)
    fun myPasta(
            @RequestParam(name = "pasta_token", required = false) token: String?
    ): List<Pasta> {
        if (token != null && userService.tokenIsValid(token)) {
            return pastaRepository.getMyPasta(token)
        } else {
            throw InvalidTokenException()
        }
    }

    @ExceptionHandler(TextEmptyException::class)
    fun handleTest(exception: TextEmptyException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = exception.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidPrivateException::class)
    fun handlePrivate(exception: InvalidPrivateException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = exception.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidTimeException::class)
    fun handlePrivate(exception: InvalidTimeException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = exception.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PastaNotFoundException::class)
    fun handlePastaNotFound(exception: PastaNotFoundException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = exception.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handlePastaNotFound(exception: MissingServletRequestParameterException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = "Bad API request, ${exception.parameterName}"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidTokenException::class)
    fun handleInvalidDevToken(exception: InvalidTokenException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = exception.message), HttpStatus.BAD_REQUEST)
    }
}
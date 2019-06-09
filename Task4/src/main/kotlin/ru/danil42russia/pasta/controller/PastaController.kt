package ru.danil42russia.pasta.controller

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.*
import ru.danil42russia.pasta.domain.MyError
import ru.danil42russia.pasta.domain.Pasta
import ru.danil42russia.pasta.domain.Views
import ru.danil42russia.pasta.exceptions.InvalidPrivateException
import ru.danil42russia.pasta.exceptions.InvalidTimeException
import ru.danil42russia.pasta.exceptions.PastaNotFoundException
import ru.danil42russia.pasta.exceptions.TextEmptyException
import ru.danil42russia.pasta.repository.PastaRepository
import ru.danil42russia.pasta.service.PastaService
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("api")
class PastaController(
        private val pastaRepository: PastaRepository,
        private val pastaService: PastaService
) {
    @GetMapping
    @JsonView(Views.PastaList::class)
    fun list(): List<Pasta> {
        val date = Instant.now().epochSecond
        return pastaRepository.findAllNotPublic(date)
    }

    @GetMapping("{hash}")
    @JsonView(Views.PastaOne::class)
    fun getPastaFromHash(
            @PathVariable("hash") hash: String
    ): Pasta {
        val date = Instant.now().epochSecond
        return pastaRepository.findByHash(date, pastaService.parseHash(hash)) ?: throw PastaNotFoundException()
    }

    @PostMapping("/search")
    @JsonView(Views.PastaOne::class)
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
    @JsonView(Views.PastaOne::class)
    fun addPasta(
            @RequestParam(name = "pasta_title", required = false) title: String,
            @RequestParam(name = "pasta_private") code: String,
            @RequestParam(name = "pasta_expire_date") expireDate: String,
            @RequestParam(name = "pasta_text") text: String
    ): Pasta {
        val date = Instant.now().epochSecond

        if (text.isEmpty()) {
            throw TextEmptyException()
        }

        val pasta = Pasta(
                title = pastaService.getTitle(title),
                private = pastaService.codeToPrivate(code),
                creationDate = date,
                expireDate = pastaService.expireDate(date, pastaService.abbreviationToTime(expireDate)),
                text = text,
                hash = UUID.randomUUID()
        )

        return pastaRepository.save(pasta)
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
}
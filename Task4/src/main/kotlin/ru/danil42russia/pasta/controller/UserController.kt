package ru.danil42russia.pasta.controller

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.danil42russia.pasta.domain.MyError
import ru.danil42russia.pasta.domain.User
import ru.danil42russia.pasta.exceptions.UserNotAuthenticationException
import ru.danil42russia.pasta.view.UserView

@RestController
@RequestMapping("account")
class UserController{

    @GetMapping("/get_token")
    @JsonView(UserView.Token::class)
    fun getToken(
            @AuthenticationPrincipal user: User?
    ): User {
        return user ?: throw UserNotAuthenticationException()
    }

    @ExceptionHandler(UserNotAuthenticationException::class)
    fun handleUserNotAuthentication(exception: UserNotAuthenticationException): ResponseEntity<MyError> {
        return ResponseEntity(MyError(message = exception.message), HttpStatus.BAD_REQUEST)
    }
}
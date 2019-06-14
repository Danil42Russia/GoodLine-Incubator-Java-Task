package ru.danil42russia.pasta.service

import org.springframework.stereotype.Service
import ru.danil42russia.pasta.repository.UserRepository

@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun tokenIsValid(token: String): Boolean {
        val user = userRepository.findByToken(token)
        return user.isPresent
    }
}
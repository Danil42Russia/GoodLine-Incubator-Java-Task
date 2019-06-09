package ru.danil42russia.pasta.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.danil42russia.pasta.domain.User

@Repository
interface UserDetailsRepository : JpaRepository<User, String>
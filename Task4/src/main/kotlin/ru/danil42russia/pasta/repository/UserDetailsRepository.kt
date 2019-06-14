package ru.danil42russia.pasta.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.danil42russia.pasta.domain.User
import java.util.*

@Repository
interface UserDetailsRepository : JpaRepository<User, String> {
    @Query("select u from User u where u.google_id = :google_id")
    fun findByGoogleId(
            @Param("google_id") text: String
    ): Optional<User>
}
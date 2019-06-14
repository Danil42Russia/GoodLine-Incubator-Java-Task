package ru.danil42russia.pasta.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.danil42russia.pasta.domain.Pasta
import ru.danil42russia.pasta.model.Private
import java.util.*
import javax.transaction.Transactional

@Repository
interface PastaRepository : JpaRepository<Pasta, Long> {
    @Query(value = "select * from pasta p where p.Private = 0 and (:thisDate <= p.expire_date or p.expire_date = 0) order by p.id desc limit 10", nativeQuery = true)
    fun findAllNotPublic(
            @Param("thisDate") date: Long
    ): List<Pasta>

    @Query(value = "select * from pasta p where p.Hash = :Hash and (:thisDate <= p.expire_date or p.expire_date = 0)", nativeQuery = true)
    fun findByHash(
            @Param("thisDate") date: Long,
            @Param("Hash") hash: String
    ): Pasta?

    @Query(value = "select * from pasta p where p.Private = 0 and (:thisDate <= p.expire_date or p.expire_date = 0) and (p.text LIKE CONCAT('%',:searchText,'%') or p.title LIKE CONCAT('%',:searchText,'%')) order by p.id desc limit 10", nativeQuery = true)
    fun findByTextOrTitle(
            @Param("thisDate") date: Long,
            @Param("searchText") text: String
    ): List<Pasta>

    @Query("select p from Pasta p left join User u on p.author = u.id where u.token = :token order by p.id desc ")
    fun getMyPasta(
            @Param("token") token: String
    ): List<Pasta>

    @Modifying
    @Transactional
    @Query("update Pasta p set p.private = :private where p.hash = :hash and (:this_date <= p.expireDate or p.expireDate = 0)")
    fun updatePrivateToken(
            @Param("this_date") date: Long,
            @Param("hash") hash: String,
            @Param("private") private: Private
    )
}
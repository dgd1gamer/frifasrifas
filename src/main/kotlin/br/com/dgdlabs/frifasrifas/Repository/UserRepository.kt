package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByName(username: String): Boolean
    fun findByName(username: String): User?
}
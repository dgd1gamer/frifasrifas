package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
}
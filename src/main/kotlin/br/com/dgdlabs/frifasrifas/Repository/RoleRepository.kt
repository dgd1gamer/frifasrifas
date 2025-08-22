package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RoleRepository : JpaRepository<Role, UUID> {
    fun findByName(name: String): Role?
    fun existsByName(name: String): Boolean
}
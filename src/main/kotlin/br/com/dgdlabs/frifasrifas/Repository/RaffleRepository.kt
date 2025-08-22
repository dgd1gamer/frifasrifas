package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.Raffle
import br.com.dgdlabs.frifasrifas.Models.RaffleStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RaffleRepository : JpaRepository<Raffle, UUID> {
    fun findByStatus(status: RaffleStatus): List<Raffle>
}
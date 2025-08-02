package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.Raffle
import org.springframework.data.jpa.repository.JpaRepository

interface RaffleRespository : JpaRepository<Raffle, Long> {
}
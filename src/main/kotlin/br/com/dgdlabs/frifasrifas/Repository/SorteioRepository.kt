package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.Sorteio
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SorteioRepository : JpaRepository<Sorteio, UUID>{
}
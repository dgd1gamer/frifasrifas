package br.com.dgdlabs.frifasrifas.Repository

import br.com.dgdlabs.frifasrifas.Models.Sorteio
import org.springframework.data.jpa.repository.JpaRepository

interface SorteioRepository : JpaRepository<Sorteio, Long>{
}
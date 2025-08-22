package br.com.dgdlabs.frifasrifas.Dto

import br.com.dgdlabs.frifasrifas.Models.RaffleStatus
import java.util.UUID

data class RaffleResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val status: RaffleStatus,
    val createdAt: String
)

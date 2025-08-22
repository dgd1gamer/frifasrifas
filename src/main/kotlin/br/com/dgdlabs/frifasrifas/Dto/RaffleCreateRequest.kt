package br.com.dgdlabs.frifasrifas.Dto

import java.util.UUID

data class RaffleCreateRequest (
    val title: String,
    val description: String,
    val pointValue: Double,
    val pointQuantity: Int,
    val prizeQuantity: Int,
    val imageUrl: String?,
    val paymentLink: String,
    val contactPhone: String,
    val endDate: String?,
    val creatorId: UUID
)
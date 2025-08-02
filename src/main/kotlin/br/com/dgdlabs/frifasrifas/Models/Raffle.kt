package br.com.dgdlabs.frifasrifas.Models

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.Date
import java.util.UUID
import jakarta.persistence.*

@Entity
data class Raffle(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val title: String,
    val description: String,
    // The value of each point in the raffle
    val pointValue: Double,
    // The total number of points available in the raffle
    val pointQuantity: Int,
    // The total number of points that can be purchased by a user
    val prizeQuantity: Int,
    // Image of the rewards
    val imageUrl: String?,
    // The link to the payment system for purchasing points
    val paymentLink: String,
    // Contact to validate the raffle
    val contactPhone: String,

    val endDate: Date? = null,

    // Status of the raffle
    @Enumerated(EnumType.STRING)
    var status: RaffleStatus = RaffleStatus.PENDING,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    val creator: User,

    val createdAt: Date = Date()
)

enum class RaffleStatus {
    PENDING, APPROVED, EXPIRED, FINISHED, BLOCKED
}
package br.com.dgdlabs.frifasrifas.Models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val number: Int,

    val buyerName: String?,
    val buyerPhone: String?,

    @Enumerated(EnumType.STRING)
    var status: TicketStatus = TicketStatus.AVAILABLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffle_id")
    val raffle: Raffle
)

enum class TicketStatus {
    AVAILABLE, PENDING, CONFIRMED
}
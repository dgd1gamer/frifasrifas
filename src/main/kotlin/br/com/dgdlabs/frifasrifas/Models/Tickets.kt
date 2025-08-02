package br.com.dgdlabs.frifasrifas.Models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
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
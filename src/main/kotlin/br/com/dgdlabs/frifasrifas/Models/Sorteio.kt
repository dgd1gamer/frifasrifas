package br.com.dgdlabs.frifasrifas.Models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
data class Sorteio(
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    val id: UUID? = null,

    @OneToOne
    @JoinColumn(name = "raffle_id")
    val raffle: Raffle,

    @ElementCollection
    @CollectionTable(name = "sorteio_numbers", joinColumns = [JoinColumn(name = "sorteio_id")])
    @Column(name = "number")
    val drawnNumbers: List<Int>,

    val executionDate: Date = Date()
)
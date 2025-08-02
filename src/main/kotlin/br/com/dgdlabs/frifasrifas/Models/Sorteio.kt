package br.com.dgdlabs.frifasrifas.Models

import jakarta.persistence.*
import java.util.*

@Entity
data class Sorteio(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
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
package br.com.dgdlabs.frifasrifas.Models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Number(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val number: Int,
    val username : String? = null,
    val phone : String? = null,
    val rifaId: Long? = null,
)
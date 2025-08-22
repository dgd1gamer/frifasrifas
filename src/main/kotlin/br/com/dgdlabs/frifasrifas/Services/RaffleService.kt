package br.com.dgdlabs.frifasrifas.Services

import br.com.dgdlabs.frifasrifas.Dto.RaffleCreateRequest
import br.com.dgdlabs.frifasrifas.Dto.RaffleResponse
import br.com.dgdlabs.frifasrifas.Repository.RaffleRepository
import br.com.dgdlabs.frifasrifas.Repository.UserRepository
import org.springframework.stereotype.Service
import br.com.dgdlabs.frifasrifas.Models.Raffle
import br.com.dgdlabs.frifasrifas.Models.RaffleStatus

@Service
class RaffleService(
    private val raffleRepository: RaffleRepository,
    private val userRepository: UserRepository
){
//    fun create(request: RaffleCreateRequest): RaffleResponse {
//    val user = userRepository.findById(request.creatorId)
//        .orElseThrow { IllegalArgumentException("Usuário não encontrado") }
//
//    val raffle = Raffle(
//        title = request.title,
//        description = request.description,
//        pointValue = request.pointValue,
//        pointQuantity = request.pointQuantity,
//        prizeQuantity = request.prizeQuantity,
//        imageUrl = request.imageUrl,
//        paymentLink = request.paymentLink,
//        contactPhone = request.contactPhone,
//        endDate = request.endDate?.let { LocalDate.parse(it).atStartOfDay().toInstant(ZoneOffset.UTC).let { Date.from(it) } },
//        status = RaffleStatus.PENDING,
//        creator = user
//    )
//
//    val saved = raffleRepository.save(raffle)
//
//    return RaffleResponse(
//        id = saved.id!!,
//        title = saved.title,
//        description = saved.description,
//        status = saved.status,
//        createdAt = saved.createdAt.toString()
//    )
//}
//
//    fun listApproved(): List<RaffleResponse> {
//        return raffleRepository.findByStatus(RaffleStatus.APPROVED)
//            .map {
//                RaffleResponse(it.id!!, it.title, it.description, it.status, it.createdAt.toString())
//            }
//    }
//
//    fun listPending(): List<Raffle> = raffleRepository.findByStatus(RaffleStatus.PENDING)
}
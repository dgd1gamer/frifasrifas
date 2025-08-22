package br.com.dgdlabs.frifasrifas.Controllers

import br.com.dgdlabs.frifasrifas.Dto.RaffleCreateRequest
import br.com.dgdlabs.frifasrifas.Dto.RaffleResponse
import br.com.dgdlabs.frifasrifas.Models.Raffle
import br.com.dgdlabs.frifasrifas.Services.RaffleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/raffles")
class RaffleController(
    private val raffleService: RaffleService
) {

//    @PostMapping
//    fun createRaffle(@RequestBody request: RaffleCreateRequest): ResponseEntity<RaffleResponse> {
//        val response = raffleService.create(request)
//        return ResponseEntity.status(HttpStatus.CREATED).body(response)
//    }
//
//    @GetMapping("/aprovadas")
//    fun listarAprovadas(): ResponseEntity<List<RaffleResponse>> {
//        return ResponseEntity.ok(raffleService.listApproved())
//    }
//
//    @GetMapping("/pendentes")
//    fun listarPendentes(): ResponseEntity<List<Raffle>> {
//        return ResponseEntity.ok(raffleService.listPending())
//    }
}

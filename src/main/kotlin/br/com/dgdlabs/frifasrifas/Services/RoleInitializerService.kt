package br.com.dgdlabs.frifasrifas.Services

import br.com.dgdlabs.frifasrifas.Models.Role
import br.com.dgdlabs.frifasrifas.Repository.RoleRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class RoleInitializerService(private val roleRepository: RoleRepository) {

    @PostConstruct
    fun initRoles() {
        if (roleRepository.findByName("ROLE_USER") == null) {
            roleRepository.save(Role(name = "ROLE_USER"))
        }
        if (roleRepository.findByName("ROLE_ADMINISTRATOR") == null) {
            roleRepository.save(Role(name = "ROLE_ADMINISTRATOR"))
        }
    }
}
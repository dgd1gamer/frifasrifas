package br.com.dgdlabs.frifasrifas.Security.authentication

import br.com.dgdlabs.frifasrifas.Repository.UserRepository
import br.com.dgdlabs.frifasrifas.Security.authentication.JwtTokenService
import br.com.dgdlabs.frifasrifas.Security.config.SecurityConfiguration
import br.com.dgdlabs.frifasrifas.Security.userdetails.UserDetailsImpl
import br.com.dgdlabs.frifasrifas.Security.userdetails.UserDetailsServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class UserAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userDetailsServiceImpl: UserDetailsServiceImpl


    @Autowired
    private lateinit var jwtTokenService: JwtTokenService

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // Verifica se o endpoint requer autenticação antes de processar a requisição
            if (checkIfEndpointIsNotPublic(request)) {
                val token = recoveryToken(request) // Recupera o token do cabeçalho Authorization da requisição
                if (token != null) {
                    try {
                        val subject = jwtTokenService.getSubjectFromToken(token)
                        val user = userRepository.findByEmail(subject)

                        if (user != null) {
                            val userDetails = UserDetailsImpl(user)

                            // Cria um objeto de autenticação do Spring Security
                            val authentication = UsernamePasswordAuthenticationToken(userDetails.username, null, userDetails.authorities)

                            // Define o objeto de autenticação no contexto de segurança do Spring Security
                            SecurityContextHolder.getContext().authentication = authentication
                        } else {
                            throw UsernameNotFoundException("Usuário não encontrado para o token fornecido")
                        }
                    } catch (e: Exception) {
                        throw RuntimeException("Erro ao processar o token: ${e.message}")
                    }
                } else {
                    throw RuntimeException("Token ausente na requisição")
                }
            }
        } catch (e: Exception) {
            // Responder com erro 401 Unauthorized se houver qualquer falha na autenticação
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
            return
        }

        filterChain.doFilter(request, response)
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private fun recoveryToken(request: HttpServletRequest): String? {
        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "")
        }
        return null
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private fun checkIfEndpointIsNotPublic(request: HttpServletRequest): Boolean {
        return !SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED.contains(request.requestURI) && !request.requestURI.startsWith("/uploads")
    }

}

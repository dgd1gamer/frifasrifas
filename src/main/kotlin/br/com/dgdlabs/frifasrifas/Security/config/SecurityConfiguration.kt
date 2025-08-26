package br.com.dgdlabs.frifasrifas.Security.config

import br.com.dgdlabs.frifasrifas.Security.authentication.UserAuthenticationFilter
import br.com.dgdlabs.frifasrifas.Security.config.JwtAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Autowired
    private lateinit var unauthorizedHandler: JwtAuthenticationEntryPoint

    @Autowired
    private lateinit var userAuthenticationFilter: UserAuthenticationFilter


    companion object {
        val ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = arrayOf(
            "/login", //url que usaremos para fazer login
            "/create", //url que usaremos para criar um usuário
            "/uploads/**",
            "**",
            "/helloworld"
        )
        // Endpoints que requerem autenticação para serem acessados
        val ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = arrayOf(
            "/refresh-token",
        )
        // Endpoints que só podem ser acessador por usuários com permissão de cliente
        val ENDPOINTS_USER: Array<String> = emptyArray()
        // Endpoints que só podem ser acessador por usuários com permissão de administrador
        val ENDPOINTS_ADMIN = arrayOf(
            "/",
        )
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors{}
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura a política de criação de sessão como stateless
            }
            .authorizeHttpRequests{authorize ->
                authorize
                    .requestMatchers("/**").permitAll()
                    .requestMatchers(*ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll() // Permitir acesso livre primeiro
                    .requestMatchers(*ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR") // Definir acesso administrativo
                    .requestMatchers(*ENDPOINTS_USER).hasRole("USER") // Definir acesso do usuário
                    .requestMatchers(*ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated() // Autenticação para todos os outros endpoints restritos
                    .anyRequest().denyAll()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(unauthorizedHandler)
            }
            .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }


    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()





}

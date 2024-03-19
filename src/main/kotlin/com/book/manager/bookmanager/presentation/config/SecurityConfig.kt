package com.book.manager.bookmanager.presentation.config

import com.book.manager.bookmanager.application.service.AuthenticationService
import com.book.manager.bookmanager.domain.enum.RoleType
import com.book.manager.bookmanager.presentation.handler.BookManagerAccessDeniedHandler
import com.book.manager.bookmanager.presentation.handler.BookManagerAuthenticationEntryPoint
import com.book.manager.bookmanager.presentation.handler.BookManagerAuthenticationFailureHandler
import com.book.manager.bookmanager.presentation.handler.BookManagerAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
                    .anyRequest().authenticated()
            }
            .csrf { csrf ->
                csrf.disable()
            }
            .formLogin { login ->
                login.loginProcessingUrl("/login").permitAll()
                    .usernameParameter("email")
                    .passwordParameter("pass")
                    .successHandler(BookManagerAuthenticationSuccessHandler())
                    .failureHandler(BookManagerAuthenticationFailureHandler())
            }
            .exceptionHandling { ex ->
                ex.authenticationEntryPoint(BookManagerAuthenticationEntryPoint())
                    .accessDeniedHandler(BookManagerAccessDeniedHandler())
            }
            .cors { cors ->
                cors.configurationSource(corsConfigurationSource())
            }

        return http.build()
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("http://localhost:8081")
        corsConfiguration.allowCredentials = true

        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)
        return corsConfigurationSource
    }
}

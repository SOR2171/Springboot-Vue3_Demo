package com.github.sor2171.backend.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    val authenticationSuccessHandler =
        { request: HttpServletRequest,
          response: HttpServletResponse,
          authentication: Authentication ->
            response.writer.write("Login successful")
        }

    val authenticationFailureHandler =
        { request: HttpServletRequest,
          response: HttpServletResponse,
          exception: Exception ->
            response.writer.write("Login failed: ${exception.message}")
        }

    val logoutSuccessHandler =
        { request: HttpServletRequest,
          response: HttpServletResponse,
          authentication: Authentication ->
        }

    val logoutFailureHandler =
        { request: HttpServletRequest,
          response: HttpServletResponse,
          exception: Exception ->
        }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin {
                it
                    .loginProcessingUrl("/api/auth/login")
                    .failureHandler(authenticationFailureHandler)
                    .successHandler(authenticationSuccessHandler)
            }
            .logout {
                it
                    .logoutUrl("/api/auth/logout")
                    .logoutSuccessHandler(logoutSuccessHandler)
            }
            .csrf { it.disable() }
            .sessionManagement {
                it
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .build()
    }
}
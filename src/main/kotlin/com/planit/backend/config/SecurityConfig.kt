package com.planit.backend.config

import com.planit.backend.Filter.FirebaseAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig (
    private val firebaseAuthenticationFilter: FirebaseAuthenticationFilter
){
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.POST,"/api/public/**", "/api/users").permitAll()
                it.requestMatchers(HttpMethod.GET,"/api/tasks/**", "/api/plans/**").authenticated()
                it.requestMatchers(HttpMethod.DELETE, "/api/users/me").authenticated()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}

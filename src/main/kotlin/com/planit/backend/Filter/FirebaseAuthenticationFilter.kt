package com.planit.backend.Filter

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource

@Component
class FirebaseAuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.method == "OPTIONS") {
            response.status = HttpServletResponse.SC_OK
            filterChain.doFilter(request, response)
            return
        }

        val authHeader = request.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.removePrefix("Bearer ")

            try {
                val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
                request.setAttribute("firebaseUid", decodedToken.uid)

                val authentication = UsernamePasswordAuthenticationToken(
                    decodedToken.uid,
                    null,
                    emptyList()
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication // <-- Clave!

            } catch (e: FirebaseAuthException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Firebase token")
                return
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header")
            return
        }

        filterChain.doFilter(request, response)
    }

}

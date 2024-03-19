package com.book.manager.bookmanager.presentation.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.access.AccessDeniedException

class BookManagerAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        HttpServletResponse.SC_FORBIDDEN.also { response.status = it }
    }
}
package com.example.app.config.auth.exceptionHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("CustomAuthenticationEntryPoint's commence invoke...");
		response.sendRedirect(request.getContextPath()+"/login?error=" + authException.getMessage()); //이상태면 인증이 안된 상태이기 때문에 계속 리다이렉팅을 할 것임
	}

}

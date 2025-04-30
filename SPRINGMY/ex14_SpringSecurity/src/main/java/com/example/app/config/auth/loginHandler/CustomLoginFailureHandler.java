package com.example.app.config.auth.loginHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
			log.error("CustomLoginFailureHandler's onAuthenticationFailure invoke...");
			
			response.sendRedirect(request.getContextPath()+"/login?error=" + exception.getMessage()); //로그인 페이지로 이동
	}

}

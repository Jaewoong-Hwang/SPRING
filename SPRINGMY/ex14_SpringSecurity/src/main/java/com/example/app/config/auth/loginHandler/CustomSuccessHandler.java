package com.example.app.config.auth.loginHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 이 부분ㅇ 중요!!!
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		log.info("CustomSuccessHandler's onAuthenticationSuccess invoke..."); //login 성공해도 여기서 멈출 것임
		
		response.sendRedirect(request.getContextPath()+"/"); //메인으로 이동
	}

}

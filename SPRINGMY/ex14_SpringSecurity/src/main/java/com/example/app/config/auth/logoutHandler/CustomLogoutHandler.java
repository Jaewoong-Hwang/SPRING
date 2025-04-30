package com.example.app.config.auth.logoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
			
		log.info("CustomLogoutHandler's logout invoke...");
		
		//session 초기화 작업
		HttpSession session = request.getSession(false);
		if(session!=null)
			session.invalidate();
	}

}

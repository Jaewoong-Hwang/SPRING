package com.example.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  // security config 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CSRF 비활성화
		http.csrf().disable(); // + CSRF 토큰 값 확인 x, GET /logout 처리 가능
		
		//권한체크
		http.authorizeRequests()
			.anyRequest().authenticated();  // 어떤 위치로 가든 인증, 이대로 실행하면 403 -> 인증하지 않았기 때문에
		
		
		
		//로그인
		http.formLogin()
			.permitAll();
		
		//로그아웃
		http.logout()
			.permitAll();
		
		//예외처리
		
	
	}

	
	@Override  //계정 임시로 만들기
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user1")
			.password("{noop}1234") //암호화 알고리즘을 쓰지않은 옵션
			.roles("USER"); //ROLE_ 이게 자동으로 붙음 --> ROLE_USER
	}
	
	
	
}

package com.example.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity  // security config 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CSRF 비활성화
		http.csrf().disable(); // + CSRF 토큰 값 확인 x, GET /logout 처리 가능 // 원래는 post 방식으로만 가능 지금은 test
		
		//CSRF를 쿠키형태로 전달하는 방법
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		//권한체크
		http.authorizeRequests()
			.antMatchers("/","/join").permitAll()  //최상위와 회원가입은 그냥 통과
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/manager").hasRole("MANAGER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest()
//			.permitAll();
			.authenticated();  // 어떤 위치로 가든 인증, 이대로 실행하면 403 -> 인증하지 않았기 때문에
		
		
		
		//로그인
		http.formLogin()
			.loginPage("/login")
			.permitAll();
		
		//로그아웃
		http.logout()
			.permitAll();
		
		//예외처리
		
	
	}

	
	@Override  //계정 임시로 만들기
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password(passwordEncoder.encode("1234")) 
			.roles("USER"); 
		
		auth.inMemoryAuthentication()
		.withUser("manager")
		.password("{noop}1234") 
		.roles("MANAGER"); 
		
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}1234") 
		.roles("ADMIN"); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	
	
}

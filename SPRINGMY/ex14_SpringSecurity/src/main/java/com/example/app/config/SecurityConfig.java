package com.example.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.app.config.auth.PrincipalDetailsService;
import com.example.app.config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.app.config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.app.config.auth.loginHandler.CustomLoginFailureHandler;
import com.example.app.config.auth.loginHandler.CustomSuccessHandler;
import com.example.app.config.auth.logoutHandler.CustomLogoutHandler;
import com.example.app.config.auth.logoutHandler.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity  // security config 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;  // 만들어 놓은 PrincipalDetailsService 사용
	
	@Autowired
	private DataSource dataSource3;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CSRF 비활성화
		http.csrf().disable(); // + CSRF 토큰 값 확인 x, GET /logout 처리 가능 // 원래는 post 방식으로만 가능 지금은 test
		
		//CSRF를 쿠키형태로 전달하는 방법
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		//권한체크
		http.authorizeRequests()
			.antMatchers("/","/join","/login").permitAll()  //최상위와 회원가입은 그냥 통과
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/manager").hasRole("MANAGER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest()
//			.permitAll();
			.authenticated();  // 어떤 위치로 가든 인증, 이대로 실행하면 403 -> 인증하지 않았기 때문에
		
		
		//로그인
		http.formLogin()
			.loginPage("/login")
			.permitAll()
			.successHandler(new CustomSuccessHandler()) // 로그인 성공시  
			.failureHandler(new CustomLoginFailureHandler()); // 로그인 실패시
			
		//로그아웃
		http.logout()
			.permitAll()
			.addLogoutHandler(new CustomLogoutHandler())  //직접추가
			.logoutSuccessHandler(new CustomLogoutSuccessHandler());// 로그아웃 성공시
		
		//예외처리
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint())  //미인증 계정 예외처리
			.accessDeniedHandler(new CustomAccessDeniedHandler()); //권한 부족시 예외처리
	
		
		//remember-me
		http.rememberMe()
			.key("rememberMeKey") //해당 서비스를 연결하는 데 쓰임
			.rememberMeParameter("remember-me") //jsp에 있는 이름과 동일
			.alwaysRemember(false) // 체크박스에 체크를 할 때만 처림
			.tokenValiditySeconds(60*60) //토큰 유효시간
			.tokenRepository(tokenRepository()); //토큰을 담을 repo
	}

	
	@Override  //계정 임시로 만들기
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//inmemory 방식
//		auth.inMemoryAuthentication()
//			.withUser("user")
//			.password(passwordEncoder.encode("1234")) 
//			.roles("USER"); 
//		
//		auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("{noop}1234") 
//		.roles("MANAGER"); 
//		
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("{noop}1234") 
//		.roles("ADMIN"); 
		
		auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder); // 연결
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository tokenRepository (){
		//dataSource를 먼저 연결해야 함
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource3);
		
		return repo;
	}
	
	
	
}

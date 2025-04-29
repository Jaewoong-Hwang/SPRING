package com.example.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MemoInterceptor implements HandlerInterceptor {

	//소스->override method 에서 전부 클릭 후 생성
	
	//MemoController로 이동하기 전 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MemoInterceptor's preHandle...");
		return true;  // true 값을 넣지 않으면 진입이 안됨
	}
	
	//MemoController 작업이 끝난 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MemoInterceptor's postHandle...");
	}

	//View Page 랜더링 된 이후 처리
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MemoInterceptor's afterCompletion...");
	}

	
}

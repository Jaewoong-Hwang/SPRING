package com.example.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {
	
	
	//pointcut
	
	@Before("execution(boolean com.example.app.domain.service.MemoServiceImpl.registraionMemo(..) )") //(..)은 어떤 함수든 받겠다는 뜻
	public void loggingBefore(JoinPoint joinPoint) {
		log.info("[AOP] BEFORE..." + joinPoint);
	}
	
	@After("execution(* com.example.app.domain.service.MemoServiceImpl.getAllMemo(..) )")
	public void loggingAfter(JoinPoint joinPoint) {
		log.info("[AOP] AFTER..." + joinPoint);
		log.info("[AOP] AFTER..." + joinPoint.getTarget());
		log.info("[AOP] AFTER..." + joinPoint.getSignature());
		log.info("[AOP] AFTER..." + joinPoint.getSignature().getName());
	}
	
	//after & before 한번에
	@Around("execution(* com.example.app.domain.service.*.*(..))")  // .*.* 모든 클래스 안의 메서드
	public Object loggingAround(ProceedingJoinPoint pjp) throws Throwable  //서비스로 넘어가는 함수
	{
		//함수의 호출을 직접 해야함
		//이전시점
		log.info("[AOP] ARUOND BEFORE");
		long startTime = System.currentTimeMillis();
		//MVC 흐름대로 진행
		Object isUpdated = (Object)pjp.proceed();
		//이후시점
		log.info("[AOP] AROUND AFTER");
		long endTime = System.currentTimeMillis();
		log.info("[AOP] TIME : " + (endTime-startTime) + "ms");
		return isUpdated;
	}
}

package com.example.app.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Scheduling {
	
//	@Scheduled(fixedRate = 1000)
//	public void t1 () {
//		System.out.println("Scheduling's t1() invoke..");
//		
//	}
	
//	@Scheduled(cron = "*/3 * * * * *")
//	public void t2 () {
//		System.out.println("Scheduling's t2() invoke..");
//		
//	}
}

//초(0-59) 분(0-59) 시(0-23) 일(1-31) 월(1-12) 요일(0-6, 일=0)

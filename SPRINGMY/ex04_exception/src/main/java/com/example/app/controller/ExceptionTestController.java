package com.example.app.controller;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/except")
public class ExceptionTestController {
	
	
//	@ExceptionHandler(FileNotFoundException.class) //리플렉션 기능을 사용하는 것, public class ExceptionTestController 안에서만 사용됨
//	public String fileNotFoundExceptionHandler(Exception e, Model model) {	 //에러페이지로 이동
//		log.error("error:"+ e); 
//		return "except/error";
//	}
//	
//	@ExceptionHandler(ArithmeticException.class) 
//	public String arithmeticException(Exception e, Model model) {	 
//		log.error("error:"+ e); 
//		return "except/error";
//	}
	
	@ExceptionHandler(Exception.class) 
	public String Exception(Exception e, Model model) {	 
		log.error("error:"+ e); 
		return "except/error";
	}
	
	@GetMapping("/ex")
	public void ex1_1() throws FileNotFoundException {
		log.info("GET/exTEST/page01");
		throw new FileNotFoundException("파일을 찾을 수가 없습니다.");
	}
	
	@GetMapping("/page01")
	public void ex1() throws FileNotFoundException {
		log.info("GET/exTEST/page01");
		throw new FileNotFoundException("파일을 찾을 수가 없습니다.");
	}
	
	@GetMapping("/page02/{num}/{div}")
	public String ex2(   // path 경로를 잡을 때는 이름을 확실하게! void (x) //return "except/page02";
			@PathVariable("num") int num,
			@PathVariable("div") int div,
			Model model
			
			) throws ArithmeticException {
		log.info("GET/exTEST/page02...."+(num/div));
		model.addAttribute("result",(num/div));
	
		return "except/page02";
	}
}

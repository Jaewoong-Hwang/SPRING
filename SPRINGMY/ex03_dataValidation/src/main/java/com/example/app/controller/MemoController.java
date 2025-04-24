package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		
		log.info("MemoController's dataBinder" + webDataBinder);
		webDataBinder.registerCustomEditor(LocalDate.class, "dateTest",new DateTestEditor());
	}
	
	
	
	@GetMapping("/add")
	public void add_get() {
		log.info("GET /memo/add...");
	}
	
	@PostMapping("/add")
	public void add_post(@Valid MemoDto dto,BindingResult bindingResult, Model model) {
		log.info("POST /memo/add..." + dto);
		
		if(bindingResult.hasErrors()) {
//			log.info("유효성 에러 발생:" + bindingResult.getFieldError("id").getDefaultMessage() );
			for(FieldError error: bindingResult.getFieldErrors()) {
				log.info("Error Filed:" + error.getField()+" Error Msg :"+error.getDefaultMessage());
				model.addAttribute(error.getField(),error.getDefaultMessage());
			}
		
		}
	}
	// 필요하면 쓰는 용도
	// static private
	private static class DateTestEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String dateTest) throws IllegalArgumentException {
			log.info("DateTestEditor's setAdText invoke..." + dateTest);
			LocalDate date = null;
			if(dateTest.isEmpty()) {
				 date = LocalDate.now();
			}else {
				//yyyy#MM#dd -> yyyy-MM-dd(LocalDate Formt)
				dateTest=dateTest.replaceAll("#", "-");
				 date = LocalDate.parse(dateTest,DateTimeFormatter.ofPattern("yyyy#MM#dd")); //아웃포맷팅이기 때문에 dateTest.replaceAll("#", "-"); 필요

			} 
				
			
			
			setValue(date);  //바인딩 처리
			
		}
			
		

	}
}

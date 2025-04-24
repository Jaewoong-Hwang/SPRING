package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

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
}

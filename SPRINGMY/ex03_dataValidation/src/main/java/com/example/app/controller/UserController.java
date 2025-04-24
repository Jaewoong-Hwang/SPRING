package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.dto.UserDto;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Builder
//@RequestMapping("/join")
public class UserController {

		@GetMapping("/join")
		public void join() {
			
			log.info("GET/join...");
		}
		
		@PostMapping("/join")
		public void join_post(@Valid UserDto dto, BindingResult bindingResult, Model model	) {
			
			log.info("POST/join..." + dto);
			
			if(bindingResult.hasErrors()) {
				for(FieldError error: bindingResult.getFieldErrors()) {
					log.info("Error Filed:" + error.getField()+" Error Msg :"+error.getDefaultMessage());
					model.addAttribute(error.getField(),error.getDefaultMessage());
				}
				
			}
		}
}

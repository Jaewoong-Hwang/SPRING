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

import com.example.app.domain.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Builder


//@RequestMapping("/join")
public class UserController {
	
	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		
		log.info("MemoController's dataBinder" + webDataBinder);
		webDataBinder.registerCustomEditor(String.class, "phone",new PhoneNumberEditor());
		webDataBinder.registerCustomEditor(LocalDate.class, "birthday",new BirthdayEditor());
	}

	@GetMapping("/join")
	public void join() {

		log.info("GET/join...");
	}

	@PostMapping("/join")
	public void join_post(@Valid UserDto dto, BindingResult bindingResult, Model model) {

		log.info("POST/join..." + dto);

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				log.info("Error Filed:" + error.getField() + " Error Msg :" + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
			}

		}

	}

	private static class BirthdayEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) {
			LocalDate date = null;
			if (text.isEmpty()) {
				date = LocalDate.now();
			} else {

				text = text.replaceAll("~", "-");
				date = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}

			setValue(date);
		}

	}

	private static class PhoneNumberEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String phone) {
			if (phone.isEmpty()) {
				setValue("");
			} else {
				String formatNumber = phone.replace("-", "");

				phone = formatNumber;

			}
			setValue(phone);
			log.info("PhoneNumberEditor's setAdText invoke..." + phone);


		}

	}

}

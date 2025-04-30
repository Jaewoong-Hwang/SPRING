package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.dto.UserDto;
import com.example.app.domain.service.UserServiceImpl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Builder


//@RequestMapping("/join")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		
		log.info("MemoController's dataBinder" + webDataBinder);
		webDataBinder.registerCustomEditor(String.class, "phone",new PhoneNumberEditor());
		webDataBinder.registerCustomEditor(LocalDate.class, "birthday",new BirthdayEditor());
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("GET / login...");
	}
	
	
	
	//1번 방법
//	@GetMapping("/user")
//	public void user(Authentication authentication) {
//		log.info("GET / user..." + authentication); // 로그인 후 사용자의 인증정보 확인
//		log.info("name..."+authentication.getName());
//		log.info("principal...."+authentication.getPrincipal());
//		log.info("authorities...."+authentication.getAuthorities()); //[] 배열형태로 돼 있음
//		log.info("details...."+authentication.getDetails());
//		log.info("credentical..."+authentication.getCredentials());
//	}
	
	//2번 방법
//	@GetMapping("/user")
//	public void user(@AuthenticationPrincipal Principal principal) {
//		log.info("GET / user..." + principal);
//		
//}
	//3번 방법 context에 직접 접근(제일 쓰임)
	@GetMapping("/user")
	public void user(Model model) {
		log.info("GET / user..." );
		
		Authentication authentication = 
		SecurityContextHolder.getContext().getAuthentication();		
		log.info("authentication : " + authentication);
		
		model.addAttribute("auth",authentication); // model 로 
}
	
	@GetMapping("/manager")
	public void manager() {
		log.info("GET / manager...");
	}
	
	@GetMapping("/admin")
	public void admin() {
		log.info("GET / admin...");
	}
	
	
	
	
	@GetMapping("/join")
	public void join() {

		log.info("GET/join...");
	}

	@PostMapping("/join")
	public String join_post(@Valid UserDto dto, BindingResult bindingResult, Model model,RedirectAttributes redirecteAttributes) {

		log.info("POST/join..." + dto);

		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				log.info("Error Filed:" + error.getField() + " Error Msg :" + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
				return "join";  //문제가 생겼을 경우 join 으로
			}
			
		}
		boolean isJoin = userService.userJoin(dto);
		if(isJoin) {
			redirecteAttributes.addFlashAttribute("message","회원가입 완료!"); //flash 는 세션에 저장함
			return "redirect:/login";
		}
		else
			return "join";

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

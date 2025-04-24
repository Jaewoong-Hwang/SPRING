package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class UserController {

		@GetMapping("/join")
		public void join() {
			
			log.info("GET/join...");
		}
		
		@GetMapping("/join")
		public void join_post() {
			
			log.info("POST/join...");
		}
}

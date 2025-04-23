package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

	@GetMapping("/add")
	public void add_get() {
		log.info("GET /memo/add...");
	}
	
	@GetMapping("/add")
	public void add_post() {
		log.info("POST /memo/add...");
	}
}

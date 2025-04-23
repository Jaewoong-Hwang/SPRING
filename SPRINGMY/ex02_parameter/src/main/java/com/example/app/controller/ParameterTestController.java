package com.example.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.domain.dto.PersonDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/param")
public class ParameterTestController {

	@RequestMapping(value = "/p01", method = RequestMethod.GET) // 요청방식을 여러개
	public void p01(@RequestParam(value = "name", required = false) String name) {
		log.info("GET/param/p01..." + name);

	}

	@GetMapping(value = "/p02")
	public void p02(@RequestParam(value = "name", required = true) String name) {
		log.info("GET/param/p02..." + name);

	}

	@PostMapping(value = "/p03") // 폼 양식을 직접 받아서 써야함
	public void p03(@RequestParam(value = "name", required = true) String name) {
		log.info("POST/param/p03..." + name);

	}
	// @RequsetParam : 동기요청 파라미터 처리 / form 기반 전달되는 파라미터 받기
	// @RequestBody : 비동기요청 파라미터 처리 / form / json 등 파라미터 받기

	@PostMapping(value = "/p04") // post 영역에서는 리퀘스트 파람 보다 리퀘스트 바디의 영역이 더 넓다
	public void p04(@RequestBody String name) {
		log.info("POST/param/p04..." + name);
	}

	@RequestMapping(value = "/p05", method = RequestMethod.GET)
	public void p05(@RequestParam(value = "name", defaultValue = "홍길동") String name) {
		log.info("GET/param/p05..." + name);
	}

	@RequestMapping(value = "/p06", method = RequestMethod.GET)
	public void p06(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age,
			@RequestParam(value = "addr") String addr) {
		log.info("GET/param/p06..." + name + " " + age + " " + addr);
	}

	@RequestMapping(value = "/p07", method = RequestMethod.GET)
	public void p07(@ModelAttribute PersonDto dto) {
		log.info("GET/param/p05..." + dto);
	}

	@RequestMapping(value="/p08/{username}/{age}/{addr}",method=RequestMethod.GET)
	public void p08(
			@PathVariable("username") String username,
			@PathVariable int age,
			@PathVariable String addr
				)	
	{
		log.info("GET/param/p08..." + username + " " + age + " " + addr);
	}
	
	@RequestMapping(value = "/p09/{username}/{age}/{addr}", method = RequestMethod.GET)
	public void p09(PersonDto dto)   //@ModelAttribute 생략
	{      
		log.info("GET/param/p05..." + dto);
	}
	
	@GetMapping("/page01")
	public void page01(PersonDto dto, Model model) {		//뷰이동
		log.info("GET/param/page01..." + dto);
		//반환자료형이 void 일때 /WEB-INF/views/param/page01.jsp와 매핑
		
		//파라미터
		//유효성
		//서비스
		//뷰이동 + 데이터 전달(Model객체 - DispatherServlet(Frontcontroller))
		model.addAttribute("dto",dto); // 객체 전달
		model.addAttribute("test", "test1Value");
	}

	@GetMapping("/page02")
	public String page02(PersonDto dto, Model model) {		
		log.info("GET/param/page02..." + dto);
	
		model.addAttribute("dto",dto); 
		model.addAttribute("test", "test2Value");
		
		
		return "param/page01";
		
	
	}
	
	@GetMapping("/page03/{username}/{age}/{addr}")
	public String page03(PersonDto dto, Model model) {		
		log.info("GET/param/page03..." + dto);
	
		model.addAttribute("dto",dto); 
		model.addAttribute("test", "test3Value");
		
		
		return "param/page01";
		
	
	}
	

	@GetMapping("/page04/{username}/{age}/{addr}")
	public ModelAndView page04(PersonDto dto) {		
		log.info("GET/param/page04..." + dto);
		
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("dto", dto);
		modelAndView.setViewName("param/page01");
		
		return modelAndView;
		
	
	}
	
	
	//SERVLET 방식
	@GetMapping("/page05")
	public String page05(HttpServletRequest req, HttpServletResponse resp) {
		log.info("GET/param/page05...");
		String name = req.getParameter("username");
		int age = Integer.parseInt(req.getParameter("age"));
		String addr = req.getParameter("addr");
		log.info(name+" " + age);
		PersonDto dto = new PersonDto(name,age,addr);
		
		HttpSession session = req.getSession();
		log.info("session:" + session);
		
		return "param/page01";
	}
	
}

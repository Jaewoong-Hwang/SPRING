package com.example.app.restController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rest/memo")

public class MemoRestController {
	@Autowired
	private MemoServiceImpl memoService;
	
	//메모확인전체
		@GetMapping(value="/getAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<MemoDto> getAll() {
			log.info("GET /rest/memo/getAll");
			return memoService.getAllMemo();
		}
		
		//메모확인(단건)
		@GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<MemoDto> get(@PathVariable int id) {
			log.info("GET /memo/get... "+id);
		
			MemoDto dto = memoService.getMemo(id);
			log.info("조회된 MemoDto: {}", dto);

			if (dto == null) {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(dto, HttpStatus.OK);
			
		}
		

		//메모쓰기
		@GetMapping("/add_rest_get")	//http://localhost:8091/app/rest/memo/add
		public void add_get(MemoDto dto) {
			log.info("GET /rest/memo/add_rest_get.."+dto);
			memoService.addMemoTx(dto);
		}
		//메모쓰기
		@PostMapping("/post")	//http://localhost:8091/app/rest/memo/post
		public void add(@RequestBody MemoDto dto) throws SQLException {
			log.info("POST /rest/memo/add_rest_post.."+dto);
			memoService.registraionMemo(dto);
		}
//		
//		//메모수정
//		@PutMapping("/put/{id}/{text}")
//		public void put(MemoDto dto) {
//			log.info("PUT /memo/put.."+dto);
//			memoService.modifyMemo(dto);
//		}
	
}

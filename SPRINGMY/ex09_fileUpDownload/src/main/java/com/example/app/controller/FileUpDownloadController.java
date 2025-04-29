package com.example.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.dto.FileDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/file")
public class FileUpDownloadController {

	// upload 경로
	private String ROOT_PATH = "c:";
	private String UPLOAD_PATH = "upload";

	@GetMapping("/upload")
	public void upload_form() {

		log.info("GET/file/upload...");
	}

	@PostMapping("/upload")
	public void upload(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {

		// 원래 service에 만들어줘야 함 // 업로드할 폴더를 만들어줌
		LocalDateTime now = LocalDateTime.now();
		// yyyyMMdd_HHmmss 폴더명
		String folderName = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		// 업로드 경로 설정
		String UploadPath = ROOT_PATH 
				+ File.separator // '/'
				+ UPLOAD_PATH 
				+ File.separator + folderName 
				+ File.separator;
		// 디렉토리 생성( c:\\upload\\20250421_102933\\ )

		File dir = new File(UploadPath);
		if (!dir.exists())
			dir.mkdirs();
		log.info("GET/file/upload..." + files.length);

		for (MultipartFile file : files) {
			System.out.println("--------------------");
			System.out.println("FILE NAME : " + file.getOriginalFilename());
			System.out.println("FILE SIZE : " + file.getSize() + " Byte");
			System.out.println("--------------------");
			String filename = file.getOriginalFilename();
			File fileObject = new File(dir, filename);

			file.transferTo(fileObject); // upload 처리

		}

	}

	@PostMapping("/upload_dto")
	public String upload_dto(FileDto dto) throws IllegalStateException, IOException {
		MultipartFile [] files = dto.getFiles();
		
		// 원래 service에 만들어줘야 함 // 업로드할 폴더를 만들어줌
		LocalDateTime now = LocalDateTime.now();
		// yyyyMMdd_HHmmss 폴더명
		String folderName = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		// 업로드 경로 설정
		String UploadPath = ROOT_PATH 
				+ File.separator // '/'
				+ UPLOAD_PATH 
				+ File.separator 
				+ folderName 
				+ File.separator;
		// 디렉토리 생성( c:\\upload\\20250421_102933\\ )

		File dir = new File(UploadPath);
		if (!dir.exists())
			dir.mkdirs();
		log.info("GET/file/upload..." + files.length);

		for (MultipartFile file : files) {
			System.out.println("--------------------");
			System.out.println("FILE NAME : " + file.getOriginalFilename());
			System.out.println("FILE SIZE : " + file.getSize() + " Byte");
			System.out.println("--------------------");
			String filename = file.getOriginalFilename();
			File fileObject = new File(dir, filename);

			file.transferTo(fileObject); // upload 처리

		}
		return "file/upload";

	}
	@GetMapping("/list")
	public void list(Model model) {
		log.info("GET/file/list...");
		String UploadPath = ROOT_PATH 
				+ File.separator // '/'
				+ UPLOAD_PATH 
				+ File.separator;
		File uploadDir = new File(UploadPath);
		File [] dirs =  uploadDir.listFiles();
		for(File dir : dirs) {
			
			System.out.println("dir : " + dir);//	file list 확인
			
			File subDir = new File(dir.getPath()); 
			for(File file : subDir.listFiles()) {
				System.out.println("FILE" + file); // 폴더 안의 file list 출력
			}
				
		}
		model.addAttribute("uploadPath", new File(UploadPath));
		
	}
	
	@GetMapping(value="/download" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam("path")String path) throws UnsupportedEncodingException {
		log.info("RestController_03Download's Download Call.." + path);
		//FileSystemResource : 파일시스템의 특정 파일로부터 정보를 가져오는데 사용
		Resource resource = new FileSystemResource(path);
		//파일명 추출
		String filename = resource.getFilename();
		//헤더 정보 추가
		HttpHeaders headers = new HttpHeaders();
		//ISO-8859-1 : 라틴어(특수문자등 깨짐 방지)
		headers.add("Content-Disposition","attachment; filename="+new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
		//리소스,파일정보가 포함된 헤더,상태정보를 전달
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		
	}
}

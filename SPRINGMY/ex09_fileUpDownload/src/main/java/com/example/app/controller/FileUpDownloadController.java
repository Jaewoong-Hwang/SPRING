package com.example.app.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}

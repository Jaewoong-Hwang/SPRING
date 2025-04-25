package com.example.app.domain.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private String userid;		//유저ID
	@NotBlank(message="password 를 입력하세요")
	private String password;	//패스워드
	@NotBlank(message="rePassword 를 입력하세요")
	private String rePassword;	//패스워드확인
	@NotBlank(message="username 를 입력하세요")
	private String username;	//유저이름
	@NotBlank(message="phone 를 입력하세요")
	private String phone;		//전화번호
	@NotBlank(message="zipcode 를 입력하세요")
	private String zipcode;		//우편번호
	@NotBlank(message="addr1 를 입력하세요")
	private String addr1;		//기본주소
	@NotBlank(message="addr2 를 입력하세요")
	private String addr2;		//상세주소
	
	@NotNull(message="birthday 를 입력하세요")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;	//생년월일(yyyy-MM-dd)
}

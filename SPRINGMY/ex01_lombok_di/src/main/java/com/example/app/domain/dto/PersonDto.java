package com.example.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
//@Data //모든 것들을 만들어 줌
@AllArgsConstructor //모든 파라미터를 받는 생성자
@NoArgsConstructor //디폴트 생성자
public class PersonDto {
	private String name;
	private int age;
	private String addr;
	
}

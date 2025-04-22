package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.domain.dto.PersonDto;

@Configuration
public class PersonDtoBeanConfig {
	
	@Bean
	public PersonDto pseron03() {
		
		return PersonDto.builder()
				.age(55)
				.name("철수")
				.addr("울산")
				.build();
		
		//return new PersonDto("김범수",44,"서울");
	}
	@Bean(name = "personBean")
	public PersonDto pseron04() {
		
		return new PersonDto("박효신",40,"대구");
	}
}

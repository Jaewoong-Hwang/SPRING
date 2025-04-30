package com.example.app.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.domain.dto.UserDto;
import com.example.app.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service	
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired  //usermapper 연결
	private UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DB로 부터 내용을 꺼내서 전달해주는 역할을 함!! 지금은 인증을 하는 게 아니라 맡기는 역할을 하는 것임
		//loadUserByUsername 는 login 에서 아이디(usernmae) 을 받아옴 
		UserDto userDto = userMapper.selectAt(username);
		
		//계정이 없을 수도 있음...
		if(userDto==null) {
			throw new UsernameNotFoundException(username + "존재하지 않는 계정입니다."); 
		}
		
		return new PrincipalDetails(userDto); // 전달
	}

}

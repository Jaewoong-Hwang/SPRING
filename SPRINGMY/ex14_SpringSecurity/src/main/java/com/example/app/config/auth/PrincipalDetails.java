package com.example.app.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.domain.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails{
	private UserDto userDto;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //권한 체크 
		Collection <GrantedAuthority> authorities = new ArrayList(); //GrantedAuthority 를 상속관계, 그리고 Collection에 있는 SimpleGrantedAuthority 룰 사용 
//		new SimpleGrantedAuthority(userDto.getRole());
//		authorities.add(userDto.getRole()); -> 이렇게 하면 안됨!!! 문자열이기 때문에
		authorities.add(new SimpleGrantedAuthority(userDto.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userDto.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userDto.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //계정 만료 여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정 락 여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 계정 만료 여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { 
		// TODO Auto-generated method stub
		return true;
	}
	
}

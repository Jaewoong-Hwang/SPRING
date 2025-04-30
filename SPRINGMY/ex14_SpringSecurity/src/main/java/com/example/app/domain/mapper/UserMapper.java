package com.example.app.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.dto.UserDto;

@Mapper
public interface UserMapper {

	@Insert("insert into tbl_user values(#{username},#{password},#{role})")
	public int insert(UserDto uiserDto);
	}

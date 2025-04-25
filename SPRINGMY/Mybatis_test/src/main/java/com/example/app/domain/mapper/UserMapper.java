package com.example.app.domain.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.dto.UserDto;


@Mapper
public interface UserMapper {
	@Insert("insert into tbl_user values(#{userid},#{username},#{password}")
	public int insert(UserDto dto);
		
	
	@Update("update tbl_memo set text=#{text} where userid=#{id}")
	public int update(UserDto dto);

	
	@Delete("delete from tbl_user where userid=#{id}")
	public int delete(String string);
	
}

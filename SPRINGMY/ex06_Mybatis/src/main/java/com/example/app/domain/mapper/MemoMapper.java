package com.example.app.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.dto.MemoDto;

@Mapper
public interface MemoMapper {

	@Insert(value = "insert into tbl_memo values(#{id},#{text},#{writer},#{createAt})")
	public int insert(MemoDto memoDto);
	
	
}

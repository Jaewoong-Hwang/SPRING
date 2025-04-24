package com.example.app.domain.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.domain.dao.MemoDaoImpl;
import com.example.app.domain.dto.MemoDto;

@Service
public class MemoServiceImpl {
	@Autowired
	private MemoDaoImpl memoDaoImpl;
	
	public boolean registraionMemo(MemoDto memoDto) throws SQLException{
		
		int result=memoDaoImpl.insert(memoDto);;
		return result>0;
	}
}

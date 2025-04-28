package com.example.app.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.example.app.domain.dto.MemoDto;

@Mapper
public interface MemoMapper {

	@SelectKey(statement="select max(id)+1 from tbl_memo",keyProperty = "id",before = false, resultType = int.class)
	@Insert("insert into tbl_memo values(#{id},#{text},#{writer},#{createAt})")
	public int insert(MemoDto dto);
	
	
	@Update("update tbl_memo set text=#{text} where id=#{id}")
	public int update(MemoDto dto);
	
	@Delete("delete from tbl_memo where id=#{id}")
	public int delete(int id);
	
	@Select("select * from tbl_memo where id=#{id}")
	public MemoDto selectAt(int id);
	
	
	@Select("select * from tbl_memo")
	public List<MemoDto> selectAll(); 
	
	@Results(id="MemoResultMap", value= {
			@Result(property = "id",column="id"),
			@Result(property = "text", column="text")
	})
	@Select("select * from tbl_memo")
	public List< Map<String,Object> > selectAllResultMap(); 	
	
	//XML 방식
	public int insertXml(MemoDto memoDto);
	public int updateXml(MemoDto memoDto);
	public int deleteXml(@Param("id")int id); //다른 파라미터를 받을 수도 있기 때문에 @Param
	public MemoDto selectAtXml(int id);
	public List<MemoDto> selectAllXml(); 
	public List< Map<String,Object> > selectAllResultMapXml(); 
	
	
	//동적쿼리
	public List< Map<String,Object> > Select_if_xml( Map<String,Object> param);
	public List< Map<String,Object> > Select_when_xml( Map<String,Object> param);
	
}

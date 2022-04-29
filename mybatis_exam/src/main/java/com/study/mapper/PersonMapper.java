package com.study.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.study.dto.PersonDTO;

public interface PersonMapper {
	// #{id},#{name}" ==> ? 변경 // mybatis에서는 #으로 설정
	// insert into person(id,name) values(?,?)

//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insertPerson(@Param("id") String id,@Param("name") String name);
	
	// 이 방식의 단점
	// SQL구문이 복잡하면 쓰기 어려움
	
	public int insert(@Param("id") String id,@Param("name") String name);
	public int update(@Param("id") String id,@Param("name") String name);
	public int delete(String id);
	public PersonDTO select(String id);
}

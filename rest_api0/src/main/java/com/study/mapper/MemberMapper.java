package com.study.mapper;

import org.apache.ibatis.annotations.Param;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;

public interface MemberMapper {
	//CRUD 메소드 정의
	
	//inCert (C)-회원가입
	public int insert(MemberDTO register);
	//R
	public AuthDTO login(@Param("userid") String userid,@Param("password") String password);
	//R
	public String dupId(String userid);
	//U 작업 - changePwd
	public int update(ChangeDTO changeDto);

	//D 작업
	public int delete(@Param("userid") String userid,@Param("password") String password);
}

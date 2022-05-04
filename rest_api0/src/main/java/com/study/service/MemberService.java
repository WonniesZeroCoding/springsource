package com.study.service;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;

public interface MemberService {
	//회원 가입
	public boolean register(MemberDTO register);
	//로그인
	public AuthDTO login(String userid,String password);
	//한사람만 나오니까 DTO 소환, 다 가지고 나올땐? List로
	public boolean changePwd(ChangeDTO changeDto);

	//삭제(탈퇴)
	public boolean leave(String userid, String password);
	//아이디 중복
	public String dupId(String userid);
}

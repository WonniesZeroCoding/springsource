package com.study.mapper;

import java.util.List;

import com.study.dto.AttachDTO;

public interface AttachMapper {
	//첨부 파일 삽입
	public int insert(AttachDTO attach);
	//첨부 파일 목록 가져오기, 여러개로 가져올 수 있으니까 List로 가져오기
	public List<AttachDTO> list(int bno);
	//첨부 파일 전체 삭제
	public int deleteAll(int bno);
	//어제 날짜의 첨부파일 목록
	public List<AttachDTO> getOldFiles();
}

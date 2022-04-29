package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.mapper.BoardMapper;

@Service("service")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		
		return mapper.select(cri);
	}

	@Override
	public boolean insert(BoardDTO insertDto) {
		return mapper.insert(insertDto)==1?true:false;
	}

	@Override
	public BoardDTO getRow(int bno) {
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardDTO modifyDto) {
		return mapper.modify(modifyDto)==1?true:false;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.remove(bno)==1?true:false;
	}

	@Override
	public int getTotalCnt() {
		
		return mapper.totalCnt();
	}

}
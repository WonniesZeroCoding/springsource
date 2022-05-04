package com.study.mapper;

import java.util.List;

import com.study.dto.BoardDTO;
import com.study.dto.Criteria;

// mapper interface의 () 안에는 #들어간 애들 가져오면 돼!
public interface BoardMapper {
	public List<BoardDTO> select(Criteria cri);
	public int insert(BoardDTO insertDto);
	public BoardDTO read(int bno);
	public int modify(BoardDTO modifyDto);
	public int remove(int bno);
	public int totalCnt(Criteria cri);
}

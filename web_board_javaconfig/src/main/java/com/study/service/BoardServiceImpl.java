package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.AttachDTO;
import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.mapper.AttachMapper;
import com.study.mapper.BoardMapper;
import com.study.mapper.ReplyMapper;

@Service("service")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		
		return mapper.select(cri);
	}
	
	@Transactional	// 글이 2개가 돌아갈 때만 넣어야하기 때문에 Transactional 걸어야함
	@Override
	public void insert(BoardDTO insertDto) {
		
		//새글 등록 -- bno가 먼저 생겨야 함
		mapper.insert(insertDto);
		
		//첨부 파일 삽입
		//먼저, 첨부파일이 없다면 되돌려 보내기
		if(insertDto.getAttachList()== null || insertDto.getAttachList().size() <= 0) {
			return;
		}
		
		//첨부 파일 개수 만큼 루프 돌기
		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			//첨부파일 삽입
			attachMapper.insert(attach);
		});
	
		
	}

	@Override
	public BoardDTO getRow(int bno) {
		
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public boolean modify(BoardDTO modifyDto) {
		
		//① 기존 첨부파일 삭제
		attachMapper.deleteAll(modifyDto.getBno());
		
		// 첨부 파일 새로 삽입
		if(modifyDto.getAttachList() != null && modifyDto.getAttachList().size() > 0) {
			for(AttachDTO attach:modifyDto.getAttachList()) {
				attach.setBno(modifyDto.getBno());
				attachMapper.insert(attach);
			}
		}
		
		return mapper.modify(modifyDto)==1?true:false;
	}

	@Transactional
	@Override
	public boolean remove(int bno) {
		
		//첨부물 삭제
		attachMapper.deleteAll(bno);
		//댓글 삭제
		replyMapper.deleteAll(bno);
		
		return mapper.remove(bno)==1?true:false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachDTO> attachList(int bno) {
		
		return attachMapper.list(bno);
	}

}

package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.BookMapper;
import com.study.dto.BookDTO;

@Service("service")   //객체 생성 ():이름부여
public class BookServiceImpl implements BookService {

	@Autowired // 생성된 객체 주입
	private BookMapper mapper; // 객체 생성 필요
	
	@Override
	public List<BookDTO> getList() {
		
		return mapper.select();
	}

	@Override
	public boolean bookInsert(BookDTO insertDto) {
		
		return mapper.insert(insertDto)==1?true:false;
		// 의미:dao.insert(insertDto)를 호출, 이 값이 1이면 true , 그게 아니라면 false;
	}

	@Override
	public boolean bookUpdate(int code, int price) {		
		return mapper.update(code, price)==1?true:false;
	}

	@Override
	public boolean bookDelete(int code) {		
		return mapper.delete(code)==1?true:false;
	}

	@Override
	public List<BookDTO> searchList(String criteria, String keyword) {
		
		return mapper.search(criteria, keyword);
	}

	@Override
	public BookDTO getRow(int code) {
		return mapper.get(code);
	}

}

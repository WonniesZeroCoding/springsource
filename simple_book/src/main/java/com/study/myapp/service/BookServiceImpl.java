package com.study.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.myapp.dao.BookDAO;
import com.study.myapp.dto.BookDTO;

@Service("service")   //객체 생성 ():이름부여
public class BookServiceImpl implements BookService {

	@Autowired // 생성된 객체 주입
	
	private BookDAO dao; // 객체 생성 필요
	
	@Override
	public List<BookDTO> getList() {
		
		return dao.select();
	}

	@Override
	public boolean bookInsert(BookDTO insertDto) {
		
		return dao.insert(insertDto)==1?true:false;
		// 의미:dao.insert(insertDto)를 호출, 이 값이 1이면 true , 그게 아니라면 false;
	}

}

package com.study.myapp.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.myapp.dto.BookDTO;

@Repository  // 객체 생성 -- 원래 생성자 만들거나 setter 만들거나 했던 작업을 안할려고 어노테이션 사용
public class BookDAO {

		@Autowired
		private JdbcTemplate jdbcTemplate; // config.xml에서 객체 생성해서 사용
	
		public List<BookDTO> select() {
		
			String sql = "select * from booktbl";
					
			
			return jdbcTemplate.query(sql, new BookRowMapper());
			//jdbcTemplate 안에 query라는 메소드가 정의가 되어 있음
			//query(sql문 실행, 실행한 결과를 담을 RowMapper를 구현한 클래스)  
		}

		// 결과 값이 int로 넘어오는 애들
		public int insert(BookDTO insertDto) {
			
			String sql = "insert into booktbl values(?,?,?,?)";
			
				
				int result = jdbcTemplate.update(sql, insertDto.getCode(), insertDto.getTitle(),insertDto.getWriter(), insertDto.getPrice());
				// .update(sql문 실행, ? 안에 담을 값들)  

			return result;
		}
		public int update(int code, int price) {
			
			String sql = "update booktbl set price = ? where code=?";
			
				
				int result = jdbcTemplate.update(sql, price, code);
				// .update(sql문 실행, ? 안에 담을 값들)  

			return result;
		}
		public int delete(int code) {
			
			String sql = "delete from booktbl where code=?";
			
				
				int result = jdbcTemplate.update(sql, code);
				// .update(sql문 실행, ? 안에 담을 값)  

			return result;
		}
}

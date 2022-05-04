package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.dto.BookDTO;
import com.study.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	
	@GetMapping("/index")
	public String insert() {
		return "/book/book_test";
	}
	
	@GetMapping(path = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> list() {
		List<BookDTO> list = service.getList();
		
		return new ResponseEntity<List<BookDTO>>(list, HttpStatus.OK);
		
	}
	
	// /book/1000 + get
	
	@GetMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> get(@PathVariable("code") int code) {
		return new ResponseEntity<BookDTO>(service.getRow(code),HttpStatus.OK);
	}
	
	// /book/1000 + delete --> 성공하면 success 문자 보내기 , 실패 시 Fail 문자 보내기 + add.request
	
	@DeleteMapping(path = "/{code}")
	public ResponseEntity<String> delete(@PathVariable("code") int code) { //문자 보내기할거니까 entity, 보낼 타입이 문자열이니까 String
		
		return service.bookDelete(code)? new ResponseEntity<String>("success", HttpStatus.OK) :
			new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	
	}
	
	// /book/1000 + PUT + body(수정내용 - json 형태)
	@PutMapping(path = "/{code}")
	public ResponseEntity<String> update(@PathVariable("code") int code,@RequestBody BookDTO dto) {
		return service.bookUpdate(code, dto.getPrice())? new ResponseEntity<String>("success", HttpStatus.OK) :
			new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// /book/new + body ==> 신규 도서 입력
	@PostMapping("/new")
	public ResponseEntity<String> insert(@RequestBody BookDTO insertDto){
		return service.bookInsert(insertDto)?new ResponseEntity<String>("success", HttpStatus.OK) :
			new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 비동기식으로 클라이언트로 부터 데이터를 가져올 때, 객체에 담고자 한다면?
	// insert안의 @RequestBody가 붙었는지 안붙었는지, 위의 JSON과 비동기식의 차이
	// 데이터를 뭘로 넘길 것이냐에 따라 받는 방식이 달라진다
	@PostMapping("/new2")
	public ResponseEntity<String> insert2(BookDTO insertDto){
		
		log.info("입력 "+insertDto);
		
		return service.bookInsert(insertDto)?new ResponseEntity<String>("success", HttpStatus.OK) :
			new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}

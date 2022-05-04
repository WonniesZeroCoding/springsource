package com.study.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.SampleDTO;

@RestController // 여기서 리턴하는 모든 것들은 데이터 그 자체
public class BasicController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
				
	}
	
	@GetMapping(path = "/send", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
	public SampleDTO sendDTO() {
		SampleDTO dto = new SampleDTO();
		dto.setName("hong");
		dto.setBno("123");
		dto.setAddr("서울");
		
		return dto;
	}
}

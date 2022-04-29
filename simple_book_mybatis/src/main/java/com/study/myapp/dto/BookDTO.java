package com.study.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// DTO --> Spring이 관리하지 않음. 필요할 때마다 만들 예정
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter


public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
}

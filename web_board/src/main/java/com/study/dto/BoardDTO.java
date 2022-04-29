package com.study.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer;	
	private Date regdate;
	private Date updatedate;
}

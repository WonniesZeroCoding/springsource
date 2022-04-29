package com.study.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ItemDTO {
	private int num;
	private String category;
	private String name;
	private String content;
	private String psize;
	private int price;
	private Date registerAt;

}

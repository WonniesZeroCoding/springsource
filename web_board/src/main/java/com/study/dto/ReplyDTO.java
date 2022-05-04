package com.study.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {
	private int rno;
	private int bno;
	private String reply;
	private String replyer;
	private Date replydate;
	private Date updatedate;
}

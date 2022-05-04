package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
	private String userid;
	private String password;
	private String confirm_password;
	private String name;
	private String gender;
	private String email;
}

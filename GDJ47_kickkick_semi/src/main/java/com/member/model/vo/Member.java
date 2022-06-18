package com.member.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	private String email;
	private String password;
	private String name;
	private String phone;
	private Date birthday;
	private String gender;
	private String address;
	private String type;
	private String deleteStatus;
}

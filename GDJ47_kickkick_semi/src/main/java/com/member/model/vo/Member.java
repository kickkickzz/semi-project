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
	private Date birthday;
	private String phone;
	private String address;
	private String memberWithdrawalStatus;
	private String kakaoMembership;
	private String gender;
	private String type;
}

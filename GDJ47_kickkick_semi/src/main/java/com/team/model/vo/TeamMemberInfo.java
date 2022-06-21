package com.team.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamMemberInfo {

	private String supporter_email;
	private String support_team;
	private String position;
	private String application_status;
	private String delete_status;
	private String email;
	private String password;
	private String name;
	private String phone;
	private Date birthday;
	private String gender;
	private String type;
	private String deletestatus;
	private String address;
	
	
	
	

}

	


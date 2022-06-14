package com.team.model.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class Team {
	private String team_code;
	private String team_leader;
	private String team_name;
	private String team_gender;
	private int team_age;
	private String team_mark_png;
	private int team_point;
	private String team_region;
	private Date team_active_lastday;
	private String team_delete_status;
}

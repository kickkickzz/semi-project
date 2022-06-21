package com.team.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Team {
	private String team_code;
	private String team_leader;
	private int team_num;
	private String team_name;
	private String team_gender;
	private String team_age;
	private String team_mark_img;
	private int team_point;
	private String team_region;
	private Date team_active_lastday;
	private String team_delete_status;

}

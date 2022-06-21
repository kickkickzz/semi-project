package com.match.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Match {
	private int regist_num;
	private String regist_team;
	private String regist_reservation_code;
	private String regist_status;
	private String regist_branch_num;
	private int regist_stadium_num;
	private String team_code;
	private String team_leader;
	private int team_num;
	private String team_name;
	private String team_gender;
	private String team_age;
	private String team_region;
	private int team_point;
	private String team_mark_img;
	private Date team_active_lastday;
	private String team_delete_status;
	private String reservation_code;
	private String reservation_email;
	private String reservation_branch_num;
	private int reservation_stadium_num;
	private int reservation_num;
	private int reservation_price; 
	private int reservation_usage_start_time; 
	private int reservation_usage_time;
	private int reservation_usage_end_time; 
	private Date reservation_usage_start_date; 
	private String reservation_status; 
	private String branch_num;
	private String branch_manager_email;
	private String branch_address;
	private String branch_phone;
	private String branch_img;
	private String branch_website;
	private int branch_point;
	private String branch_option_shower;
	private String branch_option_park;
	private String branch_option_uniform;
	private String branch_option_shoes;
	private String branch_option_ball;
	private String branch_option_inout;
	private String branch_delete_status;
	private int stadium_num; 
	private String stadium_branch_num; 
	private String stadium_name; 
	private String stadium_match_member; 
	private int stadium_reservation_start_time; 
	private int stadium_reservation_end_time; 
	private String stadium_delete_status;
}

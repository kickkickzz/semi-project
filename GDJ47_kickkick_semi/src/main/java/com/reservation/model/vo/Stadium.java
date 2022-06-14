package com.reservation.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stadium {
	private int stadium_num;
	private String branch_num;
	private String stadium_name;
	private String stadium_match_member;
	private int stadium_reservation_start_time;
	private int stadium_reservation_end_time;
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
}

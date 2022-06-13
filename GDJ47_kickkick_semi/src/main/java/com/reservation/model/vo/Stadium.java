package com.reservation.model.vo;

import lombok.Data;

@Data
public class Stadium {
	private int stadium_num;
	private String branch_num;
	private String stadium_name;
	private String stadium_match_member;
	private int stadium_reservation_start_time;
	private int stadium_reservation_end_time;
}

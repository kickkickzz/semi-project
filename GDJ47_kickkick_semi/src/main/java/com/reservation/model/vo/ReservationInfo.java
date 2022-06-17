package com.reservation.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ReservationInfo {
	private String reservation_code;
	private String reservation_email; 
	private String reservation_branch_num;
	private int reservation_stadium_num;
	private int reservation_num;
	private int reservation_price; 
	private int reservation_usage_start_time; 
	private int reservation_usage_time;
	private int reservation_usage_end_time; 
	private String reservation_usage_start_date; 
	private String reservation_status; 
	private int stadium_num; 
	private String stadium_branch_num; 
	private String stadium_name; 
	private String stadium_match_member; 
	private int stadium_reservation_start_time; 
	private int stadium_reservation_end_time; 
	private String stadium_delete_status;
	private String branch_manager_email;
}

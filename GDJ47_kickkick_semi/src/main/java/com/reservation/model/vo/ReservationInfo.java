package com.reservation.model.vo;

import java.sql.Date;

import lombok.Data;
@Data
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
	private Date reservation_usage_start_date; 
	private String reservation_status; 
}

package com.reservation.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class PayHistory {
	private String paycode;
	private String email;
	private String reservation_code;
	private String stadium_branch_num;
	private String paymethod;
	private Date paydate;
	private int starttime;
	private int endtime;
	private String name;
	
}

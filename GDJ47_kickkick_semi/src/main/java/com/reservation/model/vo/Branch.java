package com.reservation.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {
	private String branch_num;
	private String branch_manager_email;
	private String branch_address;
	private String branch_phone;
	private String branch_img;
	private String branch_website;
	private String branch_Info;
	private String detail_Info;
	private String notes;
	private int branch_point;
	private String branch_option_shower;
	private String branch_option_park;
	private String branch_option_uniform;
	private String branch_option_shoes;
	private String branch_option_ball;
	private String branch_option_inout;
	private String branch_delete_status;
}

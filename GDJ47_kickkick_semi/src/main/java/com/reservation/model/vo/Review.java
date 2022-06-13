package com.reservation.model.vo;

import lombok.Data;

@Data

public class Review {
	private int review_num;
	private String review_email;
	private String review_branch_num;
	private String review_content; 
	private int review_point;
}

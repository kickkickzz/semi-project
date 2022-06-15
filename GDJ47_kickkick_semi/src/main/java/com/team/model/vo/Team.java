package com.team.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Team {
	private String teamCode;
	private String teamLeader;
	private String teamName;
	private String teamGender;
	private int teamAge;
	private String teamMarkPng;
	private int teamPoint;
	private String teamRegion;
	private Date teamActiveLastday;
	private String teamDeleteStatus;
}

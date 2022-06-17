package com.team.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamMember {

	
	
	String supporter_email;
	String support_team;
	String position;
	String application_status;
	String delete_status;
}

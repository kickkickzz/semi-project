package com.team.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import com.team.model.dao.TeamDao;
import com.team.model.vo.Team;
import com.team.model.vo.TeamMember;
import com.team.model.vo.TeamMemberInfo;

public class TeamService {
	
	private TeamDao dao=new TeamDao();
	
	
	
	public Team selectTeamByName(String team_name) {
		Connection conn=getConnection();
		Team t=dao.selectTeamByName(conn, team_name);
		
		close(conn);
		return t;
		
	}
	public List<Team> selectTeamList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Team> result=dao.selectTeamList(conn,cPage,numPerpage);
		close(conn);
		return result;
		
	}
	
	
	public int selectTeamCount(){
		Connection conn=getConnection();
		int result=dao.selectTeamCount(conn);
		close(conn);
		return result;
	}
	
	
	public int teamRegist(Team t) {
		Connection conn = getConnection();
		int result = dao.teamEnrollment(conn, t);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	

	public List<Team> searchTeamList(String type, String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Team> result=dao.searchTeamList(conn,type,keyword,cPage,numPerpage);
		close(conn);
		return result;
	}
	public int searchTeamCount(String type, String keyword) {
		Connection conn=getConnection();
		int result=dao.searchTeamCount(conn,type,keyword);
		close(conn);
		return result;
	}
	
	public Team selectTeam(String team_code) {
		Connection conn=getConnection();
		Team t= dao.selectTeam(conn, team_code);
		close(conn);
		return t;
	}
	
	public ArrayList<TeamMemberInfo> selectTeamMemberList(String team_code){
		Connection conn=getConnection();
		ArrayList<TeamMemberInfo> result=dao.selectTeamMemberList(conn,team_code);
		close(conn);
		return result;
	}
	
	public int teamRegistCheck(String userId) {
		Connection conn = getConnection();
		int result = dao.getTeamRegistCheck(conn, userId);
		close(conn);
		return result;
	}
	
	public int teamCodeCheck(String team_code) {
		Connection conn = getConnection();
		int result = dao.getTeamCodeCheck(conn, team_code);
		close(conn);
		return result;
	}
		
	public int teamMemberRegistCheckNum(String userId) {
		Connection conn = getConnection();

		int result = dao.teamMemberRegistCheckNum(conn, userId);

		close(conn);

		return result;
	}
	
	public int teamMemberRegistCheck(String userId, String team_code) {
		Connection conn = getConnection();

		int result = new TeamDao().getTeamMemberRegistCheck(conn, userId, team_code);

		close(conn);

		return result;
	}
	public TeamMember teamMemberApplicationInfo(String userId, String team_code) {
		Connection conn = getConnection();

		TeamMember teamMember = new TeamDao().getTeamMemberApplicationInfo(conn, userId, team_code);

		close(conn);

		return teamMember;
	}

	public void teamMemberReApplication(String userId, String team_code) {
		Connection conn = getConnection();

		int result = new TeamDao().teamMemberReApplication(conn, userId, team_code);

		if (result > 0) {
			commit(conn);
		}

		close(conn);

	}

	public int teamMemberApplication(String userId, String team_code, String position) {
		Connection conn = getConnection();

		int result = new TeamDao().teamMemberApplication(conn, userId, team_code, position);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;

	}
	
	public int teamRegistNameCheck(String team_name) {
		Connection conn = getConnection();

		int result = new TeamDao().teamRegistNameCheck(conn, team_name);

		close(conn);

		return result;

	}
	

	public int getTeamApplicationCount(String team_code) {
		Connection conn=getConnection();
		int result=dao.getTeamApplicationCount(conn,team_code);
		close(conn);
		return result;
	}
	
	public ArrayList<TeamMemberInfo> selectTeamApplication(String team_code){
		Connection conn=getConnection();
		ArrayList<TeamMemberInfo> list=dao.selectTeamApplication(conn,team_code);
		close(conn);
		return list;
	}
	
	
	
	
	
	public void teamExpulsion(String supporter, String team_code) {
		Connection conn=getConnection();
		int result=dao.teamExpulsion(conn, supporter, team_code);
		if(result>0) commit(conn);
		close(conn);
	}
	
	
	public void teamAccept(String supporter, String team_code) {
		Connection conn=getConnection();
		int result=new TeamDao().teamAccept(conn, supporter, team_code);
		if(result>0) commit(conn);
		close(conn);
	}
	public void teamCancel(String supporter, String team_code) {
		Connection conn=getConnection();
		int result=new TeamDao().teamCancel(conn, supporter, team_code);
		if(result>0) commit(conn);
		close(conn);
	}
}

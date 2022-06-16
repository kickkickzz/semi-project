package com.team.model.service;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import com.team.model.dao.TeamDao;
import com.team.model.vo.Team;

public class TeamService {
	
	private TeamDao dao=new TeamDao();
	
	/*
	 * public List<Team> searchAll(int cPage, int numPerpage){ Connection
	 * conn=getConnection(); List<Team>
	 * list=dao.selectTeamList(conn,cPage,numPerpage); close(conn); return list;
	 * 
	 * } public int selectTeamCount() { Connection conn=getConnection(); int
	 * result=dao.selectTeamCount(conn); close(conn); return result; }
	 */
	
	public Team selectTeamByName(String team_name) {
		Connection conn=getConnection();
		Team t=dao.selectTeamByName(conn, team_name);
		close(conn);
		return t;
	}
	public List<Team> selectTeamList(){
		Connection conn=getConnection();
		List<Team> list=dao.selectTeamList(conn);
		close(conn);
		return list;
	}
	
	public void teamRegist(Team team) {
		Connection conn = getConnection();
		int result = dao.teamEnrollment(conn, team);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}
	
	

		

	
	


}

package com.team.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.match.model.vo.Match;
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
	
	//메인에 뿌릴 팀 정보 4개
	public List<Team> fourTeam(){
		Connection conn = getConnection();
		List<Team> result = new TeamDao().fourTeam(conn);
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
	
	public int teamMatchStatusCheck(String match_regist_num) {
		Connection conn = getConnection();

		int result = dao.teamMatchStatusCheck(conn, match_regist_num);

		close(conn);

		return result;
	}

	public int teamMatchAcStatus(String match_regist_num, String team_code) {
		Connection conn = getConnection();

		int result = dao.teamMatchAcStatus(conn, match_regist_num, team_code);

		if (result > 0) {
			commit(conn);
		}

		close(conn);
		return result;

		
	}

	public int teamMatchAccept(String match_regist_num, String team_code, String winlose) {
		Connection conn = getConnection();

		int result =dao.teamMatchAccept(conn, match_regist_num, team_code, winlose);

		if (result > 0) {
			commit(conn);
		}

		close(conn);
		return result;

	}
	public void teamMatchCaStatus(String match_regist_num, String team_code) {
		Connection conn = getConnection();

		int result =dao.teamMatchCaStatus(conn, match_regist_num, team_code);

		if (result > 0) {
			commit(conn);
		}

		close(conn);
	}

	public void teamMatchCancel(String match_regist_num, String team_code) {
		Connection conn = getConnection();

		int result = dao.teamMatchCancel(conn, match_regist_num, team_code);

		if (result > 0) {
			commit(conn);
		}

		close(conn);
	}
	
	public ArrayList<Match> selectMatch(String team_code) {
		Connection conn=getConnection();
		ArrayList<Match> m= dao.selectMatch(conn, team_code);
		close(conn);
		return m;
	}
	
	
	
	//로그인한 유저가 가입한 팀 정보보기
	public List<Team> joinTeam(String email){
		Connection conn = getConnection();
		List<Team> result = new TeamDao().joinTeam(conn,email);
		close(conn);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int deleteTeammember(String email) {
		Connection conn=getConnection();
		int result=dao.deleteTeammember(conn,email);
		close(conn);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Team teamLeader(String email) {
		Connection conn = getConnection();
		Team result = dao.teamLeader(conn, email);
		close(conn);
		return result;
	}
	
	
	
	
	
	
	public int teamUpdate(String email,String teamMark, String name, String teamAge,String teamGender,String region) {
		Connection conn = getConnection();
		int result = dao.teamUpdate(conn, email,teamMark,name,teamAge,teamGender,region);
		if(result>0) {
			close(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
}

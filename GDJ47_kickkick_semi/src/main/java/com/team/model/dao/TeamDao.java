package com.team.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.team.model.vo.Team;
import com.team.model.vo.TeamMemberInfo;

public class TeamDao {
	private Properties prop=new Properties();
	
	public TeamDao() {
		String path=TeamDao.class.getResource("/sql/team_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<Team> searchTeamList(Connection conn, String type, String  keyword,
				int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Team> result=new ArrayList();
		String sql=prop.getProperty("searchTeamList");
		sql=sql.replace("$COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, type.equals("team_name")?"%"+keyword+"%":keyword);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(TeamDao.getTeam(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int searchTeamCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchTeamCount").replace("$COL",type);
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, type.equals("team_name")?"%"+keyword+"%":keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	public List<Team> selectTeamList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Team> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(TeamDao.getTeam(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public int selectTeamCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public Team selectTeam(Connection conn, String team_code) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Team t=null;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeam"));
			pstmt.setString(1, team_code);
			rs=pstmt.executeQuery();
			if(rs.next()) t=(TeamDao.getTeam(rs));
				
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return t;
	}
	
	public ArrayList<TeamMemberInfo> selectTeamMemberList(Connection conn,String team_code, int cPage, int numPerpage) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<TeamMemberInfo> teamMemberArr = null;
		TeamMemberInfo TeamMemberInfo = null;
		
		
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamMemberList"));
			pstmt.setString(1, team_code);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			
			teamMemberArr=new ArrayList<TeamMemberInfo>();
			
			while(rs.next()) {
				TeamMemberInfo=TeamDao.getTeamMemberInfo(rs);
						
				
				teamMemberArr.add(TeamMemberInfo);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return teamMemberArr;
		
	}
	
	public int selectTeamMemberCount(Connection conn, String team_code) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamMemberCount"));
			pstmt.setString(1, team_code);
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	public int teamEnrollment(Connection conn, Team team) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("enrollTeam");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, team.getTeam_code());
			pstmt.setString(2, team.getTeam_leader());
			pstmt.setString(3, team.getTeam_name());
			pstmt.setString(4, team.getTeam_gender());
			pstmt.setString(5, team.getTeam_age());
			pstmt.setString(6, team.getTeam_region());
			pstmt.setString(7, team.getTeam_mark_img());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}
	
	public Team selectTeamByName(Connection conn, String team_name) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Team t=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamByName"));
			pstmt.setString(1, team_name);
			rs=pstmt.executeQuery();
			if(rs.next()) t= new Team(rs.getString("team_code"), rs.getString("team_leader"), rs.getInt("team_num"),
					rs.getString("team_name"), rs.getString("team_gender"), rs.getString("team_age"),
					rs.getString("team_region"), rs.getInt("team_point"), rs.getString("team_mark_img"),
					rs.getDate("team_active_lastday"), rs.getString("team_delete_status"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return t;
	}
	
	public static Team getTeam(ResultSet rs) {
		Team t=null;
		try {
			t=new Team();
			t.setTeam_code(rs.getString("team_code"));
			t.setTeam_leader(rs.getString("team_leader"));
			t.setTeam_num(rs.getInt("team_num"));
			t.setTeam_name(rs.getString("team_name"));
			t.setTeam_gender(rs.getString("team_gender"));
			t.setTeam_age(rs.getString("team_age"));
			t.setTeam_region(rs.getString("team_region"));
			t.setTeam_point(rs.getInt("team_point"));
			t.setTeam_mark_img(rs.getString("team_mark_img"));
			t.setTeam_active_lastday(rs.getDate("team_active_lastday"));
			t.setTeam_delete_status(rs.getString("team_delete_status"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	public static TeamMemberInfo getTeamMemberInfo(ResultSet rs) {
		TeamMemberInfo tmi=null;
		try {
			tmi=new TeamMemberInfo();
			tmi.setSupporter_email(rs.getString("supporter_email"));
			tmi.setSupport_team(rs.getString("support_team"));
			tmi.setPosition(rs.getString("position"));
			tmi.setApplication_status(rs.getString("application_status"));
			tmi.setDelete_status(rs.getString("delete_status"));
			tmi.setEmail(rs.getString("email"));
			tmi.setPwd(rs.getString("pwd"));
			tmi.setName(rs.getString("name"));
			tmi.setBirthday(rs.getDate("birthday"));
			tmi.setGender(rs.getString("gender"));
			tmi.setPhone(rs.getString("phone"));
			tmi.setAddress(rs.getString("address"));
			tmi.setMember_kakao(rs.getString("member_kakao"));
			tmi.setMember_type(rs.getString("member_type"));
			tmi.setMember_delete_status(rs.getString("member_delete_status"));
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tmi;
	}
	
	
	
	
	
	

}

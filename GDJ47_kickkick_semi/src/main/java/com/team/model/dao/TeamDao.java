package com.team.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import com.team.model.vo.Team;

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
	

	/*
	 * public List<Team> selectTeamList(Connection conn,int cPage, int numPerpage){
	 * PreparedStatement pstmt=null; ResultSet rs=null; List<Team> list=new
	 * ArrayList(); try {
	 * pstmt=conn.prepareStatement(prop.getProperty("selectTeamList"));
	 * pstmt.setInt(1, (cPage-1)*numPerpage+1); pstmt.setInt(2, cPage*numPerpage);
	 * rs=pstmt.executeQuery(); while(rs.next()) { Team t=getTeam(rs); list.add(t);
	 * }
	 * 
	 * }catch(SQLException e) { e.printStackTrace(); }finally { close(rs);
	 * close(pstmt); } return list; }
	 * 
	 * 
	 * public int selectTeamCount(Connection conn) { PreparedStatement pstmt=null;
	 * ResultSet rs=null; int result=0; try {
	 * pstmt=conn.prepareStatement(prop.getProperty("selectTeamCount"));
	 * rs=pstmt.executeQuery(); if(rs.next()) result=rs.getInt(1);
	 * }catch(SQLException e) { e.printStackTrace(); }finally { close(rs);
	 * close(pstmt); }return result; }
	 */
	public List<Team> selectTeamList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Team> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamList"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Team t = new Team(rs.getString("team_code"), rs.getString("team_leader"), rs.getInt("team_num"),
						rs.getString("team_name"), rs.getString("team_gender"), rs.getString("team_age"),
						rs.getString("team_region"), rs.getInt("team_point"), rs.getString("team_mark_img"),
						rs.getDate("team_active_lastday"), rs.getString("team_delete_status"));
				list.add(t);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
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
	
	

}

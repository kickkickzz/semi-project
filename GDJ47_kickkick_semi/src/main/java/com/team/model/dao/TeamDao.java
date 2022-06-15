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
	

	public List<Team> selectTeamList(Connection conn,int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Team> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			Team t=getTeam(rs);
			list.add(t);
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


	private Team getTeam(ResultSet rs) throws SQLException{
		return Team.builder()
	
			.teamCode(rs.getString("team_code"))
			.teamLeader(rs.getString("team_leader"))
			.teamName(rs.getString("team_name"))
			.teamGender(rs.getString("team_gender"))
			.teamAge(rs.getInt("team_age"))
			.teamMarkPng(rs.getString("team_mark_png"))
			.teamPoint(rs.getInt("point"))
			.teamRegion(rs.getString("region"))
			.teamActiveLastday(rs.getDate("team_active_lastday"))
			.teamDeleteStatus(rs.getString("team_delete_status"))
			.build();
		
	}
}

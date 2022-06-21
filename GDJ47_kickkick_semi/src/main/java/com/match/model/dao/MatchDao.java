package com.match.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.*;

import com.match.model.vo.Match;

public class MatchDao {
	private Properties prop = new Properties();
	
	public MatchDao() {
		String path = MatchDao.class.getResource("/sql/match_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Match> selectmatch(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Match> result = new ArrayList<Match>();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMatchList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, (cPage)*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Match m = Match.builder().regist_num(rs.getInt("regist_num")).team_name(rs.getString("team_name"))
						.team_gender(rs.getString("team_gender")).team_age(rs.getString("team_age"))
						.stadium_match_member(rs.getString("stadium_match_member")).stadium_name(rs.getString("stadium_name"))
						.reservation_usage_start_time(rs.getInt("reservation_usage_start_time")).reservation_usage_end_time(rs.getInt("reservation_usage_end_time"))
						.reservation_usage_start_date(rs.getDate("reservation_usage_start_date"))
						.branch_manager_email(rs.getString("branch_manager_email")).branch_img(rs.getString("branch_img"))
						.branch_num(rs.getString("branch_num")).branch_address(rs.getString("branch_address"))
						.regist_status(rs.getString("regist_status"))
						.build();
				result.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int selectmatchcount(Connection conn) {
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMatchCount"));
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}return result;
	}
	
}

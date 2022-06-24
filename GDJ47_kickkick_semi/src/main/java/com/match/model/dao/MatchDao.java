package com.match.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.match.model.vo.Match;
import com.reservation.model.vo.PayHistory;
import com.team.model.vo.Team;

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
						.regist_branch_num(rs.getString("regist_branch_num")).regist_stadium_num(rs.getInt("regist_stadium_num")).branch_address(rs.getString("branch_address"))
						.regist_status(rs.getString("regist_status")).regist_reservation_code(rs.getString("regist_reservation_code"))
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
	
	public int teamregistcheck(Connection conn,String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("teamRegistCheck"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next())result =rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	public int codeCheck(Connection conn,String reservationcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("codeCheck"));
			pstmt.setString(1, reservationcode);
			rs=pstmt.executeQuery();
			if(rs.next())result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int check(Connection conn,String userId,String reservationcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt= conn.prepareStatement(prop.getProperty("check"));
			pstmt.setString(1, reservationcode);
			pstmt.setString(2, userId);
			rs= pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	public Team selectteamcode(Connection conn,String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Team result = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectteamcode"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=Team.builder().team_code(rs.getString("team_code")).build();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	public PayHistory selectpayhistoryinfo(Connection conn,String reservationcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PayHistory result = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectpayhistoryinfo"));
			pstmt.setString(1, reservationcode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = PayHistory.builder().stadium_branch_num(rs.getString("stadium_branch_num")).stadium_num(rs.getInt("stanum")).build();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int matchRegist(Connection conn,Match m) {
		PreparedStatement pstmt = null;
	
		int result =0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("matchRegist"));
			pstmt.setString(1,m.getTeam_code());
			pstmt.setString(2,m.getReservation_code());
			pstmt.setString(3,m.getBranch_num());
			pstmt.setInt(4, m.getStadium_num());
			
			
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstmt);
			
		}return result;
	}
	public int matchApplicationCheck(Connection conn,String regist_num,String teamcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("matchCheck"));
			pstmt.setString(1, regist_num);
			pstmt.setString(2, teamcode);
			rs=pstmt.executeQuery();
			if(rs.next())result = rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return result;
		
	}
	public int matchReApplicationCheck(Connection conn,String regist_num,String teamcode) {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("matchReCheck"));
			pstmt.setString(1, regist_num);
			pstmt.setString(2, teamcode);
			rs=pstmt.executeQuery();
			if(rs.next())result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int matchApplication(Connection conn,String regist_num,String teamcode,String branch_num,String stadium_num,String reservationcode) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("matchApplication"));
			pstmt.setString(1, regist_num);
			pstmt.setString(2, teamcode);
			pstmt.setString(3, branch_num);
			pstmt.setString(4, stadium_num);
			pstmt.setString(5, reservationcode);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return result;
	}
	public List<Match> searchMatch(Connection conn,String type,String keyword,int cPage,int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Match> result = new ArrayList<Match>();
		String sql = prop.getProperty("searchMatch");
		sql=sql.replace("$COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			if(type.equals("team_age")) {
				pstmt.setString(1,keyword);
			}
			if(type.equals("stadium_match_member")) {
				pstmt.setString(1,keyword);
			}
			if(type.equals("team_gender")) {
				pstmt.setString(1,keyword);
			}else {
				pstmt.setString(1, "%"+keyword+"%");
			}
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				Match m = Match.builder().regist_num(rs.getInt("regist_num")).team_name(rs.getString("team_name"))
						.team_gender(rs.getString("team_gender")).team_age(rs.getString("team_age"))
						.stadium_match_member(rs.getString("stadium_match_member")).stadium_name(rs.getString("stadium_name"))
						.reservation_usage_start_time(rs.getInt("reservation_usage_start_time")).reservation_usage_end_time(rs.getInt("reservation_usage_end_time"))
						.reservation_usage_start_date(rs.getDate("reservation_usage_start_date"))
						.branch_manager_email(rs.getString("branch_manager_email")).branch_img(rs.getString("branch_img"))
						.regist_branch_num(rs.getString("regist_branch_num")).regist_stadium_num(rs.getInt("regist_stadium_num")).branch_address(rs.getString("branch_address"))
						.regist_status(rs.getString("regist_status")).regist_reservation_code(rs.getString("regist_reservation_code"))
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
	public int searchMatchCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		int result = 0;
		String sql = prop.getProperty("searchMatchCount").replace("$COL", type);
		try {
			pstmt = conn.prepareStatement(sql);
			if(type.equals("team_gender")) {
				pstmt.setString(1,keyword);
			}else {
				pstmt.setString(1, "%"+keyword+"%");
			}
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	public Team selectteamcode2(Connection conn,String teamcode){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Team result = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectteamcode2"));
			pstmt.setString(1, teamcode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result= Team.builder().team_code(rs.getString("team_code")).team_name(rs.getString("team_name")).team_gender(rs.getString("team_gender"))
						.team_age(rs.getString("team_age")).team_region(rs.getString("team_region")).build();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}

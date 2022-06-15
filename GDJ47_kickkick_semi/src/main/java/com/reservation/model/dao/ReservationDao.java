package com.reservation.model.dao;

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

import com.reservation.model.vo.Stadium;

public class ReservationDao {
	private Properties prop = new Properties();
	
	public ReservationDao() {
		String path = ReservationDao.class.getResource("/sql/reservation_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Stadium> selectStadium(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Stadium> result= new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectstadium"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, (cPage)*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Stadium s = Stadium.builder().stadium_num(rs.getInt("stadium_num")).branch_num(rs.getString("stadium_branch_num"))
						.stadium_name(rs.getString("stadium_name")).stadium_match_member(rs.getString("stadium_match_member"))
						.stadium_reservation_start_time(rs.getInt("stadium_reservation_start_time")).stadium_reservation_end_time(rs.getInt("stadium_reservation_end_time")).
						branch_manager_email(rs.getString("branch_manager_email")).branch_address(rs.getString("branch_address"))
						.branch_phone(rs.getString("branch_phone")).branch_img(rs.getString("branch_img")).branch_website(rs.getString("branch_website")).
						branch_point(rs.getInt("branch_point")).branch_option_shower(rs.getString("branch_option_shower"))
						.branch_option_park(rs.getString("branch_option_park")).branch_option_uniform(rs.getString("branch_option_uniform"))
						.branch_option_shoes(rs.getString("branch_option_shoes")).branch_option_ball(rs.getString("branch_option_ball"))
						.branch_option_inout(rs.getString("branch_option_inout")).branch_branchInfo(rs.getString("branch_branchInfo"))
						.branch_detailInfo(rs.getString("branch_detailInfo")).branch_notes(rs.getString("branch_notes")).build();
				result.add(s);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int selectStadiumCount(Connection conn) {
		PreparedStatement pstmt = null;
		int result =0;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("stadiumcount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<Stadium> searchstadium(Connection conn,String keyword,int cPage,int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Stadium> result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("searchstadium"));
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, cPage);
			pstmt.setInt(3, numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Stadium s = Stadium.builder().stadium_num(rs.getInt("stadium_num")).branch_num(rs.getString("stadium_branch_num"))
						.stadium_name(rs.getString("stadium_name")).stadium_match_member(rs.getString("stadium_match_member"))
						.stadium_reservation_start_time(rs.getInt("stadium_reservation_start_time")).stadium_reservation_end_time(rs.getInt("stadium_reservation_end_time")).
						branch_manager_email(rs.getString("branch_manager_email")).branch_address(rs.getString("branch_address"))
						.branch_phone(rs.getString("branch_phone")).branch_img(rs.getString("branch_img")).branch_website(rs.getString("branch_website")).
						branch_point(rs.getInt("branch_point")).branch_option_shower(rs.getString("branch_option_shower"))
						.branch_option_park(rs.getString("branch_option_park")).branch_option_uniform(rs.getString("branch_option_uniform"))
						.branch_option_shoes(rs.getString("branch_option_shoes")).branch_option_ball(rs.getString("branch_option_ball"))
						.branch_option_inout(rs.getString("branch_option_inout")).branch_branchInfo(rs.getString("branch_branchInfo"))
						.branch_detailInfo(rs.getString("branch_detailInfo")).branch_notes(rs.getString("branch_notes")).build();
				result.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int searchstadiumcount(Connection conn,String keyword) {
		PreparedStatement pstmt =null;
		int result = 0;
		ResultSet rs =null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("searchstadiumcount"));
			pstmt.setString(1, keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
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

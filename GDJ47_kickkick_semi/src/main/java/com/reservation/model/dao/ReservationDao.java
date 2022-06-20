package com.reservation.model.dao;

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

import com.reservation.model.vo.PayHistory;
import com.reservation.model.vo.ReservationInfo;
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
	public Stadium searchstadiumnum(Connection conn,int stanum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Stadium result = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("searchstadiumnum"));
			pstmt.setInt(1, stanum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = Stadium.builder().stadium_num(rs.getInt("stadium_num")).branch_num(rs.getString("stadium_branch_num"))
						.stadium_name(rs.getString("stadium_name")).stadium_match_member(rs.getString("stadium_match_member"))
						.stadium_reservation_start_time(rs.getInt("stadium_reservation_start_time")).stadium_reservation_end_time(rs.getInt("stadium_reservation_end_time")).
						branch_manager_email(rs.getString("branch_manager_email")).branch_address(rs.getString("branch_address"))
						.branch_phone(rs.getString("branch_phone")).branch_img(rs.getString("branch_img")).branch_website(rs.getString("branch_website")).
						branch_point(rs.getInt("branch_point")).branch_option_shower(rs.getString("branch_option_shower"))
						.branch_option_park(rs.getString("branch_option_park")).branch_option_uniform(rs.getString("branch_option_uniform"))
						.branch_option_shoes(rs.getString("branch_option_shoes")).branch_option_ball(rs.getString("branch_option_ball"))
						.branch_option_inout(rs.getString("branch_option_inout")).branch_branchInfo(rs.getString("branch_branchInfo"))
						.branch_detailInfo(rs.getString("branch_detailInfo")).branch_notes(rs.getString("branch_notes")).build();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return result;
	}
	public List<ReservationInfo> selectReservationDateList(Connection conn,int stanum,String dat){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationInfo> result = new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectReservationDateList"));
			pstmt.setInt(1, stanum);
			pstmt.setString(2, dat);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ReservationInfo r = ReservationInfo.builder().reservation_code(rs.getString(1)).reservation_email(rs.getString(2))
						.reservation_branch_num(rs.getString(3)).reservation_stadium_num(rs.getInt(4)).reservation_num(rs.getInt(5))
						.reservation_price(rs.getInt(6)).reservation_usage_start_time(rs.getInt(7))
						.reservation_usage_time(rs.getInt(8)).reservation_usage_end_time(rs.getInt(9)).reservation_usage_start_date(rs.getString(10))
						.reservation_status(rs.getString(11)).build();
				result.add(r);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int codeCheck(Connection conn,String reservation_code) {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("codeCheck"));
			pstmt.setString(1, reservation_code);
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
	public int reservationinsert(Connection conn,ReservationInfo reservation) {
		PreparedStatement pstmt = null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("reservationinsert"));
			
			pstmt.setString(1, reservation.getReservation_code());
			pstmt.setString(2, reservation.getReservation_email());
			pstmt.setString(3, reservation.getReservation_branch_num());
			pstmt.setInt(4, reservation.getReservation_stadium_num());
			pstmt.setInt(5, reservation.getReservation_price());
			pstmt.setInt(6, reservation.getReservation_usage_start_time());
			pstmt.setInt(7, reservation.getReservation_usage_time());
			pstmt.setInt(8, reservation.getReservation_usage_end_time());
			pstmt.setString(9, reservation.getReservation_usage_start_date());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int insertpayhistory(Connection conn,PayHistory p) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("payhistoryinsert"));
			pstmt.setString(1, p.getPaycode());
			pstmt.setString(2, p.getEmail());
			pstmt.setString(3, p.getReservation_code());
			pstmt.setString(4, p.getPaymethod());
			pstmt.setString(5, p.getStadium_branch_num());
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		
		}
		return result;
	}
}

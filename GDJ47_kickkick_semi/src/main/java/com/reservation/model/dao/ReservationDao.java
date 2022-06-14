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
	
	public List<Stadium> selectStadium(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Stadium> result= new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectstadium"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				

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

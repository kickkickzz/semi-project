package com.reservation.model.service;

import java.sql.Connection;
import java.util.List;

import com.reservation.model.dao.ReservationDao;
import com.reservation.model.vo.Stadium;
import static common.JDBCTemplate.*;
public class ReservationService {
	private ReservationDao dao = new ReservationDao();
	
	public List<Stadium> selectStadium(int cPage,int numPerpage){
		Connection conn = getConnection();
		List<Stadium> result = dao.selectStadium(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	public int selectStadiumCount() {
		Connection conn = getConnection();
		int result = dao.selectStadiumCount(conn);
		close(conn);
		return result;
	}

}

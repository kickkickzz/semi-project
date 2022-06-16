package com.reservation.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import java.sql.Date;
import java.util.List;

import com.reservation.model.dao.ReservationDao;
import com.reservation.model.vo.ReservationInfo;
import com.reservation.model.vo.Stadium;
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
	public List<Stadium> searchstadium(String keyword,int cPage,int numPerpage){
		Connection conn = getConnection();
		List<Stadium> result = dao.searchstadium(conn,keyword,cPage,numPerpage);
		close(conn);
		return result;
	}
	public int searchstadiumcount(String keyword) {
		Connection conn = getConnection();
		int result = dao.searchstadiumcount(conn,keyword);
		close(conn);
		return result;
	}
	public Stadium searchstadiumnum(int stanum) {
		Connection conn = getConnection();
		Stadium s = dao.searchstadiumnum(conn,stanum);
		close(conn);
		return s;
	}
	
	public List<ReservationInfo> selectreservationDate(int stanum, String dat) {
		Connection conn= getConnection();
		List<ReservationInfo> result = dao.selectReservationDateList(conn,stanum,dat);
		close(conn);
		return result;
	}

}

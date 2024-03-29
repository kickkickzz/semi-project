package com.reservation.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.reservation.model.dao.ReservationDao;
import com.reservation.model.vo.PayHistory;
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
	public int codeCheck(String reservation_code) {
		Connection conn = getConnection();
		int result = dao.codeCheck(conn,reservation_code);
		close(conn);
		return result;
	}
	public int reservationInsert(ReservationInfo r) {
		Connection conn = getConnection();
		int result = dao.reservationinsert(conn,r);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
	public int insertpayhistory(PayHistory p) {
		Connection conn = getConnection();
		int result = dao.insertpayhistory(conn,p);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
	

	//stadium 최근 6개 조회
	public List<Stadium> sixStadium(){
		Connection conn = getConnection();
		List<Stadium> result = dao.sixStadium(conn);
		close(conn);
		return result;
	}




	//구장등록할떄 지점 조회
	public List<Stadium> stadiumSearch(String email){
		Connection conn= getConnection();
		List<Stadium> result = dao.stadiumSearch(conn,email);
		close(conn);
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	//구장 등록
	public int insertStadium(String stadiumName,String stadiumMatchMember,String branchNum,int startTime,int endTime) {
		Connection conn=getConnection();
		int result = dao.insertStadium(conn, stadiumName, stadiumMatchMember,branchNum,startTime,endTime);
		close(conn);
		return result;
	}
}

package com.match.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.match.model.dao.MatchDao;
import com.match.model.vo.Match;
import com.reservation.model.vo.PayHistory;
import com.team.model.vo.Team;

public class MatchService {
	private MatchDao dao = new MatchDao();
	
	
	public List<Match> selectmatch(int cPage,int numPerpage){
		Connection conn = getConnection();
		List<Match> result = dao.selectmatch(conn,cPage,numPerpage);
		close(conn);
		return result;
		
	}
	public int selectmatchcount() {
		Connection conn = getConnection();
		int result = dao.selectmatchcount(conn);
		close(conn);
		return result;
		
	}
	public int teamregistcheck(String userId) {
		Connection conn = getConnection();
		int result = dao.teamregistcheck(conn,userId);
		close(conn);
		return result;
	}
	public int codeCheck(String reservationcode) {
		Connection conn = getConnection();
		int result = dao.codeCheck(conn,reservationcode);
		close(conn);
		return result;
	}
	public int check(String userId,String reservationcode) {
		Connection conn = getConnection();
		int result = dao.check(conn,userId,reservationcode);
		close(conn);
		return result;
		
	}
	public Team selectteamcode(String userId) {
		Connection conn = getConnection();
		Team result = dao.selectteamcode(conn,userId);
		close(conn);
		return result;
		
	}
	public PayHistory selectpayhistoryinfo(String reservationcode) {
		Connection conn= getConnection();
		PayHistory result = dao.selectpayhistoryinfo(conn,reservationcode);
		close(conn);
		return result;
	}
	public int matchRegist(Match m) {
		Connection conn = getConnection();
		int result = dao.matchRegist(conn,m);
		close(conn);
		return result;
	}
	
	
}

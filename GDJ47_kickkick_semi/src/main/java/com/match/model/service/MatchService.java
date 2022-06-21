package com.match.model.service;

import java.sql.Connection;
import java.util.List;

import com.match.model.dao.MatchDao;
import com.match.model.vo.Match;
import  static common.JDBCTemplate.*;

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

}

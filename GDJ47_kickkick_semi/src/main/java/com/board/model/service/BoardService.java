package com.board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.board.model.dao.BoardDao;
import com.board.model.vo.Board;

public class BoardService {
	private BoardDao dao=new BoardDao();

	public List<Board> selectList() {
		Connection conn= getConnection();
		List<Board> list= new BoardDao().selectList(conn);
		close(conn);
		return list;
	}
	
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn,b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public Board selectBoard(int bo) {
		Connection conn=getConnection();
		Board b=dao.selectBoard(conn,bo);
		close(conn);
		return b;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getBoardListCount() {
		Connection conn=getConnection();
		int result=new BoardDao().getBoardListCount(conn);
		close(conn);
		return result;
	}

}

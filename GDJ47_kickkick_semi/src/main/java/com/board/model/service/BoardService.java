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
	
	public List<Board> selectBoardList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Board> result=dao.selectBoardList(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	
	public int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	
	public Board selectBoard(int bId, boolean isRead) {
		Connection conn = getConnection();
		Board b = dao.selectBoard(conn, bId);
		if(b!=null&&!isRead) {
			int result=dao.updateReadCount(conn,bId);
			if(result>0) {
				commit(conn);
				b.setBoardReadCount(b.getBoardReadCount()+1);
			}
			else rollback(conn);
			
		}
		
		close(conn);
		return b;
	}

	public List<Board> selectList() {
		Connection conn = getConnection();
		List<Board> list = new BoardDao().selectList(conn);
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
	

	public int deleteBoard(int fId, int bId) {
		int result = 0;
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		// 게시판에서 지우기
		result = bDao.deleteBoard(conn, bId);
		if (fId > 0) {
			result += bDao.deleteBoardAttachmentFid(conn, bId);
		}
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}


	 //게시판 상위 4개 가져오기 
	public List<Board> mainNotice(){
		Connection conn = getConnection();
		List<Board> list = new BoardDao().mainNotice(conn);
		close(conn);
		return list;
	}

	
	
	 

}

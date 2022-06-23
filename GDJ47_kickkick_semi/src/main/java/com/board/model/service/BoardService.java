package com.board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.board.model.dao.BoardDao;
import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import com.board.model.vo.Board_re;
import com.board.model.vo.PageInfo;


public class BoardService {
	
	private BoardDao dao=new BoardDao();
	
	private Properties prop = new Properties();
	
//	public List<Board_re> selectBoardList(int cPage, int numPerpage){
//		Connection conn=getConnection();
//		List<Board_re> result=dao.selectBoardList(conn,cPage,numPerpage);
//		close(conn);
//		return result;
//	}
	
	public int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	
	public Board selectBoard(int bId) {
		// bId에 해당하는 공지사항 게시글정보를 갖고온다.
				Connection conn=getConnection();
				
				Board board=null;
				board=new BoardDao().selectBoard(conn, bId);
				close(conn);
				return board;
	}

	public int deleteBoard(int fId, int bId) {
		int result = 0;
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		// 게시판에서 지우기
		result = bDao.deleteBoard(conn, bId);
		if (fId > 0) {
			System.out.println("파일 있을때 삭제");
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

	public List<Board> selectBoardList(PageInfo pi) {
		Connection conn= getConnection();
		List<Board> boardList= new BoardDao().selectBoardList(conn, pi);
		if(boardList!=null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return boardList;
	}

	public int getBoardListCount() {
		Connection conn=getConnection();
		int result=dao.getBoardListCount(conn);
		
		close(conn);
		return result;
	}
	
	public int insertBoard(Board board, List<BoardAttachment> fileList) {
		Connection conn =getConnection();
	
		
		// 글만등록하기.
		int result1=dao.insertBoard(conn, board);
		int result2=0;
		
		if(board.getBoardImgPath()==null) {
			System.out.println("BoardService(이미지없음) => "+ board);
			//이미지 등록안한 상태
			if(result1>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result1;
			
		}else{
			//이미지 등록상태- board.getBoardImgPath()!=null
			System.out.println("BoardService(이미지있음) => "+ board);
			System.out.println("fileList=> "+ fileList);
			result2=dao.insertBoardAttachment(conn, fileList);
			if(result1>0 && result2>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
		}
		return result2;
	}
	
	public BoardAttachment selectBoardAttachment(int bId) {
		// bId에 해당하는 공지사항 게시글 이미지 정보를 갖고온다.
		Connection conn=getConnection();
		BoardAttachment boardImgAttach=null;
		
		boardImgAttach=new BoardDao().selectBoardAttachment(conn, bId);
		close(conn);
		return boardImgAttach; 
	}

	
	
	 

}

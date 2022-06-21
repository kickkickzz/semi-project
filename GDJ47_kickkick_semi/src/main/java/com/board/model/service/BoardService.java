package com.board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.board.model.dao.BoardDao;
import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import com.board.model.vo.PageInfo;

public class BoardService {
	public ArrayList<Board> selectBoardList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().selectBoardList(conn, pi);
		if (boardList != null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return boardList;

	}

	public int getBoardListCount() {
		Connection conn = getConnection();
		int result = new BoardDao().getBoardListCount(conn);
		close(conn);
		return result;
	}

	public Board selectBoard(int bId) {
		Connection conn = getConnection();
		Board board = null;
		board = new BoardDao().selectBoard(conn, bId);
		close(conn);
		return board;
	}

	public BoardAttachment selectBoardAttachment(int bId) {
		// 공지사항 이미지 정보 등
		Connection conn = getConnection();
		BoardAttachment boardImgAttach = null;
		boardImgAttach = new BoardDao().selectBoardAttachment(conn, bId);
		close(conn);
		return boardImgAttach;
	}

	public ArrayList<Board> selectList() {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn);
		close(conn);
		return list;
	}

	public int insertBoard(Board board, ArrayList<BoardAttachment> fileList) {
		Connection conn = getConnection();
		BoardDao dao = new BoardDao();
		// 글만 등록
		int result1 = dao.insertBoard(conn, board);
		int result2 = 0;
		if (board.getBoardImgPath() == null) {
			System.out.println("BoardService(x) => " + board);
			if (result1 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			return result1;

		} else {
			// 이미지 등록
			System.out.println("BoardService(o) => " + board);
			System.out.println("fileList=> " + fileList);
			result2 = dao.insertBoardAttachment(conn, fileList);
			if (result1 > 0 && result2 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
		}
		return result2;
	}

	public int updateBoard(Board board, int bId, int fId, BoardAttachment bat) {

		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		// 게시판 수정
		int result = bDao.updateBoard(conn, board, bId);

		// 변경 전 이미지
		if (fId > 0) {
			// 변경 후 이미지
			if (board.getBoardImgPath() != null) {
				// 이미지 변경
				result += bDao.updateBoardAttachment(conn, bat, bId);
			} else {
				// 이미지 삭제
				result += bDao.deleteBoardAttachmentFid(conn, bId);
			}

		} else if (fId <= 0 && board.getBoardImgPath() != null) {
			// 변경후에는 이미지가 존재
			result += bDao.insertBoardAttachmentBid(conn, bat, bId);
		}
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
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

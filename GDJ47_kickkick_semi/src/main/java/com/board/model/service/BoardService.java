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
import com.board.model.vo.PageInfo;
import com.member.model.vo.BoardAttachment;

public class BoardService {
	public ArrayList<Board> selectBoardList(PageInfo pi) {
		Connection conn= getConnection();
		ArrayList<Board> boardList= new BoardDao().selectBoardList(conn, pi);
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
		int result=new BoardDao().getBoardListCount(conn);
		
		close(conn);
		return result;
	}
	
	public Board selectBoard(int bId) {
		// bId�� �ش��ϴ� �������� �Խñ������� ����´�.
		Connection conn=getConnection();
		
		Board board=null;
		board=new BoardDao().selectBoard(conn, bId);
		close(conn);
		return board;
	}
	

	public BoardAttachment selectBoardAttachment(int bId) {
		// bId�� �ش��ϴ� �������� �Խñ� �̹��� ������ ����´�.
		Connection conn=getConnection();
		BoardAttachment boardImgAttach=null;
		
		boardImgAttach=new BoardDao().selectBoardAttachment(conn, bId);
		close(conn);
		return boardImgAttach; 
	}


	public ArrayList<Board> selectList() {
		Connection conn= getConnection();
		ArrayList<Board> list= new BoardDao().selectList(conn);
		close(conn);
		return list;
	}
	
	

	public int insertBoard(Board board, ArrayList<BoardAttachment> fileList) {
		Connection conn =getConnection();
		BoardDao dao= new BoardDao();
		
		// �۸�����ϱ�.
		int result1=dao.insertBoard(conn, board);
		int result2=0;
		
		if(board.getBoardImgPath()==null) {
			System.out.println("BoardService(x) => "+ board);
			//�̹��� ��Ͼ��� ����
			if(result1>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			close(conn);
			return result1;
			
		}else{
			//�̹��� ��ϻ���- board.getBoardImgPath()!=null
			System.out.println("BoardService(o) => "+ board);
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
	

	public int updateBoard( Board board, int bId, int fId, BoardAttachment bat) {
		//board, bat: ������ �Խù�, �Խ����̹���
		
		Connection conn=getConnection();
		BoardDao bDao = new BoardDao();
		
		//�Խ��� ����	
		int result=bDao.updateBoard(conn, board, bId);
	
		//�������� �̹����� �������ִ°�?
		if(fId>0) {
			//�������� �̹����� ������.
			
			//�����Ŀ��� �̹����� �����°�?
			if(board.getBoardImgPath()!=null) {
				//�����Ŀ� �̹����� ������. => �׳� ����
				//�̹��� ����
				result+=bDao.updateBoardAttachment(conn, bat, bId);
			}else {
				//�����Ŀ��� �̹����� ���� �ʾҴ� 
				//=> bId�� �ش��ϴ� �̹����� y���� �ٲ۴�.
				//=> �̹����� ����.
				result+=bDao.deleteBoardAttachmentFid(conn, bId);
			}

		}else if(fId<=0 && board.getBoardImgPath()!=null){
			//fId=0 : ���������� �̹����� ���� �ʾҴ�.
			//�����Ŀ��� �̹����� ����
			result+=bDao.insertBoardAttachmentBid(conn, bat, bId);
		}
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}


	public int deleteBoard(int fId, int bId) {
		int result=0;
		Connection conn=getConnection();
		BoardDao bDao= new BoardDao();
		//�Խ��Ǹ� ����°�.
		result=bDao.deleteBoard(conn, bId);
		
		if(fId>0) {
			result+=bDao.deleteBoardAttachmentFid(conn, bId);
		}
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	
}

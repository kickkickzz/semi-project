package com.board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.board.model.dao.BoardDao;
import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import com.board.model.vo.PageInfo;

public class BoardService {
	
	private BoardDao dao=new BoardDao();
	
	public List<Board> selectBoardList(PageInfo pi) {
		Connection conn= getConnection();
		List<Board> boardList= dao.selectBoardList(conn, pi);
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
	
	public Board selectBoard(int bId) {
		// bId에 해당하는 공지사항 게시글정보
		Connection conn=getConnection();
		
		Board board=null;
		board=dao.selectBoard(conn, bId);
		close(conn);
		return board;
	}
	
	public BoardAttachment selectBoardAttachment(int bId) {
		// bId에 해당하는 공지사항 게시글 이미지 정보
		Connection conn=getConnection();
		BoardAttachment boardImgAttach=null;
		
		boardImgAttach=dao.selectBoardAttachment(conn, bId);
		close(conn);
		return boardImgAttach; 
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
	
	public int updateBoard( Board board, int bId, int fId, BoardAttachment bat) {
		//board, bat: 변경한 게시물, 게시판이미지
		
		Connection conn=getConnection();
		
		//게시판 수정	
		int result=dao.updateBoard(conn, board, bId);
		System.out.println(result);
		//변경전에 이미지를 가지고있는가?
		if(fId>0) {
			//변경전에 이미지를 가졌다.
			
			//변경후에는 이미지를 가졌는가?
			if(board.getBoardImgPath()!=null) {
				//변경후에 이미지를 가졌다. => 그냥 변경
				//이미지 변경
				result+=dao.updateBoardAttachment(conn, bat, bId);
			}else {
				//변경후에는 이미지를 갖지 않았다 
				//=> bId에 해당하는 이미지는 y으로 바꾼다.
				//=> 이미지를 삭제.
				result+=dao.deleteBoardAttachmentFid(conn, bId);
			}

		}else if(fId<=0 && board.getBoardImgPath()!=null){
			//fId=0 : 변경전에는 이미지를 갖지 않았다.
			//변경후에는 이미지가 존재
			result+=dao.insertBoardAttachmentBid(conn, bat, bId);
		}
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	public int deleteBoard(int fId, int bId) {
		int result = 0;
		Connection conn = getConnection();
		// 게시판에서 지우기
		result = dao.deleteBoard(conn, bId);
		if (fId > 0) {
			result+=dao.deleteBoardAttachmentFid(conn, bId);
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

	
	
	

	
//	public List<Board_re> selectBoardList(int cPage, int numPerpage){
//	Connection conn=getConnection();
//	List<Board_re> result=dao.selectBoardList(conn,cPage,numPerpage);
//	close(conn);
//	return result;
//}
	
//	public int selectBoardCount() {
//		Connection conn=getConnection();
//		int result=dao.selectBoardCount(conn);
//		close(conn);
//		return result;
//	}
	 

}

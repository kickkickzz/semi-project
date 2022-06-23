package com.board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import com.board.model.vo.Board_re;
import com.board.model.vo.PageInfo;

public class BoardDao {
	private Properties prop=new Properties();
	
	
	
	public BoardDao() {
		String fileName=BoardDao.class.getResource("/sql/board_sql.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
//	public List<Board> selectBoardList(Connection conn, int cPage, int numPerpage) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<Board> boardList = new ArrayList();
//		
//		try{
//			pstmt=conn.prepareStatement(prop.getProperty("selectBoardList"));
//			pstmt.setInt(1, (cPage-1)*numPerpage+1);
//			pstmt.setInt(2, cPage*numPerpage);
//			rs=pstmt.executeQuery();
//			
//			while(rs.next()){
//				Board board=new Board(
//					rs.getInt("BOARD_NUM"),
//					rs.getString("WRITER_EMAIL"),
//					rs.getString("BOARD_TITLE"),
//					rs.getString("BOARD_CONTENT"),
//					rs.getDate("BOARD_DATE"),
//					rs.getString("BOARD_DELETE_STATUS"),
//					rs.getString("BOARD_WRITER"));
//					boardList.add(board);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally{
//			close(rs);
//			close(pstmt);
//		}
//		return boardList;
//	}
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}

	public int insertBoard(Connection conn, Board b) {
		
		PreparedStatement pstmt=null;
		int result=0;
		String query=prop.getProperty("insertBoard");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardWriterEmail());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardImgPath());
			pstmt.setString(5, b.getBoardWriter());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
//		PreparedStatement pstmt=null;
//		int result=0;
//		
//		try{
//			pstmt=conn.prepareStatement(prop.getProperty("insertBoard"));
//			pstmt.setString(1,b.getBoardWriterEmail());
//			pstmt.setString(2,b.getBoardTitle());
//			pstmt.setString(3,b.getBoardContent());
////			pstmt.setString(4, b.getBoardOriginalFilename());
////			pstmt.setString(5, b.getBoardRenamedFilename());
//			pstmt.setString(4,b.getBoardWriter());
//			result=pstmt.executeUpdate();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally{
//			close(pstmt);
//		}
//		
//		return result;
	}


	
	public int getBoardListCount(Connection conn) {
		int result=0;
		Statement stmt= null;
		ResultSet rs=null;
		
		String query=prop.getProperty("getBoardListCount");
		try{
			stmt=conn.prepareStatement(query);
			rs=stmt.executeQuery(query);
			if(rs.next()){
				result=rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(stmt);
		}
		return result;
	}
	
	
	
	public Board selectBoard(Connection conn, int bId) {
		Board board=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query=prop.getProperty("selectBoardBid");
		
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				//이건 풀로 받아와서 바꾸기
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return board;
	}

	public int updateBoard(Connection conn, Board_re b, int bId) {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("updateBoard");
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,b.getBoardTitle());
			pstmt.setString(2,b.getBoardContent());
//			pstmt.setString(3,b.getBoardImgPath());
			pstmt.setDate(4,b.getBoardDate());
			pstmt.setInt(5,bId);
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}


	public int deleteBoard(Connection conn, int bId) {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("deleteBoard");
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bId);
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
	//메인 공지사항에 뿌릴 공지사항 상위 4개 리스트
	public List<Board> mainNotice(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("mainNotice"));
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 4);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board=new Board(
					rs.getInt("BOARD_NUM"),
					rs.getString("BOARD_WRITER"),
					rs.getString("WRITER_EMAIL"),
					rs.getString("BOARD_TITLE"),
					rs.getString("BOARD_CONTENT"),
					rs.getString("BOARD_IMG"),
					rs.getDate("BOARD_DATE"),
					rs.getString("BOARD_DELETE_STATUS"));
					list.add(board);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public List<Board> selectBoardList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> boardList = null;
		
		String query= prop.getProperty("selectBoardList");
		int startPage= (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;//시작페이지
		int endPage=startPage +pi.getBoardLimit()-1;//끝페이지
		System.out.println("시작페이지: "+startPage+"/ 끝페이지: "+endPage);
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, endPage);
			rset=pstmt.executeQuery();
			
			boardList= new ArrayList<Board>();
			while(rset.next()) {
				Board board=new Board(
				rset.getInt("BOARD_NUM"),
				rset.getString("BOARD_WRITER"),
				rset.getString("WRITER_EMAIL"),
				rset.getString("BOARD_TITLE"),
				rset.getString("BOARD_CONTENT"),
				rset.getString("BOARD_IMG"),
				rset.getDate("BOARD_DATE"),
				rset.getString("BOARD_DELETE_STATUS"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardList;
	}
	
	public BoardAttachment selectBoardAttachment(Connection conn, int bId) {
		BoardAttachment bat = null;
		PreparedStatement pstmt= null;
		ResultSet rset=null;
		
		String query=prop.getProperty("selectBoardAttachmentBid");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bId);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				bat=new BoardAttachment(
						rset.getInt("FILE_ID"),
						rset.getInt("BOARD_NUM"),
						rset.getString("ORIGIN_NAME"),
						rset.getString("CHANGE_NAME"),
						rset.getString("FILE_PATH"),
						rset.getDate("UPLOAD_DATE"),
						rset.getString("FILE_DELETED_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return bat;
	}
	
	
	public int insertBoardAttachment(Connection conn, List<BoardAttachment> fileList) {
		int result=0;
		PreparedStatement pstmt= null;
		
		String query= prop.getProperty("insertBoardAttachment");
		try {
			for(int i=0; i<fileList.size(); i++) {
				BoardAttachment bat= fileList.get(i);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, bat.getOriginName());
				pstmt.setString(2, bat.getChangeName());
				pstmt.setString(3, bat.getFilePath());
				
				result+= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			System.out.println("result=> "+ result);
		}
		return result;
	}
	
	

	
}

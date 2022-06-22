package com.board.model.dao;

import static common.JDBCTemplate.close;

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

public class BoardDao {
	private Properties prop=new Properties();
	
	public BoardDao() {
		String path=BoardDao.class.getResource("/sql/board_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardList = new ArrayList();
		
		try{
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				boardList.add(getBoard(rs) );
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	
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
		
		try{
			pstmt=conn.prepareStatement(prop.getProperty("insertBoard"));
			pstmt.setString(1,b.getBoardWriterEmail());
			pstmt.setString(2,b.getBoardTitle());
			pstmt.setString(3,b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFilename());
			pstmt.setString(5, b.getBoardRenamedFilename());
//			pstmt.setString(6,b.getBoardWriter());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
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
				board= getBoard(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return board;
	}

	public int updateBoard(Connection conn, Board b, int bId) {
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
	
	public int updateReadCount(Connection conn, int bId) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateReadcount")); //쿼리 입력 필요
			pstmt.setInt(1, bId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
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
				Board b=getBoard(rs);
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	private Board getBoard(ResultSet rs) throws SQLException{
		return Board.builder()
				.boardNum(rs.getInt("board_no"))
				.boardWriterEmail(rs.getString("writer_email"))
				.boardTitle(rs.getString("board_title"))
				.boardContent(rs.getString("board_content"))
				.boardWriter(rs.getString("board_writer"))
				.boardOriginalFilename(rs.getString("board_original_filename"))
				.boardRenamedFilename(rs.getString("board_renamed_filename"))
				.boardDate(rs.getDate("board_date"))
				.boardReadCount(rs.getInt("board_readcount"))
				.build();
	}
	
}

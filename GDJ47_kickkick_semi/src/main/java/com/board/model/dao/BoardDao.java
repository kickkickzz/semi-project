package com.board.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.board.model.vo.Board;
import com.board.model.vo.PageInfo;
import com.board.model.dao.BoardDao;

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
	//공지사항게시판 글 목록 조회
	public List<Board> selectList(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardList"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

	private Board getBoard(ResultSet rs) throws SQLException{
		return Board.builder()
				.boardNum(rs.getInt("board_no"))
				.boardWriter(rs.getString("board_writer"))
				.boardWriterEmail(rs.getString("board_writer_email"))
				.boardTitle(rs.getString("board_title"))
				.boardContent(rs.getString("board_content"))
				.boardImgPath(rs.getString("board_img_path"))
				.boardDate(rs.getDate("board_date"))
				.boardDeleteStatus(rs.getString("board_delete_status"))
				.build();
	}

	public int insertBoard(Connection conn, Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Board selectBoard(Connection conn, int bo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoard"));
			pstmt.setInt(1, bo);
			rs=pstmt.executeQuery();
			if(rs.next()) b=getBoard(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}return b;
	}

	public int getBoardListCount(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		List<Board> result=new ArrayList();
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}

	

}

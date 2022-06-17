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
import com.board.model.vo.PageInfo;
import com.member.model.vo.BoardAttachment;

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
	public ArrayList<Board> selectBoardList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> boardList = null;
		
		String query= prop.getProperty("selectBoardList");
		int startPage= (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;//시작페이지
		int endPage=startPage +pi.getBoardLimit()-1;//끝페이지
		System.out.println("시작페이지: "+startPage+"/ 끝페이지: "+endPage);
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, endPage);
			rs=pstmt.executeQuery();
			
			boardList= new ArrayList<Board>();
			while(rs.next()) {
				Board board=new Board(
					rs.getInt("BOARD_NUM"),
					rs.getString("WRITER_EMAIL"),
					rs.getString("BOARD_WRITER"),
					rs.getString("BOARD_TITLE"),
					rs.getString("BOARD_CONTENT"),
					rs.getString("BOARD_IMG"),
					rs.getDate("BOARD_DATE"),
					rs.getString("BOARD_DELETE_STATUS"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=prop.getProperty("insertBoard");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,board.getBoardWriterEmail());
			pstmt.setString(2,board.getBoardTitle());
			pstmt.setString(3,board.getBoardContent());
			pstmt.setString(4,board.getBoardImgPath());
			pstmt.setString(5,board.getBoardWriter());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
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
	
	public int insertBoardAttachment(Connection conn, BoardAttachment bat) {
		int result=0;
		PreparedStatement pstmt= null;
		
		String query= prop.getProperty("insertBoardAttachment");
		try {
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bat.getOriginName());
			pstmt.setString(2, bat.getChangeName());
			pstmt.setString(3, bat.getFilePath());
			result+= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			System.out.println("result=> "+ result);
		}
		return result;
	}

	
	public int getBoardListCount(Connection conn) {
		int result=0;
		Statement stmt= null;
		ResultSet rs=null;
		
		String query=prop.getProperty("getBoardListCount");
		try {
			stmt=conn.prepareStatement(query);
			rs=stmt.executeQuery(query);
			if(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return result;
	}
	
	public Board selectBoard(Connection conn, int bId) {
		// bId에 해당하는 게시글을 갖고온다.
		Board board=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query= prop.getProperty("selectBoardBid");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//rset: 1개면 (게시물 고유번호인  bId해당하는 공지사항이있으면)
				board= new Board(
					rs.getInt("BOARD_NUM"),
					rs.getString("BOARD_WRITER"),
					rs.getString("WRITER_EMAIL"),
					rs.getString("BOARD_TITLE"),
					rs.getString("BOARD_CONTENT"),
					rs.getString("BOARD_IMG"),
					rs.getDate("BOARD_DATE"),
					rs.getString("BOARD_DELETE_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return board;
	}


	public BoardAttachment selectBoardAttachment(Connection conn, int bId) {
		BoardAttachment bat = null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		String query=prop.getProperty("selectBoardAttachmentBid");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bId);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				bat=new BoardAttachment(
					rs.getInt("FILE_ID"),
					rs.getInt("BOARD_NUM"),
					rs.getString("ORIGIN_NAME"),
					rs.getString("CHANGE_NAME"),
					rs.getString("FILE_PATH"),
					rs.getDate("UPLOAD_DATE"),
					rs.getString("FILE_DELETED_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return bat;
	}

	public ArrayList<Board> selectList(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Board> list=null;
		
		String query = prop.getProperty("showMainTop5");
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			list=new ArrayList<Board>();
			
			while(rs.next()) {
				Board bo = new Board(					
					rs.getInt("BOARD_NUM"),
					rs.getString("BOARD_WRITER"),
					rs.getString("WRITER_EMAIL"),
					rs.getString("BOARD_TITLE"),
					rs.getString("BOARD_CONTENT"),
					rs.getString("BOARD_IMG"),
					rs.getDate("BOARD_DATE"),
					rs.getString("BOARD_DELETE_STATUS"));
				list.add(bo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}

	public int updateBoard(Connection conn, Board board, int bId) {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("updateBoard");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardImgPath());
			pstmt.setDate(4, board.getBoardDate());
			pstmt.setInt(5, bId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBoardAttachment(Connection conn, BoardAttachment bat, int bId) {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("updateBoardAttachment");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bat.getOriginName());
			pstmt.setString(2, bat.getChangeName());
			pstmt.setString(3, bat.getFilePath());
			pstmt.setDate(4, bat.getUpdateDate());
			pstmt.setInt(5, bId);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoardAttachmentFid(Connection conn, int bId) {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("deleteBoardAttachment");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoardAttachmentBid(Connection conn, BoardAttachment bat, int bId) {
		int result=0;
		PreparedStatement pstmt= null;
		String query= prop.getProperty("insertBoardAttachmentBid");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			pstmt.setString(2, bat.getOriginName());
			pstmt.setString(3, bat.getChangeName());
			pstmt.setString(4, bat.getFilePath());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int bId) {
		int result=0;
		PreparedStatement pstmt=null;
		String query= prop.getProperty("deleteBoard");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}

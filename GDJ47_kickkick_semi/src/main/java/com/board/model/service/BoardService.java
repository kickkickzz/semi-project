package com.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.board.model.dao.BoardDao;
import com.board.model.vo.Board;

public class BoardService {
	private BoardDao dao=new BoardDao();

	public ArrayList<Board> selectList() {
		Connection conn= getConnection();
		ArrayList<Board> list= new BoardDao().selectList(conn);
		close(conn);
		return list;
	}

}

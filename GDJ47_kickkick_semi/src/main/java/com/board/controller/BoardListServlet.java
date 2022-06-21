package com.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/boardlist.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Board> list = new BoardService().selectList();

		// 엉뚱한 데이터가 나옴 삭제예정 or 더 알아보고 적용
//		response.setContentType("application/json; charset=UTF-8");
//		Gson gson=new GsonBuilder().setDateFormat("yyyy년  MM월 dd일").create();
//		gson.toJson(list, response.getWriter());

		
		request.setAttribute("list",list);
		request.getRequestDispatcher("/views/board/boardList.jsp").forward(request,response);
		 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

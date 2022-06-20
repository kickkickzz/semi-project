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
import com.board.model.vo.PageInfo;
//clear
@WebServlet("/showBoardList.do")
public class ShowBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bs= new BoardService();
		
		int listCount;
		int currentPage;
		int pageLimit;	//한 페이지에 표시되는 페이지 수
		int boardLimit;	//한 페이지당 게시물 수
		int maxPage;
		int startPage;
		int endPage;
		
		listCount= bs.getBoardListCount(); //게시물 수
		
		currentPage=1;
		if(request.getParameter("currentPage")!=null) {
			currentPage= Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit=10;
		boardLimit=10;
		
		//마지막 페이지
		maxPage=(int)Math.ceil((double)listCount/boardLimit);
		
		//시작 페이지
		startPage=((currentPage-1)/pageLimit)*pageLimit+1;
		endPage= (startPage+pageLimit)-1;
		if(maxPage<endPage) {
			endPage=maxPage;
		}
		
		PageInfo pi=new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Board>boardList= bs.selectBoardList(pi);
		
		String page=null;
		if(boardList!=null) {
			page="/views/board/boardList.jsp";
			request.setAttribute("boardLists", boardList);
			request.setAttribute("pi", pi);
		}else {
			page="/views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

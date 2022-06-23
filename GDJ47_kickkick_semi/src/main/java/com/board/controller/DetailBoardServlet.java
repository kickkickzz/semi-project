package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;

@WebServlet("/detailBoard.do")
public class DetailBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DetailBoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//boardList.jsp의 공지사항 게시물 테이블에서 선택한 게시물
		int bId= Integer.parseInt(request.getParameter("bId"));
		
		BoardService bService= new BoardService();
		
		// bId에 해당하는 Board를 선택한다.
		Board board= bService.selectBoard(bId);
		
		String page=null;
		if(board!=null) {
			// bId에 해당하는 Board가 존재한다면
			// bId에 해당하는 Attachment를 가져온다.
			request.setAttribute("board", board);
			page="/views/board/boardDetail.jsp";
			
		}else {
			// bId에 해당하는 Board가 존재하지 않는다면
			request.setAttribute("msg", "공지게시판 상세보기에 실패하였습니다.");
			page="/views/common/errorPage.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

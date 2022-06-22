package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		//1. 클라이트가 요청한 게시글번호에 해당하는 데이터를 DB에서 가져옴
		int boardNo=Integer.parseInt(request.getParameter("no"));
		
		//Board b=new BoardService().selectBoard(boardNo);
		//읽은게시글인지 아닌지 확인하기
		boolean isRead=false;
		String preBoard="";//이전게시글과 새로운 게시글을 누적시킬 변수
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie c : cookies) {
				String name=c.getName();//쿠키key값
				String value=c.getValue();//value
				if(name.equals("readboard")) {
					preBoard=value;
					if(preBoard.contains("|"+boardNo+"|")) {
						isRead=true;
						break;
					}
				}
			}
		}
		if(!isRead) {
			Cookie c=new Cookie("readboard",preBoard+"|"+boardNo+"|");
			c.setMaxAge(24*60*60);//1일동안유지
			response.addCookie(c);
		}
		
		
		request.setAttribute("board", new BoardService().selectBoard(boardNo,isRead));
		request.getRequestDispatcher("/views/board/detailView.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

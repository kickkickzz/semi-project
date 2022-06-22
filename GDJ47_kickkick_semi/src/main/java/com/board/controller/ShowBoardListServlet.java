package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;

@WebServlet("/showBoardList.do")
public class ShowBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. DB에 저장된 board데이터를 출력
		//2. 각 페이지에 맞게 출력하기
		int cPage;
		int numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerpage=5;
		}
		List<Board> boards=new BoardService().selectBoardList(cPage, numPerpage);
		int totalBoard=new BoardService().selectBoardCount();
		int totalPage=(int)Math.ceil((double)totalBoard/numPerpage);
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href="+request.getRequestURL()
					+"?cPage="+(pageNo-1)
					+"&numPerpage="+numPerpage+">[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href="+request.getRequestURL()
				+"?cPage="+(pageNo)
				+"&numPerpage="+numPerpage+">"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href="+request.getRequestURL()
			+"?cPage="+(pageNo)
			+"&numPerpage="+numPerpage+">[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("boards", boards);
		
		request.getRequestDispatcher("/views/board/boardList.jsp")
		.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

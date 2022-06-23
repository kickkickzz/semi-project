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
import com.board.model.vo.PageInfo;

@WebServlet("/showBoardList.do")
public class ShowBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardService bService= new BoardService();
		
		int listCount; 		//총 게시글 개수
		int currentPage; 	//현재 페이지번호
		int pageLimit; 		//한페이지에 표시될 페이지수
		int boardLimit; 	//한페이지에 보여줄 게시물 개수
		int maxPage;		//현재 속한 페이지범위에서 가장 마지막 페이지번호
		int startPage;		//현재속한 페이지범위에서 맨처음 페이지번호
		int endPage;		//가장 마지막 페이지번호
		
		listCount= bService.getBoardListCount(); //게시물의 개수를 불러온다.
		
		currentPage=1;
		if(request.getParameter("currentPage")!=null) {
			//현재페이지가 null이 아니라면..
			currentPage= Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit=10; //한페이지에 보여줄수있는 페이지범위 10
		boardLimit=10;// 한페이지에 보여줄수 있는 게시물개수 10개
		
		//마지막 페이지
		maxPage=(int)Math.ceil((double)listCount/boardLimit);
		
		//시작페이지
		startPage=((currentPage-1)/pageLimit)*pageLimit+1;
		endPage= (startPage+pageLimit)-1;
		if(maxPage<endPage) {
			endPage=maxPage;
		}
		
		PageInfo pi=new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		List<Board>boardList= bService.selectBoardList(pi);
		
		
		String page=null;
		if(boardList!=null) {
			page="/views/board/boardList.jsp";
			request.setAttribute("boardLists", boardList);
			request.setAttribute("pi", pi);
		}else {
			page="/views/common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 게시판 조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
//		//1. DB에 저장된 board데이터를 출력
//		//2. 각 페이지에 맞게 출력하기
//		int cPage;
//		int numPerpage;
//		try {
//			cPage=Integer.parseInt(request.getParameter("cPage"));
//		}catch(NumberFormatException e) {
//			cPage=1;
//		}
//		try {
//			numPerpage=Integer.parseInt(request.getParameter("numPerPage"));
//		}catch(NumberFormatException e) {
//			numPerpage=5;
//		}
//		List<Board> boards=new BoardService().selectBoardList(cPage, numPerpage);
//		int totalBoard=new BoardService().selectBoardCount();
//		int totalPage=(int)Math.ceil((double)totalBoard/numPerpage);
//		
//		int pageBarSize=5;
//		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
//		int pageEnd=pageNo+pageBarSize-1;
//		
//		String pageBar="";
//		if(pageNo==1) {
//			pageBar+="<span>[이전]</span>";
//		}else {
//			pageBar+="<a href="+request.getRequestURL()
//					+"?cPage="+(pageNo-1)
//					+"&numPerpage="+numPerpage+">[이전]</a>";
//		}
//		
//		while(!(pageNo>pageEnd||pageNo>totalPage)) {
//			if(cPage==pageNo) {
//				pageBar+="<span>"+pageNo+"</span>";
//			}else {
//				pageBar+="<a href="+request.getRequestURL()
//				+"?cPage="+(pageNo)
//				+"&numPerpage="+numPerpage+">"+pageNo+"</a>";
//			}
//			pageNo++;
//		}
//		
//		if(pageNo>totalPage) {
//			pageBar+="<span>[다음]</span>";
//		}else {
//			pageBar+="<a href="+request.getRequestURL()
//			+"?cPage="+(pageNo)
//			+"&numPerpage="+numPerpage+">[다음]</a>";
//		}
//		
//		request.setAttribute("pageBar", pageBar);
//		request.setAttribute("boards", boards);
//		
//		request.getRequestDispatcher("/views/board/boardList.jsp")
//		.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

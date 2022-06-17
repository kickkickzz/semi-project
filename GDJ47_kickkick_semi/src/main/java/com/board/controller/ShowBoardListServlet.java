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

@WebServlet("/showBoardList.do")
public class ShowBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bs= new BoardService();
		
		int listCount; 		//�� �Խñ� ����
		int currentPage; 	//���� ��������ȣ
		int pageLimit; 		//���������� ǥ�õ� ��������
		int boardLimit; 	//���������� ������ �Խù� ����
		int maxPage;		//���� ���� �������������� ���� ������ ��������ȣ
		int startPage;		//������� �������������� ��ó�� ��������ȣ
		int endPage;		//���� ������ ��������ȣ
		
		listCount= bs.getBoardListCount(); //�Խù��� ������ �ҷ��´�.
		
		currentPage=1;
		if(request.getParameter("currentPage")!=null) {
			//������������ null�� �ƴ϶��..
			currentPage= Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit=10; //���������� �����ټ��ִ� ���������� 10
		boardLimit=10;// ���������� �����ټ� �ִ� �Խù����� 10��
		
		//������ ������
		maxPage=(int)Math.ceil((double)listCount/boardLimit);
		
		//����������
		startPage=((currentPage-1)/pageLimit)*pageLimit+1;
		endPage= (startPage+pageLimit)-1;
		if(maxPage<endPage) {
			endPage=maxPage;
		}
		
		PageInfo pi=new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Board>boardList= bs.selectBoardList(pi);
		
		
//		ArrayList<Board> boardList= bService.selectBoardList();
		String page=null;
		if(boardList!=null) {
			page="/views/board/boardList.jsp";
			request.setAttribute("boardLists", boardList);
			request.setAttribute("pi", pi);
		}else {
			page="/views/common/errorPage.jsp";
			request.setAttribute("msg", "�������� �Խ��� ��ȸ�� �����Ͽ����ϴ�.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

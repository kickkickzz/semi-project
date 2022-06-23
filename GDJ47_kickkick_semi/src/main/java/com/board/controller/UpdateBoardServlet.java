package com.board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.board.model.service.BoardService;
import com.board.model.vo.Board_re;
import com.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamedPolicy;

@WebServlet("/updateBoard.do")
public class UpdateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateBoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result=0;
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize= 1024*1024*10; //10MB
			String root=getServletContext().getRealPath("/upload/");
			String path=root+"/board"; //경로확인 할것
			
			MultipartRequest multiRequest=new MultipartRequest(request, path, maxSize, "UTF-8", new MyFileRenamedPolicy() );
			
			
			//폼에서 입력받은 값들을 모두 갖고온다.
			//게시글번호: bId
			int bId= Integer.parseInt(multiRequest.getParameter("bId"));
			
			//작성자 이메일, 이름.
			String email=((Member) request.getSession().getAttribute("loginMember")).getEmail();
			String name=((Member) request.getSession().getAttribute("loginMember")).getName();
			
			//제목:title
			String title=multiRequest.getParameter("title");
			
			//내용: content
			String content=multiRequest.getParameter("content");
			
			Board_re board=new Board_re();
			board.setBoardNum(bId);
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setBoardWriter(name);
			board.setBoardWriterEmail(email);

			//수정날짜로 갱신
			Date date=new Date(new GregorianCalendar().getTimeInMillis());
			board.setBoardDate(date);
		
			//이미지 보류
			
			System.out.println("bId=> "+bId);
			System.out.println("UpdateBoard/updateBoard.bo\n게시판=>"+board);
			if(result>0) {
				response.sendRedirect("detailBoard.bo?bId="+bId);
			}else {
				request.setAttribute("msg", "공지사항 게시판 게시글 수정에 실패하였습니다.");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

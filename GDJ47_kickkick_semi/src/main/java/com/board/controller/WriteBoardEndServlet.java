package com.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamedPolicy;
import common.exception.LoginCheckException;

@WebServlet("/writeBoard.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new LoginCheckException("enctype에러");
		}else {
			String root=getServletContext().getRealPath("/");
			String path=root+"/board"; //경로확인 할것
//			File f=new File(path);
//			if(!f.exists()) f.mkdirs();
			
			MultipartRequest mr=new MultipartRequest(request,path,(1024*1024*10),"UTF-8",
					new MyFileRenamedPolicy());

			Board b= new Board();
				b.setBoardTitle(mr.getParameter("boardTitle"));
				b.setBoardContent(mr.getParameter("boardContent"));
				b.setBoardWriter(mr.getParameter("boardWriter"));
//				b.setBoardOriginalFilename(mr.getOriginalFileName("upfile"));
//				b.setBoardRenamedFilename(mr.getFilesystemName("upfile"));
			
			int result=new BoardService().insertBoard(b);
			String msg="",loc="";
			
			if(result>0) {
				msg="게시글이 등록되었습니다.";
				loc="/showBoardList.do";
			}else {
				msg="게시글 등록실패!";
				loc="/writeBoard.do";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

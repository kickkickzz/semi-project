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
//clear
@WebServlet("/board/writeBoardEnd.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("파일 업로드 안 됨");
		}else {
			String root=getServletContext().getRealPath("/upload/");
			String path=root+"/board";
			File f=new File(path);
			if(!f.exists()) f.mkdirs();
			
			MultipartRequest mr=new MultipartRequest(request,path,(1024*1024*10),"UTF-8",
					new MyFileRenamedPolicy());

			Board b=Board.builder()
					.boardTitle(mr.getParameter("boardTitle"))
					.boardContent(mr.getParameter("boardContent"))
					.boardWriter(mr.getParameter("boardWriter"))
					.boardOriginalFilename(mr.getOriginalFileName("upfile"))
					.boardRenamedFilename(mr.getFilesystemName("upfile"))
					.build();
			
			int result=new BoardService().insertBoard(b);
			String msg="",loc="";
			
			if(result>0) {
				msg="게시글이 등록되었습니다.";
				loc="/showBoardList.do";
			}else {
				msg="게시글 등록실패!";
				loc="/board/boardWrite.do";
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

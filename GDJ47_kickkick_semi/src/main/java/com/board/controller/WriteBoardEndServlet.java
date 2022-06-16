package com.board.controller;

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
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/writeBoard.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지사항 데이터 저장, 파일 업로드
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지사항 등록에 실패하였습니다.");
			request.setAttribute("loc", "/writeBoard.do");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}else {
			String saveBoardPath=request.getServletContext().getRealPath("/resources/storage/board_img/"); //공지사항 게시판 파일 저장소
			int maxSize=1024*1024*10; //10MB;
			String encoding="UTF-8";
			DefaultFileRenamePolicy dfp=new DefaultFileRenamePolicy();
			
			// BoardImgFileRenamePolicy() => 공지사항게시판 이미지 이름 변경 방법.
			MultipartRequest mr= new MultipartRequest(request, saveBoardPath, maxSize,"UTF-8",dfp);
			Board n=Board.builder().boardTitle(mr.getParameter("title"))
					.boardWriter(mr.getParameter("writer"))
					.boardContent(mr.getParameter("content"))
					.boardImgPath(mr.getFilesystemName("upfile"))
					.build();
			System.out.println(n);
			
			//DB에 데이터 저장
			int result=new BoardService().insertBoard(n);
			String msg="";
			String loc="";
			if(result>0) {
				msg="공지사항 등록 완료!";
				loc="/writeBoard.do";
			}else {
				msg="공지사항 등록 실패!";
				loc="/writeBoard.do";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

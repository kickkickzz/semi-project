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
		//�������� ������ ����, ���� ���ε�
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "�������� ��Ͽ� �����Ͽ����ϴ�.");
			request.setAttribute("loc", "/writeBoard.do");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}else {
			String saveBoardPath=request.getServletContext().getRealPath("/resources/storage/board_img/"); //�������� �Խ��� ���� �����
			int maxSize=1024*1024*10; //10MB;
			String encoding="UTF-8";
			DefaultFileRenamePolicy dfp=new DefaultFileRenamePolicy();
			
			// BoardImgFileRenamePolicy() => �������װԽ��� �̹��� �̸� ���� ���.
			MultipartRequest mr= new MultipartRequest(request, saveBoardPath, maxSize,"UTF-8",dfp);
			Board n=Board.builder().boardTitle(mr.getParameter("title"))
					.boardWriter(mr.getParameter("writer"))
					.boardContent(mr.getParameter("content"))
					.boardImgPath(mr.getFilesystemName("upfile"))
					.build();
			System.out.println(n);
			
			//DB�� ������ ����
			int result=new BoardService().insertBoard(n);
			String msg="";
			String loc="";
			if(result>0) {
				msg="�������� ��� �Ϸ�!";
				loc="/writeBoard.do";
			}else {
				msg="�������� ��� ����!";
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

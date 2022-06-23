package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import com.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

import common.BoardImgFileRenamePolicy;

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
			
			MultipartRequest multiRequest=new MultipartRequest(request, path, maxSize, "UTF-8", new BoardImgFileRenamePolicy() );
			File file=new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			String saveFile=null;
			String originFile=null;
			Enumeration<String> files= multiRequest.getFileNames();
			if(files.hasMoreElements()) {
				String name= files.nextElement();
				
				if(multiRequest.getFilesystemName(name)!=null) {
					saveFile=multiRequest.getFilesystemName(name);
					originFile=multiRequest.getOriginalFileName(name);
				}
			}
			
			//폼에서 입력받은 값들을 모두
			int bId= Integer.parseInt(multiRequest.getParameter("bId"));
			String email=((Member) request.getSession().getAttribute("loginMember")).getEmail();
			String name=((Member) request.getSession().getAttribute("loginMember")).getName();
			String title=multiRequest.getParameter("title");
			String content=multiRequest.getParameter("content");
			
			Board board=new Board();
			board.setBoardNum(bId);
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setBoardWriter(name);
			board.setBoardWriterEmail(email);

			//수정날짜 갱신
			Date date=new Date(new GregorianCalendar().getTimeInMillis());
			board.setBoardDate(date);
		
			//이미지: img
			BoardAttachment bat= new BoardAttachment();
			bat.setFilePath(path);
			bat.setOriginName(originFile);
			bat.setChangeName(saveFile);
			bat.setUpdateDate(date);
			
			System.out.println("bId=> "+bId);
			System.out.println("UpdateBoard/updateBoard.do\n게시판=>"+board);
			if(result>0) {
				response.sendRedirect("detailBoard.do?bId="+bId);
			}else {
				File failedFile=new File(path+ saveFile);
				failedFile.delete();
				request.setAttribute("msg", "공지사항 게시판 게시글 수정에 실패하였습니다.");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

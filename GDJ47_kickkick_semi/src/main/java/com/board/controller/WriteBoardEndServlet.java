package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.board.model.service.BoardService;
import com.board.model.vo.Board_re;
import com.oreilly.servlet.MultipartRequest;

import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import common.BoardImgFileRenamePolicy;
import com.member.model.vo.Member;

@WebServlet("/writeBoard.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize=1024*1024*10; //10MB;
			String root= request.getSession().getServletContext().getRealPath("/");
			String saveBoardPath= root+"/storage/board_img/"; //공지사항 게시판 파일 저장소
			
			// BoardImgFileRenamePolicy() => 공지사항게시판 이미지 이름 변경 방법.
			MultipartRequest multiRequest= new MultipartRequest(request, saveBoardPath, maxSize,"UTF-8", new BoardImgFileRenamePolicy() );
			File file=new File(saveBoardPath);
			
			// saveBoadPath에 해당하는 디렉토리가 존재하지 않는다면
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//바뀐 파일이름을 저장하는 ArrayList
			ArrayList<String> saveFiles = new ArrayList<String>();
			
			//원본파일이름을 저장하는 ArrayList
			ArrayList<String> originFiles= new ArrayList<String>();
			
			//getFileName(): 폼에서 전송된 File의 이름을 위의 규정대로 변환
			Enumeration<String> files= multiRequest.getFileNames();
			while(files.hasMoreElements()) {
				String name= files.nextElement();
				
				if(multiRequest.getFilesystemName(name)!=null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
				}
			}
			
			// 입력한 데이터를 String 형태로 변환
			//제목 "title"
			String title= multiRequest.getParameter("title"); //제목-title - BOARD_TITLE
			String content= multiRequest.getParameter("content"); //내용-content - BOARD_CONTENT
			String email=((Member) request.getSession().getAttribute("loginMember")).getEmail();
			String name=((Member) request.getSession().getAttribute("loginMember")).getName(); //이름- BOARD_WRITER
			
			Board board= new Board();
			board.setBoardTitle(title);
			board.setBoardWriter(name);
			board.setBoardContent(content);
			board.setBoardWriterEmail(email);
			
			ArrayList<BoardAttachment> fileList= new ArrayList<BoardAttachment>();
			for(int i=originFiles.size()-1; i>=0; i--) {
				BoardAttachment bat= new BoardAttachment();
				bat.setFilePath(saveBoardPath);
				bat.setOriginName(originFiles.get(i));
				bat.setChangeName(saveFiles.get(i));
				
				//이미지이름
				board.setBoardImgPath(originFiles.get(i));
				
				fileList.add(bat);
			}
			
			System.out.println("/insertBoard.do=> "+board); //board출력
			
			int result= new BoardService().insertBoard(board, fileList);
			if(result>0) {
				response.sendRedirect("showBoardList.do");
			}else {
				for(int i=0; i<saveFiles.size(); i++) {
					File failedFile=new File(saveBoardPath+saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "공지 게시판 게시글 등록에 실패하였습니다.");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
			
		}else {
			request.setCharacterEncoding("UTF-8");
			String title=request.getParameter("title");
			String email=((Member)request.getSession().getAttribute("loginMember")).getEmail();
			String content= request.getParameter("content");
			String name=((Member)request.getSession().getAttribute("loginMember")).getName();
			
			Board board= new Board();
			board.setBoardContent(content);
			board.setBoardTitle(title);
			board.setBoardWriter(name);
			board.setBoardWriterEmail(email);
			
			int result=new BoardService().insertBoard(board, null);
			if(result>0) {
				response.sendRedirect("showBoardList.do");
				
			}else {
				request.setAttribute("msg", "공지사항 등록에 실패하였습니다.");
				RequestDispatcher view = request.getRequestDispatcher("/views/common/errorPage.jsp");
				view.forward(request, response);
			}
		}
		
		
//		if(!ServletFileUpload.isMultipartContent(request)) {
//			throw new LoginCheckException("enctype에러");
//		}else {
//			String root=getServletContext().getRealPath("/");
//			String path=root+"/board"; //경로확인 할것
////			File f=new File(path);
////			if(!f.exists()) f.mkdirs();
//			
//			MultipartRequest mr=new MultipartRequest(request,path,(1024*1024*10),"UTF-8",
//					new MyFileRenamedPolicy());
//
//			Board_re b= new Board_re();
//				b.setBoardTitle(mr.getParameter("boardTitle"));
//				b.setBoardContent(mr.getParameter("boardContent"));
//				b.setBoardWriter(mr.getParameter("boardWriter"));
////				b.setBoardOriginalFilename(mr.getOriginalFileName("upfile"));
////				b.setBoardRenamedFilename(mr.getFilesystemName("upfile"));
//			
//			int result=new BoardService().insertBoard(b);
//			String msg="",loc="";
//			
//			if(result>0) {
//				msg="게시글이 등록되었습니다.";
//				loc="/showBoardList.do";
//			}else {
//				msg="게시글 등록실패!";
//				loc="/writeBoard.do";
//			}
//			request.setAttribute("msg", msg);
//			request.setAttribute("loc", loc);
//			request.getRequestDispatcher("/views/common/msg.jsp")
//			.forward(request, response);
//			
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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
import com.board.model.vo.Board;
import com.board.model.vo.BoardAttachment;
import com.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

import common.BoardImgFileRenamePolicy;

@WebServlet("/writeBoard.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize=1024*1024*10; //10MB;
			String root= request.getSession().getServletContext().getRealPath("/"); // \WebContent\
			String saveBoardPath= root+"/resources/storage/board_img/"; //�������� �Խ��� ���� �����
			
			// BoardImgFileRenamePolicy() => �������װԽ��� �̹��� �̸� ���� ���.
			MultipartRequest multiRequest= new MultipartRequest(request, saveBoardPath, maxSize,"UTF-8", new BoardImgFileRenamePolicy() );
			File file=new File(saveBoardPath);
			
			// saveBoadPath�� �ش��ϴ� ���丮�� �������� �ʴ´ٸ�
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//�ٲ� �����̸��� �����ϴ� ArrayList
			ArrayList<String> saveFiles = new ArrayList<String>();
			
			//���������̸��� �����ϴ� ArrayList
			ArrayList<String> originFiles= new ArrayList<String>();
			
			//getFileName(): ������ ���۵� File�� �̸��� ���� ������� ��ȯ
			Enumeration<String> files= multiRequest.getFileNames();
			while(files.hasMoreElements()) {
				String name= files.nextElement();
				
				if(multiRequest.getFilesystemName(name)!=null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
				}
			}
			
			// �Է��� �����͸� String ���·� ��ȯ
			String title= multiRequest.getParameter("title"); //����-title - BOARD_TITLE
			String content= multiRequest.getParameter("content"); //����-content - BOARD_CONTENT
			String email=((Member) request.getSession().getAttribute("loginUser")).getEmail();
			String name=((Member)request.getSession().getAttribute("loginUser")).getName(); //�̸�- BOARD_WRITER
			
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
				
				//�̹����̸�
				board.setBoardImgPath(originFiles.get(i));
				
				fileList.add(bat);
			}
			
			System.out.println("/insertBoard.bo=> "+board); //board���
			
			int result= new BoardService().insertBoard(board, fileList);
			if(result>0) {
				response.sendRedirect("showBoardList.bo");
			}else {
				for(int i=0; i<saveFiles.size(); i++) {
					File failedFile=new File(saveBoardPath+saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "���� �Խ��� �Խñ� ��Ͽ� �����Ͽ����ϴ�.");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
			
		}else {
			request.setCharacterEncoding("UTF-8");
			String title=request.getParameter("title");
			String email=((Member)request.getSession().getAttribute("loginUser")).getEmail();
			String content= request.getParameter("content");
			String name=((Member)request.getSession().getAttribute("loginUser")).getName();
			
			Board board= new Board();
			board.setBoardContent(content);
			board.setBoardTitle(title);
			board.setBoardWriter(name);
			board.setBoardWriterEmail(email);
			
			int result=new BoardService().insertBoard(board, null);
			if(result>0) {
				response.sendRedirect("showBoardList.bo");
				
			}else {
				request.setAttribute("msg", "�������� ��Ͽ� �����Ͽ����ϴ�.");
				RequestDispatcher view = request.getRequestDispatcher("/views/common/errorPage.jsp");
				view.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

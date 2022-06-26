package com.team.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.team.model.service.TeamService;

import common.MyFileRenamedPolicy;

/**
 * Servlet implementation class TeamUpdateServlet
 */
@WebServlet("/teamUpdate.do")
public class TeamUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userId = ((Member)request.getSession().getAttribute("loginMember")).getEmail();
//		 
//	    String root = request.getSession().getServletContext().getRealPath("/");
//	    String savePath = root + "resources/storage/"+userId+"/team_img/";
//	      
//	    
//	      File f = new File(savePath);
//	      if(!f.exists()) {
//	         f.mkdirs();
//	      }
//
//	      int maxSize = 1024 * 1024 * 10;
//	      
//	    MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamedPolicy());
//	      
//	    
		String teamMark = request.getParameter("fileName");
		String teamAge = request.getParameter("team_age");
		String name = request.getParameter("name");
		String teamGender = request.getParameter("team_gender");
		String region = request.getParameter("region");
		String email = request.getParameter("email");
		System.out.println(teamMark);
		int result = new TeamService().teamUpdate(email,teamMark,name,teamAge,teamGender,region);
		
		String msg="",loc="";
		if(result>0) {
			msg += "팀 정보가 수정되었습니다.";
			loc +="/member/myteam.do?email="+email;
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}else {
			msg += "팀 정보 수정에 실패하였습니다.";
			loc += "/member/myteam.do?email="+email;
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
		request.getRequestDispatcher("/views/address/updateAddress.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

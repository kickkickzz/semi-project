package com.team.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.model.service.TeamService;

/**
 * Servlet implementation class deleteTeammemberServlet
 */
@WebServlet("/deleteteammember.do")
public class deleteTeammemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteTeammemberServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String email = request.getParameter("supporter_email");
		String email =  "wow7@daum.net";//나중에 할떄는 이거 주석처리
		int result = new TeamService().deleteTeammember(email);
		System.out.println(result);
		if(result>0) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

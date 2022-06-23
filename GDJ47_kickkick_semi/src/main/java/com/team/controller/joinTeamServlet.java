package com.team.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.model.service.TeamService;
import com.team.model.vo.Team;

/**
 * Servlet implementation class joinTeamServlet
 */
@WebServlet("/joinTeam.do")
public class joinTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String email = request.getParameter("email"); //로그인한 이메일
		
		String email = "wer2@daum.net";
		
		//팀코드를 받아야함
		List<Team> result = new TeamService().joinTeam(email); //로그인한 이메일하고 가입한 팀에 리더가 있으면 
		System.out.println(result);
		if(!result.isEmpty()) {
			request.setAttribute("result", result);
			request.getRequestDispatcher("/views/member/joinTeam.jsp").forward(request, response);
		}
		else { 
			request.setAttribute("result", result);
		 	request.getRequestDispatcher("/views/member/joinTeam.jsp").forward(request,response); 
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


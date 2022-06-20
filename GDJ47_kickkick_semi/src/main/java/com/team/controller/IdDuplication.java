package com.team.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.model.service.TeamService;
import com.team.model.vo.Team;

/**
 * Servlet implementation class IdDuplication
 */
@WebServlet(name = "IdDuplicationServlet", urlPatterns = { "/team/idDuplicate.do" })
public class IdDuplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdDuplication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String team_name=request.getParameter("team_name");
		System.out.println(request.getParameter("team_name"));
		
		Team t=new TeamService().selectTeamByName(team_name);
		System.out.println(t);
		
		request.setAttribute("result",t==null?true:false);
		
	
		request.getRequestDispatcher("/views/team/idduplicate.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

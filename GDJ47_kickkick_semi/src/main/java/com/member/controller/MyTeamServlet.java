package com.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.vo.Member;
import com.team.model.service.TeamService;
import com.team.model.vo.Team;

/**
 * Servlet implementation class MyTeamServlet
 */
@WebServlet("/member/myteam.do")
public class MyTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//where email=? (팀리더이메일) 즉 팀 유저 한명당 팀을 하나밖에 만들지 못함 그 유저한명이 만들면 팀리더가 됨
		//팀 가입은 3개까지 가능
		
		
		Member m = (Member)request.getSession().getAttribute("loginMember");
		System.out.println(m);
		String email = m.getEmail();
		//Team team = new TeamService().getTeamLeader(email);
		
		//ArrayList<Team> teamArr = new TeamService().getTeam(userId);
		
		//request.setAttribute("team", team);
		//request.setAttribute("teamArr", teamArr);

		request.getRequestDispatcher("/views/member/myteam.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

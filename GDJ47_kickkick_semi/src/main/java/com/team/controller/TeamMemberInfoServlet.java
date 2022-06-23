package com.team.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.match.model.service.MatchService;
import com.match.model.vo.Match;
import com.team.model.service.TeamService;
import com.team.model.vo.Team;
import com.team.model.vo.TeamMemberInfo;

/**
 * Servlet implementation class TeamMemberInfo
 */
@WebServlet("/team/teamMemberInfo.do")
public class TeamMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamMemberInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String team_code=request.getParameter("team_code");
		
		
		Team teamInfo=new TeamService().selectTeam(team_code);
		request.setAttribute("teamInfo", teamInfo);
		
		ArrayList<TeamMemberInfo> teamMemberArr = new TeamService().selectTeamMemberList(team_code);
		ArrayList<TeamMemberInfo> list = new TeamService().selectTeamApplication(team_code);
		ArrayList<Match> matchlist=new TeamService().selectMatch(team_code);
		List<Team> t = new ArrayList<Team>();
		for(Match m : matchlist) {
			Team  t2= new MatchService().selectteamcode2(m.getTeam_code());
			t.add(t2);
		}
		
		
		
		request.setAttribute("teamMemberArr", teamMemberArr);
		request.setAttribute("teamEnterMember", list);
		request.setAttribute("matchlist", t);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/team/team_info.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

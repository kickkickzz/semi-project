package com.team.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int cPage;
		try{
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		
		int numPerpage=5;
		
		Team teamInfo=new TeamService().selectTeam(team_code);
		request.setAttribute("teamInfo", teamInfo);
		
		ArrayList<TeamMemberInfo> teamMemberArr = new TeamService().selectTeamMemberList(team_code,cPage,numPerpage);
		
		
		System.out.println(teamInfo);
		
		System.out.println(teamMemberArr);
		
		
		
		Calendar current = Calendar.getInstance();
		int year = current.get(Calendar.YEAR);
		for (int i = 0; i < teamMemberArr.size(); i++) {
		
			Date from = teamMemberArr.get(i).getBirthday();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy");
			String to = transFormat.format(from);
			int birth = Integer.parseInt(to);
			teamMemberArr.get(i).setAge((year-birth)+1);

		}
		
		
		
		int totalData=new TeamService().selectTeamMemberCount(team_code);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		
		int pageBarSize=5;
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
					+"/team.do?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"<span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()
				+"/team.do?cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
			+"/team.do?cPage="+pageNo+"'>[다음]</a>";
		}

		request.setAttribute("teamMemberArr", teamMemberArr);
		request.setAttribute("pageBar", pageBar);

		
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

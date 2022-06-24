package com.team.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservation.model.service.ReservationService;
import com.reservation.model.vo.Stadium;
import com.team.model.service.TeamService;
import com.team.model.vo.Team;

/**
 * Servlet implementation class TeamMainServelet
 */
@WebServlet("/team.do")
public class TeamMainServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamMainServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int cPage;
		int numPerpage;
		try{
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=10;
		}
		
		List<Team> list=new TeamService().selectTeamList(cPage, numPerpage);
		request.setAttribute("list", list);
		
		
		int totalData=new TeamService().selectTeamCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		
		int pageBarSize=10;
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		
		String pageBar="";

		
		
		
		
		if(pageNo==1) {
			
		}else {
			pageBar+="<a href='"+request.getContextPath()
					+"/team.do?cPage="+(pageNo-1)+"'></a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
				
			}else {
				pageBar+="<a href='"+request.getContextPath()+
						"/team.do?cPage="+(pageNo)
						+"&numPerPage="+numPerpage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
		
		}else {
			pageBar+="<a href='"+request.getContextPath()
			+"/team.do?cPage="+(pageNo)
					+"&numPerPage="+numPerpage+"'></a>";
		}
		
		
		
		request.setAttribute("pageBar", pageBar);

		
		RequestDispatcher view = request.getRequestDispatcher("/views/team/team_main.jsp");
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

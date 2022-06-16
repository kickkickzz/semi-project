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
		
		/*
		 * int cPage; try{ cPage=Integer.parseInt(request.getParameter("cPage"));
		 * }catch(NumberFormatException e) { cPage=1; } int numPerpage=5;
		 * 
		 * //DB에 저장되어있는 Member테이블의 모든데이터를 가져와야함. List<Team> list=new
		 * TeamService().searchAll(cPage, numPerpage);
		 * 
		 * request.setAttribute("list", list);
		 * 
		 * 
		 * //사용자가 원하는 페이지를 요청할 수 있게 페이지바를 만들어보자 //1. 전체 페이지수 int totalData=new
		 * TeamService().selectTeamCount(); int
		 * totalPage=(int)Math.ceil((double)totalData/numPerpage);
		 * 
		 * //2. 출력할 페이지번호의 갯수 정하기 int pageBarSize=5;
		 * 
		 * //3. 출력할 페이지번호 시작, 끝 정하기 int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		 * int pageEnd=pageNo+pageBarSize-1;
		 * 
		 * 
		 * //4. pageBar생성하기 String pageBar=""; if(pageNo==1) {
		 * pageBar+="<span>[이전]</span>"; }else {
		 * pageBar+="<a href='"+request.getContextPath()
		 * +"/team.do?cPage="+(pageNo-1)+"'>[이전]</a>"; }
		 * 
		 * while(!(pageNo>pageEnd||pageNo>totalPage)) { if(cPage==pageNo) {
		 * pageBar+="<span>"+pageNo+"<span>"; }else {
		 * pageBar+="<a href='"+request.getContextPath()
		 * +"/team.do?cPage="+pageNo+"'>"+pageNo+"</a>"; } pageNo++; }
		 * 
		 * if(pageNo>totalPage) { pageBar+="<span>[다음]</span>"; }else {
		 * pageBar+="<a href='"+request.getContextPath()
		 * +"/team.do?cPage="+pageNo+"'>[다음]</a>"; }
		 * 
		 * request.setAttribute("pageBar", pageBar);
		 */
		
		
		List<Team> list=new TeamService().selectTeamList();
		
		request.setAttribute("list",list);

		
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

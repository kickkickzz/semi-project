package com.match.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.match.model.service.MatchService;
import com.match.model.vo.Match;

/**
 * Servlet implementation class MatchSearchServlet
 */
@WebServlet("/matchsearch.do")
public class MatchSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("searchType");
		String keyword=request.getParameter("searchKeyword");
		
		if(type.equals("place")) {
			type="branch_address";
		}
		if(type.equals("gender")) {
			type="team_gender";
		}
		if(type.equals("team")) {
			type="team_name";
		}
		if(type.equals("age")) {
			type="team_age";
		}
		if(type.equals("member")) {
			type="stadium_match_member";
		}
		System.out.println(type);
		System.out.println(keyword);
		
		int cPage=0;
		int numPerpage=5;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		List<Match> m = new MatchService().searchMatch(type,keyword,cPage,numPerpage);
		request.setAttribute("matcharr", m);
		
		int totalData = new MatchService().searchMatchCount(type,keyword);
		int totalpage = (int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize = 5;
		
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+
					"/matchsearch.do?cPage="+(pageNo-1)+
					"&numPerPage="+numPerpage+"&searchType="+type+"&searchKeyword="+keyword+"'>[이전]</a>";
					
		}
		while(!(pageNo>pageEnd||pageNo>totalpage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
				
			}else {
				pageBar+="<a href='"+request.getContextPath()+
						"/matchsearch.do?cPage="+(pageNo)
						+"&numPerPage="+numPerpage+"&searchType="+type+"&searchKeyword="+keyword+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalpage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/matchsearch.do?cPage="+(pageNo)
					+"&numPerPage="+numPerpage+"&searchType="+type+"&searchKeyword="+keyword+"'>[다음]</a>";
			
		}
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/match/match.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

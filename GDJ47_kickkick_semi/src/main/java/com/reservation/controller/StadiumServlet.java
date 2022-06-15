package com.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservation.model.service.ReservationService;
import com.reservation.model.vo.Stadium;

/**
 * Servlet implementation class StadiumServlet
 */
@WebServlet("/stadium.do")
public class StadiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StadiumServlet() {
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
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=7;
		}
		List<Stadium> staArr = new ReservationService().selectStadium(cPage,numPerpage);
		request.setAttribute("staArr", staArr);
		
		int totaldata = new ReservationService().selectStadiumCount();
		int totalpage = (int)Math.ceil((double)totaldata/numPerpage);
		int pageBarsize=5;
		int pageNo = ((cPage-1)/pageBarsize)*pageBarsize+1;
		int pageEnd= pageNo+pageBarsize-1;
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+
					"/stadium.do?cPage="+(pageNo-1)+
					"&numPerPage="+numPerpage+"'>[이전]</a>";
					
		}
		while(!(pageNo>pageEnd||pageNo>totalpage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
				
			}else {
				pageBar+="<a href='"+request.getContextPath()+
						"/stadium.do?cPage="+(pageNo)
						+"&numPerPage="+numPerpage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalpage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/stadium.do?cPage="+(pageNo)
					+"&numPerPage="+numPerpage+"'>[다음]</a>";
			
		}
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/stadium/stadium.jsp").forward(request, response);
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

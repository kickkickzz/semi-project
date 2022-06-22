package com.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservation.model.service.ReservationService;

/**
 * Servlet implementation class InsertStadiumServlet
 */
@WebServlet("/insertStadium.do")
public class InsertStadiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStadiumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String stadiumName = request.getParameter("stadiumName");
		String stadiumMatchMember = request.getParameter("stadiumMatchMember");
		String branchNum = request.getParameter("branchNum");
		int startTime = Integer.parseInt(request.getParameter("startTime"));
		int endTime = Integer.parseInt(request.getParameter("endTime"));
		System.out.println(email);
		System.out.println(stadiumName);
		System.out.println(stadiumMatchMember);
		System.out.println(branchNum);
		System.out.println(startTime);
		System.out.println(endTime);
		
		int result = new ReservationService().insertStadium(stadiumName,stadiumMatchMember,branchNum,startTime,endTime);
		System.out.println(result);
		if(result>0) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(result);
		}else {
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

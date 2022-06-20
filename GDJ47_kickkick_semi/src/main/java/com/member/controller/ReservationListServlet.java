package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;
import com.reservation.model.vo.PayHistory;


@WebServlet("/member/reservationlist.do")
public class ReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReservationListServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		List<PayHistory> p = new MemberService().selectpayhistory(email);
		System.out.println(p);
		request.setAttribute("paylist", p);
		
		request.getRequestDispatcher("/views/member/reservationlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

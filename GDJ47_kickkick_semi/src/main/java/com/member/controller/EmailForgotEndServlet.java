package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class EmailForgotEndServlet
 */
@WebServlet("/emailForgotEnd.do")
public class EmailForgotEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailForgotEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		Member m = new MemberService().selectEmail(name,phone);
		
		String email = m.getEmail();
		System.out.println(email);
		String msg="", script="";
		if(m!=null) {
			msg += "찾으실 이메일 : "+email;
			script += "close()";
		}else{
			msg += "조회된 이메일이 없습니다.";
			script += "close()";
		}
		

		request.setAttribute("msg", msg);
		request.setAttribute("script", script);
		request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

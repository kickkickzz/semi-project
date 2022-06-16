package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class LoginMember
 */
@WebServlet("/loginMember.do")
public class LoginMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		
		Member m = new MemberService().LoginMember(email,pwd);
		
		
		//cookie생성
		if(saveId!=null) {
			Cookie cookie = new Cookie("saveId",email);
			cookie.setMaxAge(24*60*60*7);
			response.addCookie(cookie);
		}
		
		//session생성
		if(m!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
			request.getRequestDispatcher(request.getContextPath()).forward(request, response);
		}else {
			request.getRequestDispatcher(request.getContextPath()+"").forward(request, response);			
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

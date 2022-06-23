package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;


@WebServlet(name="extraPassword" , urlPatterns={"/passwordForgotUpdate.do"})
public class PasswordForgotUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordForgotUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey"); //인증키1111
		String AuthenticationKey = request.getParameter("AuthenticationKey");
		String AuthenticationUser = request.getParameter("AuthenticationUser"); //
		System.out.println(AuthenticationKey);
		System.out.println(AuthenticationUser);
		String msg="", loc="";
		if(!AuthenticationKey.equals(AuthenticationUser))
        {
            System.out.println("인증번호 일치하지 않음");
            msg +="인증번호가 일치하지 않습니다.";
            loc +="/passwordForgot.do";
            request.setAttribute("msg", msg);
            request.setAttribute("loc", loc);
            request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
            return;
        }else {
        	System.out.println("인증번호 일치합니다.");
        	String email = request.getParameter("email");
        	//int result = new MemberService().extraPassword(email,AuthenticationUser);
        	int result = new MemberService().extraPassword(email,AuthenticationKey);
        	if(result>0) {
        		msg +="임시비밀번호로 변경되었습니다.";
        		String script = "close()";
        		request.setAttribute("email", email);
        		request.setAttribute("msg", msg);
        		request.setAttribute("script", script);
        		request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
        	}
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

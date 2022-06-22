package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;

/**
 * Servlet implementation class EmailCertificationendEndServlet
 */
@WebServlet("/emailCertificationend.do")
public class EmailCertificationendEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCertificationendEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String AuthenticationKey = request.getParameter("AuthenticationKey");
		String AuthenticationUser = request.getParameter("AuthenticationUser");
		String email = request.getParameter("email");
		System.out.println(email);
		System.out.println(AuthenticationKey);
		System.out.println(AuthenticationUser);
		String msg="", loc="";
		if(!AuthenticationKey.equals(AuthenticationUser))
        {
            msg +="인증번호가 일치하지 않습니다.";
            loc +="/emailCertification.do";
            request.setAttribute("msg", msg);
            request.setAttribute("loc", loc);
            request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
            return;
        }else {
        	System.out.println("인증번호 일치합니다.");
        	msg +="인증번호가 일치합니다. 회원가입을 진행합니다.";
            String script = "close()";
            request.setAttribute("email", email);
        	request.setAttribute("msg", msg);
        	request.setAttribute("script", script);
        	request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
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

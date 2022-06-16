package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;


@WebServlet("/updatepasswordend.do")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdatePasswordEndServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String oriPw = request.getParameter("oriPw");
		String newPw = request.getParameter("newPw");
		System.out.println(oriPw);
		System.out.println(newPw);
		
		Member m = new MemberService().LoginMember(email, oriPw);
		
		String msg="", loc="";
		if(m!=null) {
			//현재비밀번호가 맞음
			int result = new MemberService().updatePassword(email, newPw);
			if(result>0) {
				msg +="비밀번호를 변경하였습니다.";
				loc +="/memberview.do";
			}else {
				msg +="비밀번호 변경에 실패하였습니다.";
				loc +="/updatepassword.do";
			}
		}else {
			//현재비밀번호가 틀림
			msg+="현재비밀번호가 틀립니다.";
			loc+="/updatepassword.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

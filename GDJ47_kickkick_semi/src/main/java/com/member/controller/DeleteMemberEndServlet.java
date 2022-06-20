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
 * Servlet implementation class DeleteMemberEndServlet
 */
@WebServlet(name="deleteMemberEndServlet", urlPatterns={"/deletememberend.do"})
public class DeleteMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd= request.getParameter("oriPw");
		System.out.println(email);
		System.out.println(pwd);
		Member m = new MemberService().LoginMember(email, pwd);
		
		String msg="", loc="";
		if(m!=null) {
			//현재비밀번호가 맞음
			int result = new MemberService().deleteMember(email);
			if(result>0) {
				msg +="회원탈퇴가 완료되었습니다.";
				loc +="/logoutMember.do";
				String script="opener.location.replace('"+request.getContextPath()+"/logoutMember.do');close();";
				request.setAttribute("script",script);
			}else {
				msg +="회원탈퇴에 실패하였습니다.";
				loc +="/deletemember.do?email="+email;// ?email=추가해야함
			}
		}else {
			//현재비밀번호가 틀림
			msg+="현재비밀번호가 틀립니다.";
			loc+="/deletemember.do?email="+email; // ?email=추가해야함
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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

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
 * Servlet implementation class ExtraPasswordServlet
 */
@WebServlet(name="extraPasswordEnd" , urlPatterns={"/extraPasswordEnd.do"})
public class ExtraPasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtraPasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		//String oriPw = request.getParameter("oriPw");
		String newPw = request.getParameter("newPw");
		//System.out.println(oriPw);
		//System.out.println(newPw);
		System.out.println(email); //여기서 지금 email이 안넘어감
		//System.out.println(oriPw);
		Member m = new MemberService().emailCheck(email);
		
		String msg="", loc="";
		if(m!=null) {
			//현재비밀번호가 맞음
			int result = new MemberService().updatePassword(email, newPw);
			if(result>0) {
				msg +="비밀번호를 변경하였습니다.";
				String script = "close()";
				request.setAttribute("script", script);
				//String script="opener.location.replace('"+request.getContextPath()+"/logoutMember.do');close();";
				//request.setAttribute("script", script);
			}else {
				msg +="비밀번호 변경에 실패하였습니다.";
				loc +="/member/updatePassword.do?email="+email;// ?email=추가해야함
			}
		}
//		else {
//			//현재비밀번호가 틀림
//			msg+="현재비밀번호가 틀립니다.";
//			loc+="/member/updatePassword.do?email="+email; // ?email=추가해야함
//		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/msg/exPassword.jsp").forward(request, response); //jsp 하나 만들어야함
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

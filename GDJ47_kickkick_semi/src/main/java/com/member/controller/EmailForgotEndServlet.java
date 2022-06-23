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
		String msg="", script="";
		if(m!=null) {
			String email = m.getEmail();
			String userName = m.getName();
			//System.out.println(email);
			msg += userName+"회원님의 이메일 : "+email;
			script += "close()";
		}else if(m==null){
			msg += "조회된 이메일이 없습니다.";
			script += "close()";
		}
		
//		//3개 입력받으면
//		Member m = new Memberservice().passwordForgot(3개입력받은값);
//		//암호화처리
//		if(m!=null) {
//			임시비밀번호 생성하는 로직
//			임시비밀번호를 업데이트 하는 로직 세우고 
//			if(result>0) {
//				//업데이트 완료
//				password = 
//			}else {
//				msg+="업데이트 실패";
//				loc +="비밀번호 변경하는창으로";
//			}
//		}else {
//			조회된결과가 없습니다.
//		}
		request.setAttribute("msg", msg);
		request.setAttribute("script", script);
		request.getRequestDispatcher("/views/member/searchEndEmail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

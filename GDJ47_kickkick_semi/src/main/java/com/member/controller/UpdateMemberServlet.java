package com.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberEndServlet
 */
@WebServlet("/updatemember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateMemberServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("실행됐다");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String name = request.getParameter("name");

		Member m = Member.builder()
				.email(email)
				.name(name)
				.phone(phone)
				.build();
		
		Date birth = null;
		if(birthday!=null) {
			String[] birthArr = birthday.split("-");
			int year = Integer.parseInt(birthArr[0]);
			int month = Integer.parseInt(birthArr[1])-1;
			int day = Integer.parseInt(birthArr[2]);
			birth = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		}else {
			birth = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		String address = null;
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String address4 = request.getParameter("address4");
		ArrayList<String> addArr = new ArrayList();
		addArr.add(address2);
		addArr.add(address3);
		addArr.add(address4);
		for(int i=0; i<addArr.size(); i++) {
			if(i==2) {
				address  += addArr.get(i);
			}else {
				address += addArr.get(i)+" ";
			}
		}
		
		int result = new MemberService().updateMember(email,birth,phone,address,gender);
		
		String msg="", loc="";
		if(result>0) {
			msg+="회원정보 수정이 완료되었습니다.";
			loc+="";
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
		}else {
			msg+="회원정보 수정에 실패하였습니다.";
			loc+="/memberview.do";
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

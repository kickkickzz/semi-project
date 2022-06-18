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

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class EnrollMemberServlet
 */
@WebServlet(name="enrollMemberServlet", urlPatterns={"/enrollMember.do"})
public class EnrollMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String birthday = request.getParameter("birthday");
		
		//생년월일 받기
		Date birth = null;
		if(birthday!=null) {
			String[] birthArr = birthday.split("-"); // "2022" "11" "12"
			if(birthArr.length>1) {
				int year = Integer.parseInt(birthArr[0]); //2022=year 11=month 12=day
				int month = Integer.parseInt(birthArr[1])-1;
				int day = Integer.parseInt(birthArr[2]);
				birth = new Date(new GregorianCalendar(year, month, day).getTimeInMillis()); //birth=20221112
			}
		}else {
			birth = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		//주소 받기
		String address = "";
		String address2 = request.getParameter("address2"); //주소
		String address3 = request.getParameter("address3");//참고항목
		String address4 = request.getParameter("address4");//상세주소
		ArrayList<String> addArr = new ArrayList();
		addArr.add(address2.trim());
		addArr.add(address3.trim());
		addArr.add(address4.trim()); // addArr = 경기도 광명시 광명4동18-255
		for(int i=0; i<addArr.size(); i++) {
			if(i==2) {
				address  += addArr.get(i);
			}else {
				address += addArr.get(i)+" ";
			}
		}
		System.out.println(birthday);
		System.out.println(address);
		Member m = Member.builder()
				.email(request.getParameter("email"))
				.password(request.getParameter("password"))
				.name(request.getParameter("name"))
				.phone(request.getParameter("phone"))
				.birthday(birth)
				.gender(request.getParameter("gender"))
				.address(address)
				.build();
		
	
		boolean flag = new MemberService().EnrollMember(m);
		if(flag) {
			request.setAttribute("msg", "회원가입에 성공하였습니다!");
			request.setAttribute("loc","");
		}else {
			request.setAttribute("msg", "회원가입에 실패하였습니다!ㅠㅠ");
			request.setAttribute("loc","/loginPage.do");
		}
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
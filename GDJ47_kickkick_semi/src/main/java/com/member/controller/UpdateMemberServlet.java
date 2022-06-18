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
		String address = null;
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday"); //생년월일
		String phone = request.getParameter("phone"); //전화번호
		String address2 = request.getParameter("address2"); //주소
		String address3 = request.getParameter("address3");//참고항목
		String address4 = request.getParameter("address4");//상세주소
		ArrayList<String> addArr = new ArrayList();
		addArr.add(address2);
		addArr.add(address3);
		addArr.add(address4); //주소 참고항목 상세주소가 각각 addArr 배열에 들어감
		for(int i=0; i<addArr.size(); i++) {
			if(i==2) {
				address  += addArr.get(i);
			}else {
				address += addArr.get(i)+" ";
			}
		}
		String gender = request.getParameter("gender");

		Member m = Member.builder()
				.email(email)
				.name(name)
				.phone(phone)
				.build();
		
		Date birth = null;
		if(birthday!=null) {
			String[] birthArr = birthday.split("-"); // "2022" "11" "12"
			int year = Integer.parseInt(birthArr[0]); //2022=year 11=month 12=day
			int month = Integer.parseInt(birthArr[1])-1;
			int day = Integer.parseInt(birthArr[2]);
			birth = new Date(new GregorianCalendar(year, month, day).getTimeInMillis()); //brith=20221112
		}else {
			birth = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		System.out.println(birth);
		System.out.println(address);
		
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

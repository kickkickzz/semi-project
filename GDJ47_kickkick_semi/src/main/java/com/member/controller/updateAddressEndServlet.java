package com.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;

/**
 * Servlet implementation class updateAddressEndServlet
 */
@WebServlet("/updateaddressend.do")
public class updateAddressEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAddressEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String address = "";
		String address2 = request.getParameter("address2"); //주소
		String address3 = request.getParameter("address3");//참고항목
		String address4 = request.getParameter("address4");//상세주소
		ArrayList<String> addArr = new ArrayList();
		addArr.add(address2);
		addArr.add(address3);
		addArr.add(address4); //주소 참고항목 상세주소가 각각 addArr 배열에 들어감
		for(int i=0; i<addArr.size(); i++) {
			if(i==2) {
				address += addArr.get(i);
			}else {
				address += addArr.get(i)+" ";
			}
		}
		System.out.println(address);
		
		int result = new MemberService().updateAddress(email,address);
		String msg="", loc="";
		if(result>0) {
			msg +="주소 변경이 완료되었습니다.";
			String script="close();";
			request.setAttribute("script", script);
		}else {
			msg +="주소 변경이 실패했습니다.";
			loc +="/updateAddress.do?email="+email;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/address/updateAddress.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

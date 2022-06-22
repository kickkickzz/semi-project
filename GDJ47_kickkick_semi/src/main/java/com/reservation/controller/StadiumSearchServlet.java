package com.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.reservation.model.service.ReservationService;
import com.reservation.model.vo.Stadium;

/**
 * Servlet implementation class StadiumSearchServlet
 */
@WebServlet("/stadiumSearch.do")
public class StadiumSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StadiumSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//stadium이 구장, branch가 지점 지점안에 구장이 여러개가 있음
		String email = request.getParameter("email");
		System.out.println(email);
		List<Stadium> result = new ReservationService().stadiumSearch(email); //{stadim[0],stadium[1]...}
		JSONArray list = null;
		if(!result.isEmpty()) {
			for(Stadium s : result) { //
				list = new JSONArray();
				JSONObject sta = new JSONObject();
				System.out.println();
				sta.put("branch_num",s.getBranch_num());
				list.add(sta);
			}
		}else {
			System.out.println("result 가없습니다.");
			System.out.println(result);
//			list = null;
		}
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(list); //조회된 지점이 없으면 list가 null값이 나오는데 왜 안되징
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

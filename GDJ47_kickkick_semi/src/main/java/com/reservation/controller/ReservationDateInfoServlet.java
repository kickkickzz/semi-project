package com.reservation.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.reservation.model.service.ReservationService;
import com.reservation.model.vo.ReservationInfo;

/**
 * Servlet implementation class ReservationDateInfoServlet
 */
@WebServlet("/reservationinfo.do")
public class ReservationDateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationDateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int stanum=Integer.parseInt(request.getParameter("stanum"));
		String usage_start_date=request.getParameter("u_s_date");
		System.out.println(stanum);
		System.out.println(usage_start_date);
		Date dat= new Date(new GregorianCalendar().getTimeInMillis());
		
//		if(usage_start_date!=null) {
//			String[] date = usage_start_date.split("-");
//			int year=Integer.parseInt(date[0]);
//			int month=Integer.parseInt(date[1]);
//			int day=Integer.parseInt(date[2]);
//			dat = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
//			
//		}
		
		List<ReservationInfo> result = new ReservationService().selectreservationDate(stanum,usage_start_date);
		System.out.println(result);
		JSONObject userObj = null;
		JSONArray reservation = new JSONArray();
		JSONObject userMap = new JSONObject();
		for(ReservationInfo r:result) {
			userObj= new JSONObject();
			userObj.put("reservation_code", r.getReservation_code());
			userObj.put("reservation_email", r.getReservation_email());
			userObj.put("reservation_branch_num", r.getReservation_branch_num());
			userObj.put("reservation_stadium_num", r.getReservation_stadium_num());
			userObj.put("reservation_num", r.getReservation_num());
			userObj.put("reservation_price", r.getReservation_price());
			userObj.put("reservation_usage_start_time", r.getReservation_usage_start_time());
			userObj.put("reservation_usage_time", r.getReservation_usage_time());
			userObj.put("reservation_usage_end_time", r.getReservation_usage_end_time());
			userObj.put("reservation_usage_start_date", r.getReservation_usage_start_date());
			reservation.add(userObj);
			userMap.put("reservation",reservation);
		}
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(userMap,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.reservation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.reservation.model.service.ReservationService;
import com.reservation.model.vo.PayHistory;
import com.reservation.model.vo.ReservationInfo;

/**
 * Servlet implementation class ReservationInsertServlet
 */
@WebServlet("/reservationinsert.do")
public class ReservationInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* String userId = ((Member)request.getSession()).getEmail(); */
		
		System.out.println("클릭해서 진입");
		//예약코드 생성
		Random rand = new Random();
		String reservation_code = "";
		int ckCode = 0;
		do{
			for(int i=0; i<=5; i++) {
				String ran = Integer.toString(rand.nextInt(10));
				if(!reservation_code.contains(ran)) {
					reservation_code += ran;
				}else {
					i-=1;
				}
			}
			ckCode = new ReservationService().codeCheck(reservation_code);
		}while(ckCode == 1);
		System.out.println(reservation_code);

		String userId=request.getParameter("userId");
		String name=request.getParameter("name");
		
		String reservation_branch_num = request.getParameter("reservation_branch_num");
		int reservation_stadium_num = (Integer.parseInt(request.getParameter("reservation_stadium_num")));
		int reservation_price = (Integer.parseInt(request.getParameter("reservation_price")));
		int reservation_usage_start_time = (Integer.parseInt(request.getParameter("reservation_usage_start_time")));
		int reservation_usage_time = (Integer.parseInt(request.getParameter("reservation_usage_time")));
		int reservation_usage_end_time = (Integer.parseInt(request.getParameter("reservation_usage_end_time")));
		String reservation_usage_start_date = request.getParameter("reservation_usage_start_date");
		String[] start = reservation_usage_start_date.split("-");
		int year = Integer.parseInt(start[0]);
		int month= Integer.parseInt(start[1])-1;
		int day=Integer.parseInt(start[2]);
		Date dat= new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
		String paycode = request.getParameter("paycode");
		String paymethod= request.getParameter("buyer_method");
		System.out.println(paycode);
		System.out.println(paymethod);
		
		
		
		System.out.println(dat);
		
		ReservationInfo reservation = ReservationInfo.builder().reservation_code(reservation_code).reservation_email(userId).reservation_branch_num(reservation_branch_num)
				.reservation_stadium_num(reservation_stadium_num).reservation_price(reservation_price).reservation_usage_start_time(reservation_usage_start_time)
				.reservation_usage_time(reservation_usage_time).reservation_usage_end_time(reservation_usage_end_time).reservation_usage_start_date(reservation_usage_start_date).build();
		
		PayHistory p = PayHistory.builder().email(userId).paycode(paycode).reservation_code(reservation_code).paymethod("카카오페이").stadium_branch_num(reservation_branch_num).
				starttime(reservation_usage_start_time).endtime(reservation_usage_end_time).
				build();
		System.out.println(p);
		int result = new ReservationService().reservationInsert(reservation);
		int ph = new ReservationService().insertpayhistory(p);
		
		
		System.out.println("지점번호: "+reservation_branch_num);
		System.out.println("구장번호: : "+reservation_stadium_num);
		System.out.println("가격: "+reservation_price);
		System.out.println("예약시작 시각: "+reservation_usage_start_time);
		System.out.println("예약종료 시각: "+reservation_usage_end_time);
		System.out.println("예약 이용 시간:"+ reservation_usage_time);
		System.out.println("예약날짜: "+reservation_usage_start_date);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(result,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;
import com.reservation.model.vo.PayHistory;


@WebServlet("/member/reservationlist.do")
public class ReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReservationListServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		int cPage;
		int numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage")); //앞단에서 받아오는데 ?? 어떻게 받아오지????
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e){
			numPerpage = 5;
		}

		List<PayHistory> p = new MemberService().selectpayhistory(email,cPage,numPerpage);
		
		int totalData = new MemberService().payHistorylist(email); //이메일에 해당하는 예약현황을 모두 갖고옴
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize = 3;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		String pageBar ="";
		if(pageNo==1) {
			pageBar += "<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
						+"/member/reservationlist.do?cPage="+(pageNo-1)+"numPerpage="+numPerpage+"&email="+email+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) { //totalPage가 23이면 5로 나눌떄 3개가 남음로 totalPage도 설정해줌
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+"</span>"; //1일때는 아무것도 클릭이안댐
				//pageBar += "<li class="+'"page-item'"+"><a class="page-link" href="#">Previous</a></li>";
			}else {
				pageBar += "<a href='"+request.getContextPath()
						+"/member/reservationlist.do?cPage="+pageNo+"&numPerpage="+numPerpage+"&email="+email+"'>"+pageNo+"</a>";
			}
			pageNo++; //totalPage로 가면 ++하므로 totalPage보다 하나 커진다.
		}
		
		//다음버튼 누를떄 실행되는 로직
		if(pageNo>totalPage) {//하나 커지므로 >로 설정
			pageBar += "<span>[다음]</span>";
		}else {
			pageBar += "<a href='"+request.getContextPath()
						+"/member/reservationlist.do?cPage="+pageNo+"&numPerpage="+numPerpage+"&email="+email+"'>[다음]</a>";
		}
		request.setAttribute("paylist", p);
		request.setAttribute("pageBar",pageBar);
		request.getRequestDispatcher("/views/member/reservationlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

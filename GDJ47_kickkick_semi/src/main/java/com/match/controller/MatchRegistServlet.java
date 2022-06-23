package com.match.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.match.model.service.MatchService;
import com.match.model.vo.Match;
import com.reservation.model.service.ReservationService;
import com.reservation.model.vo.PayHistory;
import com.team.model.vo.Team;

/**
 * Servlet implementation class MatchRegistServlet
 */
@WebServlet("/matchregist.do")
public class MatchRegistServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      String userId = request.getParameter("userId");
      String reservation_code = request.getParameter("reservation_code");
      int result = new MatchService().teamregistcheck(userId);//팀 리더 조회 


      if(result>0) {//팀리더가맞으면
         int codeCheck= new ReservationService().codeCheck(reservation_code);//유효한 코드인지 조회
         if(codeCheck>0) {//코드가 유효하면
            int codeCheck2 = new MatchService().codeCheck(reservation_code);// 코드로 매칭이 잡혀있으면 중복등록 불가 조회된 매칭이없으면 매칭등록가능
            if(codeCheck2>0) { //이미 매칭이 잡혀있기 때문에 중복등록이 불가능
               result = 1;
            }else {
               int codeCheck3 = new MatchService().check(userId,reservation_code);//사용자 예약내역이 있는지확인
               if(codeCheck3>0) {//사용자의 구장예약 내역이 있으면
                  Team team = new MatchService().selectteamcode(userId);//사용자의 팀코드 가져옴
                  PayHistory ph = new MatchService().selectpayhistoryinfo(reservation_code);
                  Match m = Match.builder().team_code(team.getTeam_code()).reservation_code(reservation_code)
                        .branch_num(ph.getStadium_branch_num()).stadium_num(ph.getStadium_num()).build();
                  new MatchService().matchRegist(m);
                  result =2;
                  }else {
                     result =3; //내역이 없기 때문에 예약 불가
                  }

            }

         }else {//

         }
      }else { // 팀없음
         result =0;
      }




      response.setContentType("application/json; charset=utf-8");
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
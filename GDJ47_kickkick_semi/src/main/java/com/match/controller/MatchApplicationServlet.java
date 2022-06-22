package com.match.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.match.model.service.MatchService;
import com.team.model.vo.Team;

/**
 * Servlet implementation class MatchApplicationServlet
 */
@WebServlet("/matchApplication.do")
public class MatchApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String regist_num = request.getParameter("regist_num");
		String branch_num = request.getParameter("branch_num");
		String stadium_num = request.getParameter("stadium_num");
		String reservation_code = request.getParameter("reservation_code");
		int result = new MatchService().teamregistcheck(userId);//현재 이용자의 팀이 있는지 없는지를 체크
		if(result>0) {//팀이 있다면
			Team t = new MatchService().selectteamcode(userId);//신청자의 팀정보를 불러온다 
			
			int appCheck = new MatchService().matchApplicationCheck(regist_num,t.getTeam_code()); //매치등록 리스트에서 신청자의 팀코드로 등록유무 결정
			//1일시 자기팀에 신청할수 있기 때문에 신청불가 그 외 신청할수있음
			if(appCheck>0) {
				result=1;
			}else {
				int reappCheck = new MatchService().matchReApplicationCheck(regist_num,t.getTeam_code()); //매치테이블에 매칭이 잡혀잇는지 유무
				if(reappCheck>0) {//매칭이 잡혀잇기 때문에 중복등록이 불가
					result=2; // 
				}else {
					//매칭 신청
					new MatchService().matchApplication(regist_num,t.getTeam_code(),branch_num,stadium_num,reservation_code);
					result =3;
				}
			}
			
			
			
			
			
		}else {//팀이 없다면
			result = 0;
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

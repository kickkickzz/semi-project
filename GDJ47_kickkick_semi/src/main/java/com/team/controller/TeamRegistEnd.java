package com.team.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.team.model.service.TeamService;
import com.team.model.vo.Team;

import common.MyFileRenamedPolicy;

/**
 * Servlet implementation class TeamRegistEnd
 */
@WebServlet("/team/teamRegistEnd.do")
public class TeamRegistEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamRegistEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String msg="",loc="";
		 String userId = ((Member)request.getSession().getAttribute("loginMember")).getEmail();
		 String teamName=request.getParameter("name");
		 
		 System.out.println(userId);
		 System.out.println(request.getParameter("team_name"));
		 
		 
	      String root = request.getSession().getServletContext().getRealPath("/");
	      //String savePath = root + "resources/storage/"+userId+"/team_img/";
	      String savePath= "D:/projectfiles/"+userId+"/team_img";
	    
	      File f = new File(savePath);
	      if(!f.exists()) {
	         f.mkdirs();
	      }

	      int maxSize = 1024 * 1024 * 10;
	      
	     
	      
	      
	      
	      
	      int check = new TeamService().teamRegistCheck(userId);
	      
	      if(check > 0) {
	    	  msg="팀은 2개 이상 등록불가.";
	    	  loc="/team/teamRegist.do"; 
		      
		  }else { //팀등록;
		         
		            
			  Random rand = new Random();
	            String team_code = "";
	            int ckCode = 0;
	            do{
	               for(int i=0; i<=5; i++) {
	                  String ran = Integer.toString(rand.nextInt(10));
	                  if(!team_code.contains(ran)) {
	                     team_code += ran;
	                  }else {
	                     i-=1;
	                  }
	               }
	               
	               ckCode = new TeamService().teamCodeCheck(team_code);
	            }while(ckCode == 1);
	            
	            
	            Team t =Team.builder()
	            		.team_code(team_code)
	            		.team_leader(userId)
	            		.team_name(teamName)
	            		.team_gender(request.getParameter("team_gender"))
	            		.team_age(request.getParameter("team_age"))
	            		.team_region(request.getParameter("team_region"))
	            		.team_mark_img(request.getParameter("team_mark_img"))
	            		.build();
	            
	            
	            
	            int result=new TeamService().teamRegist(t);
	           
				
				if(result>0) {
					msg="팀이 등록되었습니다.";
					loc="/team.do";
				}else {
					msg="팀 등록에 실패하였습니다.";
					loc="/team/teamRegist.do";
				}
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher("/views/msg/msg.jsp")
				.forward(request, response);
	           
	         }
	           
	      
		           
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.reservation.model.vo.Branch;

import common.MyFileRenamedPolicy;

/**
 * Servlet implementation class EnrollBranchEndServlet
 */
@WebServlet("/enrollbranchend.do")
public class EnrollBranchEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollBranchEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = ((Member)request.getSession().getAttribute("loginMember")).getEmail();
	      String root = request.getSession().getServletContext().getRealPath("/");
	      //String savePath = root + "resources/storage/"+userId;
	      String savePath = root+"/resources/storage/branch_img";
	      File f = new File(savePath);
	      if(!f.exists()) {
	         f.mkdirs();
	      }
	      String savePath2 = root + "resources/storage/branch_img";
	     
	      System.out.println(savePath2);
	      int maxSize = 1024 * 1024 * 10;
	      MultipartRequest multiRequest = new MultipartRequest(request, savePath2, maxSize, "UTF-8", new MyFileRenamedPolicy());
	      String branch_num = multiRequest.getParameter("branchName");
	      String branch_branchInfo = multiRequest.getParameter("branchInfo");
	      String branch_detailInfo = multiRequest.getParameter("detailInfo");
	      String branch_option_shower = multiRequest.getParameter("shower");
	      String branch_option_park = multiRequest.getParameter("parking");
	      String branch_option_uniform = multiRequest.getParameter("uniform");
	      String branch_option_shoes = multiRequest.getParameter("shoes");
	      String branch_option_ball = multiRequest.getParameter("ball");
	      String branch_option_inout = multiRequest.getParameter("place");
	      String branch_notes = multiRequest.getParameter("notes");
	      String branch_website = multiRequest.getParameter("sns");
	      String branch_img = multiRequest.getParameter("fileName");
	      String branch_phone = multiRequest.getParameter("phone");
	      int point = 0;
	      String status = "N";
	      
	      String address2 = multiRequest.getParameter("address2");
	      String address3 = multiRequest.getParameter("address3");
	      String address4 = multiRequest.getParameter("address4");
	      String address5 = multiRequest.getParameter("address5");
	      
	      List<String> addressArr = new ArrayList<String>();

	      addressArr.add(address2);
	      addressArr.add(address3);
	      addressArr.add(address4);
	      addressArr.add(address5);
	      String branch_address  = "";
	      
	      for(String s:addressArr) {
	    	  branch_address+=s+" ";
	      }
	      Branch b = Branch.builder().branch_num(branch_num).branch_manager_email(userId)
	    		  .branch_address(branch_address).branch_phone(branch_phone).branch_img(branch_img)
	    		  .branch_website(branch_website).branch_Info(branch_branchInfo).detail_Info(branch_detailInfo)
	    		  .notes(branch_notes).branch_point(point).branch_option_shower(branch_option_shower)
	    		  .branch_option_park(branch_option_park).branch_option_uniform(branch_option_uniform)
	    		  .branch_option_shoes(branch_option_shoes).branch_option_ball(branch_option_ball)
	    		  .branch_option_inout(branch_option_inout).branch_delete_status(status).build();
	      
	      int result = new MemberService().insertbranch(b);
	      String msg = "";
	      String loc="/views/member/branch.jsp";
	      if(result>0) {
	    	  msg="지점을 등록하였습니다.";
	    	  request.setAttribute("msg", msg);
	    	  request.setAttribute("loc", loc);
	    	  request.getRequestDispatcher("/views/msg/enrollbranchmsg.jsp").forward(request, response);
	      }else {
	    	  msg="지점등록에 실패하였습니다.";
	    	  request.setAttribute("msg", msg);
	    	  request.setAttribute("loc", loc);
	    	  request.getRequestDispatcher("/views/msg/enrollbranchmsg.jsp").forward(request, response);
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

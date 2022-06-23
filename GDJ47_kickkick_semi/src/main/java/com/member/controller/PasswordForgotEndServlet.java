package com.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class PasswordForgotEndServlet
 */
@WebServlet("/passwordForgotEnd.do")
public class PasswordForgotEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordForgotEndServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
//		System.out.println(email);
//		System.out.println(phone);
//		System.out.println(name);
		
		Member m = new MemberService().passwordForgot(email,name);

		String msg="" , loc="" , script="";
		
		//네이버 이메일로 인증보내기
		if(m==null) {
			msg+="정보와 일치하는 이메일이 없습니다.";
			loc+="/passwordForgot.do";
			script = "close();";
			email = "";
			request.setAttribute("email", email);
			request.setAttribute("script", script);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
		}else {
			String host = "smtp.naver.com";
			String user = "kdh961129@naver.com";
			String password = "rlaehdgns12";
			msg += "입력하신 이메일로 임시비밀번호를 발송했습니다.";
			Properties prop = new Properties();
	
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.port", 587);
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	
	        
	        StringBuffer temp =new StringBuffer();
	        Random rnd = new Random();
	        for(int i=0;i<10;i++)
	        {
	            int rIndex = rnd.nextInt(3);
	            switch (rIndex) {
	            case 0:
	                // a-z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	                break;
	            case 1:
	                // A-Z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                break;
	            case 2:
	                // 0-9
	                temp.append((rnd.nextInt(10)));
	                break;
	            }
	        }
	        String AuthenticationKey = temp.toString();
	        System.out.println(AuthenticationKey);
	        Session session = Session.getInstance(prop, new Authenticator(){
				public PasswordAuthentication getPasswordAuthentication()
				{
					// 인증 아이디/비밀번호를 저장한다.
					return new PasswordAuthentication(user, password);
				}
			} );
	
	        try {
	            MimeMessage msgg = new MimeMessage(session);
	            //발신자
	            msgg.setFrom(new InternetAddress(user,"ㅋ킥킥ㅋ"));
	            //수신자
	            msgg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	            
	            //메일 제목
	            msgg.setSubject("안녕하세요 ㅋ킥킥ㅋ 인증 메일입니다.");
	            //메일 내용
	            msgg.setText("임시비밀번호 : "+temp);
	            
	            Transport.send(msgg);
	            System.out.println("이메일 전송");
	            
	        }catch (Exception e) {
	            e.printStackTrace();// TODO: handle exception
	        }
	        //HttpSession saveKey = request.getSession();
	        request.setAttribute("AuthenticationKey", AuthenticationKey);
	        
	        //임시비밀번호로 update하기 위한 email
	        //패스워드 바꿀때 뭘 바꿀지 조건에 들어가는 id
	        request.setAttribute("email", email);
	        request.setAttribute("msg", msg);
	        request.getRequestDispatcher("/views/msg/message.jsp").forward(request, response);
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

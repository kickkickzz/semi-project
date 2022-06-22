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

/**
 * Servlet implementation class emailCertificationServlet
 */
@WebServlet("/emailCertification.do")
public class emailCertificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public emailCertificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println(email);
		
		String host = "smtp.naver.com";
		String user = "kdh961129@naver.com";
		String password = "rlaehdgns12";
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
            msgg.setText("인증번호 : "+temp);
            
            Transport.send(msgg);
            System.out.println("이메일 전송");
            
        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
        
        request.setAttribute("AuthenticationKey", AuthenticationKey);
        request.getRequestDispatcher("/views/msg/emailCertification.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

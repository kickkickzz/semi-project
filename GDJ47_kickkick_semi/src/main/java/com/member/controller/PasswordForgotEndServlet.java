package com.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Servlet implementation class PasswordForgotEndServlet
 */
@WebServlet(name="passwordforgot" , urlPatterns={"/passwordForgotEnd.do"})
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
//		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");
//		String name = request.getParameter("name");
////		System.out.println(email);
////		System.out.println(phone);
////		System.out.println(name);
//		Member m = new MemberService().passwordForgot(email,phone,name);
////		System.out.println(m);
//		String msgg="" , loc="" , script="";
//		
//		//네이버 이메일로 인증보내기
//		if(m==null) {
//			msgg+="정보와 일치하는 회원이 없습니다.";
//			loc+="/passwordForgot.do";
//			request.setAttribute("msgg", msgg);
//			request.setAttribute("loc", loc);
//			return;
//		}
//		
//		String host = "smtp.naver.com";
//		String user = "kdh961129";
//		String mypassword = "rlaehdgns12";
//		Properties prop = new Properties();
////		try {
////		prop.load(new FileReader(MemberDao.class.getResource("/sql/smtp.properties").getPath()));
////	}catch(IOException e) {
////		e.printStackTrace();
////	}
//		prop.put("mail.smtp.host", host);
//		prop.put("mail.smtp.port", 587);
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
////        prop.put("mail.smtp.ssl.trust", host);
//       // prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
//        
//        StringBuffer temp =new StringBuffer();
//        Random rnd = new Random();
//        for(int i=0;i<10;i++)
//        {
//            int rIndex = rnd.nextInt(3);
//            switch (rIndex) {
//            case 0:
//                // a-z
//                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
//                break;
//            case 1:
//                // A-Z
//                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
//                break;
//            case 2:
//                // 0-9
//                temp.append((rnd.nextInt(10)));
//                break;
//            }
//        }
//        String AuthenticationKey = temp.toString();
//        System.out.println(AuthenticationKey);
//        Authenticator auth = new Authenticator();
//        Session session = Session.getDefaultInstance(prop,auth);
//
//        try {
//            MimeMessage msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(user, "ㅋ킥킥ㅋ"));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//            
//            //메일 제목
//            msg.setSubject("안녕하세요 ㅋ킥킥ㅋ 인증 메일입니다.");
//            //메일 내용
//            msg.setText("인증 번호는 :"+temp);
//            
//            Transport.send(msg);
//            System.out.println("이메일 전송");
//            
//        }catch (Exception e) {
//            e.printStackTrace();// TODO: handle exception
//        }
//        HttpSession saveKey = request.getSession();
//        saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
//        
////      request.setAttribute("id", memberId);
//        request.getRequestDispatcher("/views/msg/message.jsp").forward(request, response);
//		
		
		
		
		
		
		
		
		
		
		
		
//		if(m!=null) { //이메일과 전화번호 이름에 해당하는 멤버 찾으면 임시비밀번호 생성하고
//			System.out.println(ranPassword);
//			String ranPassword = new RandomPassword().getRamdomPassword(10);
//			int result = new MemberService().randomPassword(email,ranPassword); //생성한 임시비밀번호로 업데이트 한다.
//			if(result>0) { //임시비밀번호로 업데이트 완료되면
//				msg += "임시 비밀번호 : "+ranPassword;
//				script +="close()";
//			}else { //임시비밀번호로 업데이트 실패하면
//				msg += "임시비밀번호 발급 실패";
//				loc += "/passwordForgot.do";
//			}
//		}else {
//			msg += "임시비밀번호 발급 실패패";
//			loc += "/passwordForgot.do";
//		}
//		
//		request.setAttribute("msg", msg);
//		request.setAttribute("loc", loc);
//		request.setAttribute("script", script);
//		request.getRequestDispatcher("/views/msg/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

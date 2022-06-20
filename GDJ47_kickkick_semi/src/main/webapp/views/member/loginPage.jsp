<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 및 회원가입</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/loginPage.css">
	<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/loginPage.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script src="<%=request.getContextPath() %>/js/kakaoLogin.js"></script>
</head>
	<body>
			
		<div id="login-container">
		   <div id="login-left">
		      <a href="<%=request.getContextPath()%>">
		         <img id="semi-logo" src="<%=request.getContextPath()%>/images/세미로고.png">
		      </a>   
		   </div>
		   <div id="login-right">
		      <img id="bg-img" src="<%=request.getContextPath()%>/images/login-bg.jpg">
		      <div class="container">
		           <div class="move">
		             <div class="p-button normal signin animated pulse">회원가입하기</div>
		           </div>
		           <div class="welcome">
		             <h4 class="bold welcome-text">회원가입</h4>
		             <p class="normal text">아직 아이디가 없다면<br> 회원가입을 하세요!<br>더 많은 서비스들이 당신을 기다리고 있습니다.</p>
		           </div>
		           <div class="hello">
		             <h4 class="bold welcome-text">로그인</h4>
		             <p class="normal text">아이디가 있다면 로그인을 하세요! <br>로그인을 통해 더 많은 서비스를<br> 이용할 수 있습니다.</p>
		           </div>
		           <div class="form">
		                <h4 class="bold title">로그인</h4>
			                <div id="in" class="icons">
			                <a href="javascript:kakaoLogin();" style="text-decoration: none; color: gray">
			                     <div class="icon"><img class="kakao-icon" src="<%=request.getContextPath()%>/images/kakao-talk.png"></div>
			                     카카오로 간편하게 로그인하세요!!
			                </a>
			                </div>
		                <div id="up" class="icons">
		                     <div class="icon"><img class="kakao-icon" src="<%=request.getContextPath()%>/images/kakao-talk.png"></div>
		                     카카오로 간편하게 가입하세요!!
		                </div>
		                <input type="email" placeholder="이메일" class="normal email"><br>
		                <div id="msg"></div>
		                <input type="password" placeholder="비밀번호" class="normal password"><br>
		                <div id="pwmsg"></div>
		                <div class="login-error-msg"><%=msg!=null?msg:"" %></div>
		                <input type="text" placeholder="이름" class="normal name"><br>
		                <div id="namemsg"></div>
		                <input type="tel" placeholder="전화번호" class="normal phone"><br>
		                <input type="date" placeholder="생년월일" class="normal birthday"><br>
		                <input type="text" id="sample6_postcode" placeholder="우편번호" class="address" style="margin-bottom : 3px;">
		                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="address">
		                <input type="text" id="sample6_address" name="address2" placeholder="주소" size="29" style="margin-bottom : 13px;" class="address">
		                <input type="text" id="sample6_extraAddress" name="address3" placeholder="참고항목" class="address"><br>
		                <input type="text" id="sample6_detailAddress" name="address4" placeholder="상세주소" size="29" style="margin-bottom : 13px;" class="address">
		                
		                <br/>
		                <p class="normal forgot">비밀번호를 잊어버리셨습니까?</p>
		                <input type="submit" id="bb" class="b-button normal" value="로그인하기">
		         </div>
		         <div id="hidden-form">
		            <form id="h-form" action="" method="post">
		               <input id="email" type="text" name="email" value="">
		               <input id="password" type="text" name="password" value="">
		               <input id="name" type="text" name="name" value="">
		               <input id="phone" type="text" name="phone" value="">
		               <input id="date" type="text" name="birthday" value="">
		               <input id="address2" type="text" name="address2" value="">
		               <input id="address3" type="text" name="address3" value="">
		               <input id="address4" type="text" name="address4" value="">
		               <input id="gender" type="radio" name="gender" value="">
		            </form>
		         </div>
		      </div>   
		   </div>
		</div>
	</body>
</html>
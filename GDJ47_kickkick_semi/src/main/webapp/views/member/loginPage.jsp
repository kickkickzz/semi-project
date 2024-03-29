<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String msg = (String)request.getAttribute("msg");
%>
<%-- <%@ include file="/views/common/header.jsp" %> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/loginPage.css">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/js/loginPage.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2-nopolyfill.js" charset="utf-8"></script>
<title>ㅋ킥킥ㅋ</title>

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
               	<input type="email" placeholder="이메일" class="normal email" id="ema" value="">
               	<i id="x-mark" class="fa-solid fa-x"></i>
                <input type="button" id="idcheck" value="이메일인증" onclick="fn_emailDuplicate();"><br>
                <div id="msg"></div>
	            <input type="password" placeholder="비밀번호" class="normal password" onkeyup="javascript:enterkey();">
	            <i id="p-mark" class="fa-solid fa-x"></i><br>
                <div class="login-error-msg"><%=msg!=null?msg:"" %></div>
                <input type="text" placeholder="이름" class="normal name">
                <i id="n-mark" class="fa-solid fa-x"></i><br>
                <input type="tel" placeholder="전화번호(-없이)" class="normal phone" maxlength="11">
				<i id="ph-mark" class="fa-solid fa-x"></i><br>
                <input type="date" placeholder="생년월일" class="normal birthday"><br>
                
                <input type="text" id="sample6_postcode" placeholder="우편번호" class="address" style="margin-bottom : 3px;">
                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="address">
                <input type="text" id="sample6_address" name="address2" placeholder="주소" size="29" style="margin-bottom : 13px;" class="address">
                <input type="text" id="sample6_extraAddress" name="address3" placeholder="참고항목" class="address"><br>
                <input type="text" id="sample6_detailAddress" name="address4" placeholder="상세주소" size="29" style="margin-bottom : 13px;" class="address">
                
                <br/>
                <p class="normal forgot" onclick="fn_emailForgot();">이메일을 잊어버리셨습니까?</p>
                <p class="normal forgot" onclick="fn_passwordForgot();">비밀번호를 잊어버리셨습니까?</p>
                <input type="submit" id="bb" class="b-button normal" value="로그인하기">
                <input id="ducheck" type="hidden">
         </div>
         <div id="hidden-form">
            <form id="h-form" action="" method="post" class="h-form">
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
         <form name="emailDuplicate">
         	<input id="ducheck" type="hidden" name="email">
         </form>
      </div>   
   </div>
</div>
<script>
function fn_emailDuplicate(){
	const email = $('.email').val();
	console.log(email);
	const url = "<%=request.getContextPath()%>/views/member/emailDup.jsp";
    const title="emailDuplicate";
    open("",title,"width=500,height=300");
    console.log(emailDuplicate);
    emailDuplicate.email.value=email;
    emailDuplicate.method="post";
    emailDuplicate.action=url;
    emailDuplicate.target=title;
    emailDuplicate.submit();
}
function fn_passwordForgot(){
	   open("<%=request.getContextPath()%>/passwordForgot.do?","_blank","width=450, height=400 ,left=700, top=300");
	}

function fn_emailForgot(){
	open("<%=request.getContextPath()%>/emailForgot.do?","_blank","width=400, height=450 ,left=700, top=300");
}
</script>
<%-- <%@ include file="/views/common/footer.jsp" %> --%>

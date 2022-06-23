<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean result = (boolean)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#checkId-container{
		text-align : center;
		padding-top : 50px;
	}
</style>
</head>
<body>
	<div id="checkId-container">
		<%if(result) {%>
			<span id="asd"><%=request.getParameter("email")%></span>는 사용가능한 이메일 입니다.<br>
			회원가입을 계속 하기 위해서 인증을 진행합니다.
			<%-- <br><br>
			<button type="button">닫기</button>
			<br> --%>
			<form action="<%=request.getContextPath()%>/emailCertification.do" method="post">
			<input type="text" value="<%=request.getParameter("email")%>"><br>
			<input type="hidden" value="<%=request.getParameter("email")%>" name="email">
			<input type="submit" style="text-align : center;"value="이메일인증보내기">
			</form>
		<%} else{%>
			<span id="duplicated"><%=request.getParameter("email")%></span>는 이미 사용중입니다.
			<br><br>
			<!-- 아이디 재입력창 구성 -->
			<form action="<%=request.getContextPath() %>/emailDuplicate.do" method="post">
				<input type="text" name="email" id="email">
				<input type="submit" value="중복검사" >
			</form>
		<%} %>
	</div>
	<script>
		const btn = document.querySelector("[type=button]");
		btn.addEventListener("click",(e)=>{
			opener.document.getElementById("ema").value='<%=request.getParameter("email")%>';
			opener.password.focus();
			close(); //열린 윈도우가 닫힘
		})
	</script>
</body>
</html>
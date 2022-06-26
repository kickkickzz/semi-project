<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String msg = (String)request.getAttribute("msg");
	String email = (String)request.getAttribute("email");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 중복확인</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<style>
	#check-container{
		text-align: center;
		padding-top: 20px;
	}
	body{
	text-align: center;
	padding-top: 25px;}
</style>
<body>
	<h2>이메일 중복확인</h2>
	<div id="check-container">
		<form action="<%=request.getContextPath() %>/checkEmail.do" method="post">
			<input type="email" name="email" value="<%=email!=null?email:""%>"><br><br>
			<input type="submit" value="중복확인" >
			<input type="button" onclick="window.close();" value="닫기">
		</form>
		<div>
		<%if(msg=="1"){ %>
			<p style="color:red">이미 가입된 이메일입니다.</p>
		<%}else if(msg=="2"){ %>
			<p style="color:green">가입 가능한 이메일입니다.</p>
			<form action="<%=request.getContextPath()%>/emailCertification.do">
				<input type="hidden" name="email" value="<%=email%>">
				<input type="submit" value="인증하기">
			</form>
		<%}else if(msg=="3"){ %>
			<p style="color:red">올바른 이메일을 입력해주세요.</p>
		<%} %>
		</div>
	</div>
</body>


</html>
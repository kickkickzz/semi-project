<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String)request.getAttribute("msg"); %>
<% String loc = (String)request.getAttribute("loc"); %>
<% String script=(String)request.getAttribute("script"); %>
<% String email = (String)request.getAttribute("email"); %>
<% String AuthenticationKey = (String)request.getAttribute("AuthenticationKey"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="hh">
		<h2>비밀번호 찾기</h2>
		<hr/>
		<br>
		<p>
			인증번호를 입력하시고 일치하면<br>
			비밀번호 재변경 가능합니다.
		</p>
	</div>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/passwordForgotUpdate.do" method="post">
	<div style="text-align : center;">
		> 인증키 <input type="text" name="AuthenticationUser" style="margin-left:10px">
		<input type="submit" value="확인">
	</div>
		<input type="hidden" value="<%=email%>" name="email">
		<input type="hidden" value="<%=AuthenticationKey%>" name="AuthenticationKey">
	</form>
</body>
<style>
.hh{
			text-align:center;
		}
</style>
<script>

</script>
</html>
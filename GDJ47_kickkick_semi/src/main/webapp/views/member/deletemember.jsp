<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/deletememberend.do" method="post">
		현재 비밀번호 &nbsp&nbsp&nbsp<input type="password" name="oriPw" class="pw"><br>
		<input type="submit" value="탈퇴">
		<input type="reset" value="취소">
		<input type="hidden" name="email" value="<%=request.getParameter("email")%>"/>
	</form>
<style>
	.pw{
		margin-top:10%;
	}
</style>
</body>
</html>
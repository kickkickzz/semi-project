<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form action="<%=request.getContextPath()%>/passwordForgotEnd.do" method="post">
		이메일 &nbsp&nbsp&nbsp&nbsp<input type="text" name="email" class="pw"><br>
		전화번호 <input type="text" name="phone" class="pw"><br>
		이름 &nbsp&nbsp&nbsp&nbsp<input type="text" name="name" class="pw"><br>
		<input type="submit" value="찾기">
		<input type="reset" value="취소">
		<%-- <input type="hidden" name="email" value="<%=request.getParameter("email")%>"/> --%>
	</form>
</body>
</html>
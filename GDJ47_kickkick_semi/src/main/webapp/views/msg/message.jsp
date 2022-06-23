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
<!-- alert으로 창 뛰어서 확인 누르면 닫히고 임시비밀번호로 업데이트 된다. -->
	<form action="<%=request.getContextPath()%>/passwordForgotUpdate.do" method="post">
		인증키 <input type="text" name="AuthenticationUser">
		<input type="submit" value="확인">
		<input type="hidden" value="<%=email%>" name="email">
		<input type="hidden" value="<%=AuthenticationKey%>" name="AuthenticationKey">
	</form>
</body>
<script>
	<%if(email!=null){%>
		opener.document.getElementById("ema").value='<%=email%>';
	<%}else{%>
		opener.document.getElementById("ema").value=' ';
	<%}%>
</script>
</html>
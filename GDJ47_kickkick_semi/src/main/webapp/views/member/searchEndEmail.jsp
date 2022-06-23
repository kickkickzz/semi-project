<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<h2>
		아이디 찾기가 완료 되었습니다.
		</h2>
		<hr/>
		<p>
		</p>
	</div>
	<div class="body">
	<p><%=request.getAttribute("msg")%></p>
	</div>
	<style>
		.header{
		text-align:center;
		}
		.body{
		margin-top : 80px;
		text-align : center;
		}
	</style>
</body>
</html>
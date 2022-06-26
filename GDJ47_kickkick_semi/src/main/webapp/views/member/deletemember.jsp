<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="hh">
		<h2>회원 탈퇴</h2>
		<hr/>
		<br>
		<p>
			현재 비밀번호를 입력하시면 <br>
			회원탈퇴가 완료 됩니다.
		</p>
	</div>
	<form action="<%=request.getContextPath()%>/deletememberend.do" method="post">
		<div style="margin-left:50px;">
		현재 비밀번호 &nbsp&nbsp&nbsp<input type="password" name="oriPw" class="pw" style="margin-bottom:10px"><br>
		</div>
		<div style="text-align : center;">
		<input type="submit" value="탈퇴">
		<input type="reset" value="취소" onclick="window.close()">
		</div>
		<input type="hidden" name="email" value="<%=request.getParameter("email")%>"/>
	</form>
<style>
	.pw{
		margin-top:10%;
	}
		
	#hh{
		text-align:center;
		
	}
</style>
</body>
</html>
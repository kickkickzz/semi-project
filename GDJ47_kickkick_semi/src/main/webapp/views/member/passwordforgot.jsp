<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
			비밀번호가 기억나지 않으신가요?<br>
			이메일과 이름을 입력하시면 입력하신<br>
			이메일로 인증번호를 보내드립니다.<br>
		</p>
	</div>
	<br>
	<br>
   <form action="<%=request.getContextPath()%>/passwordForgotEnd.do" method="post">
   		<div style="text-align:left; margin-left:85px">
		> 이메일<input type="text" name="email" class="pw" style="margin-left:10px"><br>
		</div><br>
		<div style="text-align:left; margin-left:85px">
		> 이름&nbsp&nbsp<input type="text" name="name" class="pw" style="margin-left:15px">
		</div><br>
		<div class="hh">
		<input type="submit" value="찾기">
		<input type="reset" value="취소" onclick="window.close();">
		</div>
	</form>
	<style>
		.hh{
			text-align:center;
		}
	</style>
</body>
</html>
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
		<h2>이메일 찾기</h2>
		<hr/>
		<br>
		<p>
			회원가입 했던 이메일이 기억나지 않으세요?<br>
			아래 정보를 입력하시면 이메일을 알려드립니다.
		</p>
	</div>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/emailForgotEnd.do" method="post">
		
		<div style="text-align:left; margin-left:40px">
		> 이름&nbsp<input type="text" name="name" class="pw" style="margin-left:35px"><br>
		</div><br>
		<div style="text-align:left; margin-left:37px">
		> 전화번호&nbsp&nbsp<input type="text" name="phone" class="pw">
		</div><br>
		<div style="text-align:center;">
		<input type="submit" value="찾기">
		<input type="reset" value="취소" onclick="window.close();">
		</div>
		<%-- <input type="hidden" name="email" value="<%=request.getParameter("email")%>"/> --%>
	</form>
	<style>
		#hh{
		text-align:center;
		
		}
	</style>
</body>

</html>
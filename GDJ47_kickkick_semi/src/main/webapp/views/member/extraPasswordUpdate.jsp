<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String)request.getAttribute("msg"); %>
<% String email = (String)request.getAttribute("email"); %>
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
			인증번호를 입력하시면<br>
			비밀번호를 재변경 가능합니다.
		</p>
	</div>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/extraPasswordEnd.do" method="post" onsubmit="return pwSubmit();">
		<div style="text-align:left; margin-left:40px">
		> 변경할 비밀번호 <input type="password" name="newPw" id="newPw"><br>
		</div><br>
		<div style="text-align:left; margin-left:40px">
		> 비밀번호 확인 &nbsp&nbsp&nbsp<input type="password" name="newPwCk" id="newPwCk"><br>
		</div><br>
		<div style="text-align:center;">
		<input type="submit" value="변경">
		<input type="reset" value="취소">
		</div>
		<input type="hidden" name="email" value="<%=request.getParameter("email")%>"/>

	</form>
<style>
	.pw{
		margin-top:10%;
	}
	.hh{
		text-align : center;
	}
</style>
<script>
  function pwSubmit() {
    var pw1 = document.getElementById('newPw').value;
    var pw2 = document.getElementById('newPwCk').value;
    if ( pw1 != pw2 ) {
      alert('비밀번호가 일치 하지 않습니다.');
      return false;
    }
  }
</script>
</body>
</html>
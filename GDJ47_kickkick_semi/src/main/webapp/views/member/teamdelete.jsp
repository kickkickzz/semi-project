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
		<h2>팀 탈퇴</h2>
		<hr/>
		<br>
		<p>
			<br>
			새로운 비밀번호로 변경 가능합니다.
		</p>
	</div>
	<form action="<%=request.getContextPath()%>/updatepasswordend.do" method="post" onsubmit="return pwSubmit();">
		<div style="text-align:left; margin-left:75px">
		>현재 비밀번호 &nbsp&nbsp&nbsp<input type="password" name="oriPw" class="pw"><br>
		</div>
		<div style="text-align:left; margin-left:75px">
		>변경할 비밀번호  <input type="password" name="newPw" id="newPw" style="margin-left:1px;"><br>
		</div>
		<div style="text-align:left; margin-left:75px">
		>비밀번호 확인 &nbsp&nbsp&nbsp<input type="password" name="newPwCk" id="newPwCk"><br><!-- onsubmit 처리 -->
		</div>
		<div style="text-align:center; margin-top: 10px;" >
		<input type="submit" value="변경">
		<input type="reset" value="취소" onclick="window.close()">
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
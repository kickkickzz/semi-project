<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean result=(boolean)request.getAttribute("result");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="checkId-container">
		<%if(result) {%>
			[<span id="teamname"><%=request.getParameter("team_name")%> </span>]는 사용가능합니다.	
			<br><br>
			<button type="button" >닫기</button>
		<%} else{%>
			[<span id="duplicated"><%=request.getParameter("team_name") %></span>]는 사용중입니다.
			<br><br>
			<!-- 아이디 재입력창 구성 -->
			<form action="<%=request.getContextPath() %>/team/idDuplicate.do" method="post">
				<input type="text" name="team" id="team">
				<input type="submit" value="중복검사" >
			</form>
		<%} %>
	</div>
	<script>
		const btn=document.querySelector("[type=button]");
		console.log(btn);
		btn.addEventListener("click",(e)=>{
			opener.document.getElementById("teamna").value='<%=request.getParameter("team_name")%>';
			close();
		});
	</script>
</body>
</html>
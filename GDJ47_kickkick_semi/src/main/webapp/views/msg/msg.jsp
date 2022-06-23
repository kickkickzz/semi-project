<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String)request.getAttribute("msg"); %>
<% String loc = (String)request.getAttribute("loc"); %>
<% String script=(String)request.getAttribute("script"); %>
<% String email = (String)request.getAttribute("email"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	console.log(<%=script%>);
		alert("<%=msg%>");
		/* close(); */
		<%if(script!=null){%>
			opener.document.getElementById("ema").value='<%=email%>';
			<%=script%>
		<%}%>
		<%if(email!=null){%>
			opener.document.getElementById("ema").value='<%=email%>';
		<%}
		else{%>
			opener.document.getElementById("ema").value='';
		<%}%>
		<%-- <%=script!=null?script:""%> --%>
		location.replace("<%=request.getContextPath()%><%=loc%>");
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import= "com.reservation.model.vo.Stadium,java.util.List" %>
    
<%@ include file="/views/common/header.jsp" %>
<% List<Stadium> result = (List<Stadium>)request.getAttribute("staArr");
	String pagebar = (String)request.getAttribute("pageBar");
%>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
		
		
	
	
	<div class="container">
		<br>
		<br>
		
		<h1 class="text-center"><a>reservation</a></h1>
		<br>
		<br>
		<form action="<%=request.getContextPath()%>/searchstadium.do" onsubmit="return fn_searchData(event);">
		<input type="text" name="searchstadium" placeholder="검색할 구장명을 입력하세요" size="25" onkeyup="submit"> 
		
		</form>
		<br>
		<br>
			
			
		<table class="table table-hover table-striped text-center">
		<thead>
			<tr>
				<td>no</td>
				<td>이미지</td>
				<td>장소</td>
				<td>구장명</td>
				<td>구장</td>
				<td>매치</td>
				<td>예약가능</td>
				<td>전화번호</td>			
			</tr>
		</thead>
		<%if(result==null){ %>
		<tbody>
			<tr>
				<td colspan="7"></td>
			</tr>
		</tbody>
		<%}else{ 
		
			for(Stadium s:result){%>
			<tbody>
			
			<tr onclick="location.assign('<%=request.getContextPath()%>/reservation.do?stanum=<%=s.getStadium_num()%>')">
				<td><%=s.getStadium_num() %></td>
				<td><%=s.getBranch_img() %></td>
				<td><%=s.getBranch_address() %></td>
				<td><%=s.getBranch_num() %></td>
				<td><%=s.getStadium_name() %></td>
				<td><%=s.getStadium_match_member() %></td>
				<td><%=s.getStadium_reservation_start_time()%> : 00 ~ <%=s.getStadium_reservation_end_time()%> : 00</td>
				
				<td><%=s.getBranch_phone()%></td>
				
			</tr>
			
			<%} %>
		</tbody>
		<%} %>
	
	
	</table>
		<div class ="" id="pageBar" style="text-align:center">
		<%=pagebar %></div>
		
	
	</div>
	<script>
	
		const fn_searchData=e=>{
			if($(e.target).find("input").val().length==0){
				alert("값을 입력하고 조회하세요.");
				return false;
			}
		}
	
	</script>
	

<%@ include file="/views/common/footer.jsp" %>

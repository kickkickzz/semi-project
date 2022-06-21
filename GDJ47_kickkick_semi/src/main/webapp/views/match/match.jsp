<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import = "com.match.model.vo.*" %>
 <%List<Match> m = (List<Match>)request.getAttribute("matcharr");
 System.out.println(m);
 System.out.println("Dd");
 String pagebar = (String)request.getAttribute("pageBar");%>
 <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
<%@ include file="/views/common/header.jsp" %>

<div class="container">
		<br>
		<br>
		
		<h1 class="text-center"><a>match</a></h1>
		<br>
		<br>
		<!-- <form action= onsubmit="">
		<input type="text" name="" placeholder="검색할 구장명을 입력하세요" size="25" onkeyup="submit"> 
		
		</form> -->
		<br>
		<br>
			
			<h1 style="text-align:center">매치 정보 보기</h1>
		<table class="table table-hover table-striped text-center">
		<thead>
			<tr>
								<th >no</th>
								<th>팀</th>
								<th>-</th>
								<th>-</th>
								<th>매치</th>
								<th >구장</th>
								<th>시간</th>
								<th>날짜</th>
								<th>이미지</th>
								<th>장소</th>
								<th>신청</th>	
								
			</tr>
			
		</thead>
		<%if(m.isEmpty()){ %>
		<tbody>
			<tr>
				<td colspan="11">조회된 결과가 없습니다.</td>
			</tr>
		</tbody>
		<%}else{ %>
			<tbody>
			<%for(Match m1:m){ %>
			<tr >
				<td><%=m1.getRegist_num() %></td>
				<td><%=m1.getTeam_gender() %></td>
				<td><%=m1.getTeam_name()%></td>
				<td><%=m1.getTeam_age()%></td>
				<td><%=m1.getStadium_match_member()%></td>
				<td><%=m1.getStadium_name()%></td>
				<td><%=m1.getReservation_usage_start_time()%>:00 ~ <%=m1.getReservation_usage_end_time() %>:00</td>
				<td><%=m1.getReservation_usage_start_date() %></td>
				
				<td>5</td>
				<td><%=m1.getBranch_address() %></td>
				<td>5</td>
			
				
			</tr>
			<%} %>
			
			
		</tbody>
		<%} %>
		
	
	
	</table>
		<div class ="" id="pageBar" style="text-align:center">
		<%=pagebar %></div>
		
	
	</div>


<%@ include file="/views/common/footer.jsp" %>
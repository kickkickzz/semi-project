<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="com.match.model.vo.*,java.util.List, java.util.ArrayList,com.team.model.vo.*,com.member.model.vo.* " %>
<%
	String loginUser = ((Member)session.getAttribute("loginMember")).getEmail();
	ArrayList<TeamMemberInfo> teamMemberArr=(ArrayList<TeamMemberInfo>)request.getAttribute("teamMemberArr");
	Team teamInfo = (Team)request.getAttribute("teamInfo");
	ArrayList<TeamMemberInfo> teamEnterMember=(ArrayList<TeamMemberInfo>)request.getAttribute("teamEnterMember");
	List<Team> matchlist = (List<Team>)request.getAttribute("matchlist");
	ArrayList<Match> registnum= (ArrayList<Match>)request.getAttribute("registnum");
	int rnum =0;
	if(registnum!=null){
		rnum =registnum.get(0).getRegist_num();
	}
	
	
	

%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='https://d3js.org/d3.v3.min.js'></script>

<body>

	<!-- BODY 시작 -->
	<section id="content">
	
		<!-- container-for-carousel 시작-->
		<div class="container-for-carousel">

			<!-- carousel start -->
			<div class="carousel-container">
			</div>
			<!-- carousel end -->
		</div>
		<!-- .container-for-carousel 끝-->
		<!--container-for-content 시작-->
		<div class="container-for-content">
			<!-- container-for-content안에 내용을 넣어주세요~ -->

			<div class="container-content">
				<!-- container안에 들어있는 내용이 바뀜. -->

				<div class="wrap">
					<br> <br>
					<div class="media" style="position: relative; left: 20px;">
						<div class="media-left">
							<input type="hidden" size="50" name="team_leader"
								id="team_leader" value="<%= teamInfo.getTeam_leader()%>">
							<h2><%= teamInfo.getTeam_name()%></h2>
							<a href="#"> 
							 <img src="./resources/storage/<%= teamInfo.getTeam_leader()%>/team_img/<%= teamInfo.getTeam_mark_img()%>"
								width="250px" height="200px"> 
							</a>
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="media-body">
							<br> <br>
							<table class="table table-striped" style="table-layout: fixed;">
								<tr>
									<th>지역</th>
									<td><%= teamInfo.getTeam_region()%></td>
								</tr>
								<tr>
									<th>성별</th>
									<td><%= teamInfo.getTeam_gender()%></td>
								</tr>
								<tr>
									<th>연령대</th>
									<td><%= teamInfo.getTeam_age()%></td>
								</tr>
								<tr>
									<th>팀별점</th>
									<td>
										<%for (int i = 0; i < teamInfo.getTeam_point(); i++) { %><span class="fa fa-star checked"></span> <%}%>
                            			<%for (int i = 0; i < 5-teamInfo.getTeam_point(); i++) { %><span class="fa fa-star"></span> <% }%></h1>
									</td>
								</tr>

							</table>
						</div>
					</div>
				</div>

				

				

				<div class="wrap4" id="wrap4">

					<hr>
					<h1>팀원 정보</h1>
					<hr>
					<% if(loginUser != null) { %>
					<button type="button" id="supporter" style="float: right;"
						class="simple" data-toggle="modal"
						data-target="#exampleModal" data-whatever="@mdo">용병지원</button>
					<% }%>

					<div class="table-responsive">
						<table class="table table-hover">
							<thead class="thead-dark">
								<tr>
									<th scope="col">이름</th>
									<th scope="col">성별</th>
									<th scope="col">연락처</th>
									<th scope="col">포지션</th>
								</tr>
							</thead>
							<tbody>
								<% if(teamMemberArr.isEmpty()){ %>
									<tr>
										<td colspan="4">존재하는 팀원이 없습니다</td>
									</tr>
								<%} else { %>
							
									<%for(TeamMemberInfo t:teamMemberArr ) {%>
									<tr>
										<td><%= t.getName() %></td>
										<td><%= t.getGender() %></td>
										<td><%= t.getPhone() %></td>
										<td><%= t.getPosition() %></td>
									</tr>
									<%}%>
								<%}%>
						
							</tbody>
						</table>
					</div>
				</div>
				
					<br> <br>
					
					
				
		<!--container-for-content 끝-->
		<!-- modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">&lt;팀신청&gt;</h4>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">

								<div class="input-group mb-3">
									<label for="recipient-name" class="control-label"
										style="font-size: 25px;">희망 포지션 : </label> &nbsp;&nbsp;&nbsp;
									<span class="input-group-addon"> <i
										class="fa fa-user fa" aria-hidden="true"></i>
									</span> <select id="position">
										<option value="">포지션 선택</option>
										<option value="공격수">공격수</option>
										<option value="미드필더">미드필더</option>
										<option value="수비수">수비수</option>
										<option value="골키퍼">골키퍼</option>
									</select>
								</div>

							</div>
							<div class="form-group">
								<textarea class="form-control" id="message-text"
									placeholder="메모를 남겨주세요"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="supporterBtn" class="simple">지원하기</button>
						<button type="button" class="btn btn-default"
							data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="wrap5" id="wrap5">
			<hr>
			<h1>용병 신청</h1>
			<hr>

			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">이름</th>
							<th scope="col">성별</th>
							<th scope="col">나이</th>
							<th scope="col">포지션</th>
							<th scope="col">신청</th>
						</tr>
					</thead>
					<tbody>
						<% if(teamEnterMember.isEmpty()){ %>
							<tr>
								<td colspan="5">신청한 용병이 없습니다.</td>
							</tr>
						<%} else { %>
							
							<%for(TeamMemberInfo te:teamEnterMember ) {%>
							<tr>
								<td><%= te.getName() %></td>
								<td><%= te.getGender() %></td>
								<td><%= te.getAge() %></td>
								<td><%= te.getPosition() %></td>
								<td><input type="button" id="acBtn" class="acBtn" supporter="<%=te.getSupporter_email() %>" value="수락">  
								    <input type="button" id="caBtn" class="caBtn" supporter="<%=te.getSupporter_email() %>" value="취소"></td>
							</tr>
							<%}%>
						<%}%>
						
						
						
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="wrap6" id="wrap6">
			<hr>
			<h1>신청 받은 매칭</h1>
			<hr>

			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">등록</th>
							<th scope="col">위치</th>
							<th scope="col">구장</th>
							<th scope="col">매치</th>
							<th scope="col">시간</th>
							<th scope="col">날짜</th>
							<th scope="col">신청팀</th>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col">별점</th>
							<th scope="col">상태</th>
						</tr>
					</thead>
					<tbody>
			 			<% if(matchlist.isEmpty()){ %>
							<tr>
								<td colspan="7">매칭팀이 없습니다.</td>
							</tr>
						<%} else { %>
							
							<%for(Team mt: matchlist ) {%>
							<tr>
								<td><%= mt.getTeam_name() %></td>
								<td><%= mt.getTeam_gender() %></td>
								<td><%= mt.getTeam_region() %></td>
								<td><%= mt.getTeam_age()%></td>
								<td><input type="button" id="macBtn" class="macBtn" matchregistnum="<%=rnum%>" value="수락">
                        		<input type="button" id="mcaBtn" class="mcaBtn" matchregistnum="<%=rnum%>" value="취소"></td></tr>
                        
							
								
								
							
							<%}%>
						<%}%>
						
						
						</tr>		
						<div>
						
						</div>
						
						
					</tbody>
				</table>
			</div>
		</div>
	</section>
	
	<!--BODY 끝.-->



</body>

<script type="text/javascript">

	$(document).on('click', '#exBtn, #acBtn, #caBtn, #macBtn, #mcaBtn', function(){
	   var supporter = $(this).attr('supporter');
	   var matchregistnum = $(this).attr('matchregistnum');
	   var teamcode = $(this).attr('teamcode');
	   var id = $(this).attr('id');
	   var team_code = '<%= teamInfo.getTeam_code()%>';
	   var type = '';
	   console.log(supporter);
	   console.log(id);
	   console.log(team_code);
	   var userId = '<%= loginUser%>';
	   if(userId != null) {
		   if(id == "exBtn") {
			   type = '1';
			   if (confirm("추방하시겠습니까?") == true){
				   $.ajax({
						 url: 'http://localhost:9090/GDJ47_kickkick_semi/team/leader.do',
						 data: {supporter:supporter, team_code:team_code, type:type},
						 success: function(data) {
							 console.log(data);
							 
							 if(data == 1) {
								 alert('추방되었습니다');
								 teamInfo(1);
							 }
						 }
					 });
				   
			   }else{
				     return false;
				     
			   }
		   }else if(id == "acBtn") {
			   type = '2';
			   if (confirm("가입하시겠습니까?") == true){
				   $.ajax({
						 url: 'http://localhost:9090/GDJ47_kickkick_semi/team/leader.do',
						 data: {supporter:supporter, team_code:team_code, type:type},
						 success: function(data) {
							 console.log(data);
							 
							 if(data == 2) {
								 alert('수락되었습니다');
								 support(1);
							 }
						 }
					 });
				   
			   }else{
				     return false;
				     
			   }
		   }else if(id == "caBtn") {
			   type = '3';
			   if (confirm("가입거절하시겠습니까?") == true){
				   $.ajax({
						 url: 'http://localhost:9090/GDJ47_kickkick_semi/team/leader.do',
						 data: {supporter:supporter, team_code:team_code, type:type},
						 success: function(data) {
							 console.log(data);
							 
							 if(data == 3) {
								 alert('취소되었습니다');
								 support(1);
							 }
						 }
					 });
				   
			   }else{
				     return false;
				     
			   }
		   }else if(id == "macBtn") {
			   type = '4';
			   if (confirm("매치수락하시겠습니까?") == true){
				   $.ajax({
						 url: 'http://localhost:9090/GDJ47_kickkick_semi/team/leader.do',
						 data: {match_regist_num:matchregistnum, team_code:teamcode, teamcode:team_code, type:type},
						 success: function(data) {
							 console.log(data);
							 
							 if(data == 4) {
								 alert('매치수락되었습니다');
								 match(1);
							 }else{
								 alert('1개팀만매칭가능');
								 
							 }
						 }
					 });
				   
			   }else{
				     return false;
				     
			   }
		   }else if(id == "mcaBtn") {
			   type = '5';
			   if (confirm("매치취소하시겠습니까?") == true){
				   $.ajax({
						 url: 'http://localhost:9090/GDJ47_kickkick_semi/team/leader.do',
						 data: {match_regist_num:matchregistnum, team_code:teamcode, teamcode:team_code, type:type},
						 success: function(data) {
							 console.log(data);
							 
							 if(data == 5) {
								 alert('매치취소되었습니다');
								 match(1);
							 }
						 }
					 });
				   
			   }else{
				     return false;
				     
			   }
		   }
		   
	   }else {
		   alert('로그인후이용가능');
	   }
	   
	});

	$(function(){
		
		$('#supporter').on('show.bs.modal', function (event) {
			  
		});
		
		$('#supporterBtn').click(function(){
			 if (confirm("가입하시겠습니까?") == true){
				 var position = $('#position').val();
				 var team_code = '<%=teamInfo.getTeam_code()%>';
				 var userId = '<%= loginUser%>';
				 if(userId != null) {
					   if ($('#team_leader').val() == userId) {
						   alert('팀장은가입할수없습니다');
						   
					   } else {
						   if (position == "") {
							   alert('포지션선택해주세요');
							   $("#position").focus();
							   
						   } else {
							   $.ajax({
								   url : 'http://localhost:9090/GDJ47_kickkick_semi/teamMemberRegist.do',
								   data : {
									   userId : userId,
									   position : position,
									   team_code : team_code
									   },
									   success : function(data) {
										   console.log(data);
										   if (data == 1) {
											   alert('3개이상팀가입불가');
											   
										   } else if (data == 2) {
											   alert('회원입니다');
											   
										   } else if (data == 3) {
											   alert('중복가입신청 불가');
											   
										   } else if (data == 4) {
											   alert('재신청되었습니다');
											   
										   } else {
											   alert('가입신청되었습니다');
											   
										   }
									   }
							   });
							   
						   }
						   
					   }
					   
				   } else {
					   alert('로그인후이용가능');
					   
				   }
				   
			 } else {
				 return false;
				 
			 }
			 
		});
	});

	let w = 600;
	let h = 400;
	let padding = 25;
	let avg1 = 60 / 100;
	let avg2 = 40 / 100;
	let avg3 = 10 / 100;
	let avg4 = 30 / 100;
	let avg5 = 20 / 100;

	let dataset = [

	[ 0, 20 ], [ 1, 80 ], [ 2, 20 ], [ 3, 40 ], [ 4, 40 ] ];

	
	}

</script>




<%@ include file="/views/common/footer.jsp" %>
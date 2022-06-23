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
	int rnum =registnum.get(0).getRegist_num();
	

%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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

				<hr>
				<div class="wrap2" id="wrap2">
				</div>
				<br>
				<br>
				<hr>

				

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
								<td><input type="button" id="macBtn" class="macBtn" matchregistnum="rnum" value="수락">
								<input type="button" id="mcaBtn" class="mcaBtn" matchregistnum="rnum" value="취소"></td></tr>
								
								
							
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

	/*create svg element*/
	let svg = d3.select("#wrap2").append("svg").attr("width", w).attr("height",
			h).attr("id", "chart");

	let drag = d3.behavior.drag().on("dragstart", dragstarted).on("drag",
			dragged).on("dragend", dragended);

	/*x scale*/
	let xScale = d3.scale.linear().domain([ 0, d3.max(dataset, function(d) {
		return d[0]
	}) ]).range([ padding, w - padding ]);

	/*y scale*/
	let yScale = d3.scale.linear().domain([ 0, 100 ]).range(
			[ h - padding, padding ]);

	/*x axis*/
	let xAxis = d3.svg.axis().scale(xScale).orient("bottom");

	/*y axis*/
	let yAxis = d3.svg.axis().scale(yScale).orient("left");

	let today = new Date();

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1; // 월
	let date = today.getDate(); // 날짜
	let day = today.getDay(); // 요일

	var data = [ {
		"date" : new Date(year, month - 1, date - 4),
		"value" : 0
	}, {
		"date" : new Date(year, month - 1, date),
		"value" : 100
	} ];

	var x_domain = d3.extent(data, function(d) {
		return d.date;
	}), y_domain = d3.extent(data, function(d) {
		return d.value;
	});

	var xScale2 = d3.time.scale().domain(x_domain) // values between for month of january
	.range([ padding, w - padding ]); // map these sides of the chart, in this case 100 and 600

	// define the y scale  (vertical)
	var yScale2 = d3.scale.linear().domain(y_domain).nice() // make axis end in round number
	.range([ h - padding, padding ]); // map these to the chart height, less padding.  In this case 300 and 100
	//REMEMBER: y axis range has the bigger number first because the y value of zero is at the top of chart and increases as you go down.

	var date_format = d3.time.format('%m/%d');
	//var  date_format = d3.time.format("%d %b");

	var yAxis2 = d3.svg.axis().orient("left").scale(yScale2);

	// define the x axis
	var xAxis2 = d3.svg.axis().orient("bottom").scale(xScale2).tickFormat(
			date_format);

	svg.append("g").attr("class", "yaxis axis").attr("transform",
			"translate(" + padding + ",0)").call(yAxis2);

	// draw x axis with labels and move to the bottom of the chart area
	svg.append("g").attr("class", "xaxis axis") // two classes, one for css formatting, one for selection below
	.attr("transform", "translate(0," + (h - padding) + ")").call(xAxis2);

	/* svg.append("text")
	.attr("text-anchor", "middle")  // this makes it easy to centre the text as the transform is applied to the anchor
	.attr("transform", "translate("+ (padding/2) +","+(h/2)+")rotate(0)")  // text is drawn off the screen top left, move down and out and rotate
	.text("승률");
	
	svg.append("text")
	.attr("text-anchor", "middle")  // this makes it easy to centre the text as the transform is applied to the anchor
	.attr("transform", "translate("+ (w/2) +","+(420-(padding/2))+")")  // centre below axis
	.text("최근날짜");    */

	/*define line*/
	let lines = d3.svg.line().x(function(d) {
		return xScale(d[0])
	}).y(function(d) {
		return yScale(d[1])
	}).interpolate("monotone");

	/*append line*/
	let path = svg.append("path").attr({
		d : lines(dataset),
		'class' : "lineChart"
	});

	console.log(path);

	svg.select(".lineChart").style("opacity", 0).transition().duration(2500)
			.delay(1000).style("opacity", 1);

	/*add points*/
	let points = svg.selectAll("circle").data(dataset).enter().append("circle")
			.call(drag);

	console.log(points);

	/*point attributes*/
	points.attr("cy", 0).transition().duration(1500).delay(function(d, i) {
		return i * 100 + 500
	}).ease("elastic").attr({
		cx : function(d) {
			return xScale(d[0])
		},
		cy : function(d) {
			return yScale(d[1])
		},
		r : 7,
		'class' : "datapoint",
		id : function(d, i) {
			return i
		}

	}).style("opacity", 1);

	let xMax = d3.max(dataset, function(d) {
		return d[0]
	}), yMax = d3.max(dataset, function(d) {
		return d[1]
	});

	function dragstarted() {
		d3.event.sourceEvent.stopPropagation();
		d3.select(this).classed("dragging datapoint", true);
	}

	function dragged() {
		d3.select(this).attr({
			cx : Math.max(padding, Math.min(d3.event.x, w - padding)),
			cy : Math.max(padding, Math.min(d3.event.y, h - padding))
		});
	}

	function dragended() {
		d3.select(this).classed("datapoint", true);
		// get id of dragged point
		let id = d3.select(this).attr("id"),
		// get new absolute position coordinates of the point
		xPos = d3.select(this).attr("cx"), yPos = h
				- d3.select(this).attr("cy");

		// convert absolute position coordinates relative to scales
		xPos = (xPos - padding) * (xMax / (w - padding * 2));
		yPos = (yPos - padding) * (yMax / (h - padding * 2));
		dataset[id][0] = xPos;
		dataset[id][1] = yPos;

		// update line
		svg.select(".lineChart").transition().duration(500).attr("d",
				lines(dataset));
	}

</script>




<%@ include file="/views/common/footer.jsp" %>
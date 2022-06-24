<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import = "com.match.model.vo.*" %>
 <%List<Match> m = (List<Match>)request.getAttribute("matcharr");

 String pagebar = (String)request.getAttribute("pageBar");%>
<%@ include file="/views/common/header.jsp" %>
 <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
<style>
.simple {
    display: inline-block;
    font: inherit;
    font-weight: bold;
    font-size: 20px;
    cursor: pointer;
    background-color: gray;
    color: #fff;
    text-decoration: none;
    padding: 10px 25px;
    border: none;
    border-bottom: 3px solid black;
    border-radius: 3px;
}
.simple:hover {background-color: lightgray;}
.acBtn {
    background-color: #33DF5E;
    display: inline-block;
    font: inherit;
    font-weight: bold;
    font-size: 15px;
    cursor: pointer;
    color: #fff;
    text-decoration: none;
    padding: 5px 10px;
    border: none;
    border-bottom: 3px solid #3f84b0;
    border-radius: 3px;
    margin-bottom: 10px;
    
}
.acBtn:hover{
	background: #36BF59;
}
.caBtn {
    background-color: #f8585b;
    display: inline-block;
    font: inherit;
    font-weight: bold;
    font-size: 15px;
    cursor: pointer;
    color: #fff;
    text-decoration: none;
    padding: 5px 10px;
    border: none;
    border-bottom: 3px solid #3f84b0;
    border-radius: 3px;
    margin-bottom: 10px;
   
}
.matchSearch{
	width:300px; 
  	height:50px;
	border:3px solid #ccc;
}
.matchSearch::placeholder{
	color: gray;
	font-size: large;
	font-weight:200;
}
.matchSearch2{
	width:100px; 
  	height:50px;
	border:3px solid #ccc;
}

</style>
<%
String userId = null;
if(loginMember!=null){
	 userId= loginMember.getEmail();
}
 %>

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
		<h1 style="text-align:center">매치 정보 보기</h1>
		<br>
		<div id="search-container" style="text-align:center">
			<select id="searchType" class='matchSearch2'>
				검색타입 : 
        		<option value="team">팀명</option>
        		<option value="place" >장소</option>
        		<option value="gender">성별</option>
        		<option value="age">연령대</option>
        		<option value="member">매치인원</option>
        	</select>
        	<div id="search-team">
        		<form action="<%=request.getContextPath()%>/matchsearch.do">
        			<input type="hidden" name="searchType" value="team" >
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 팀명을 입력하세요"  class="matchSearch">
        			
        			</datalist>
        			<button type="submit" class='simple'>검색</button>
        		</form>
        	</div>
        	<div id="search-place">
        		<form action="<%=request.getContextPath()%>/matchsearch.do">
        			<input type="hidden" name="searchType" value="place">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 장소를 입력하세요" class="matchSearch">
        			<button type="submit" class='simple'>검색</button>
        		</form>
        	</div>
        	<div id="search-gender" style="border:3px solid #ccc;height:55">
        		<form action="<%=request.getContextPath()%>/matchsearch.do">
        			
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" value="남자그룹" >남자그룹</label>
        			<label><input type="radio" name="searchKeyword" value="여자그룹" >여자그룹</label>
        			<label><input type="radio" name="searchKeyword" value="남녀그룹" >남녀그룹</label>
        			
        			<button type="submit" class='simple'>검색</button>
        		</form>
        	</div>
        	<div id="search-age" style="border:3px solid #ccc;height:55">
        		<form action="<%=request.getContextPath()%>/matchsearch.do">
        			
        			<input type="hidden" name="searchType" value="age">
        			<label><input type="radio" name="searchKeyword" value="10대" >10대</label>
        			<label><input type="radio" name="searchKeyword" value="20대" >20대</label>
        			<label><input type="radio" name="searchKeyword" value="30대" >30대</label>
        			<label><input type="radio" name="searchKeyword" value="40대" >40대</label>
        			<label><input type="radio" name="searchKeyword" value="50대" >50대</label>
        			
        			<button type="submit" class='simple'>검색</button>
        		</form>
        	</div>
        	<div id="search-member" style="border:3px solid #ccc;height:55">
        		<form action="<%=request.getContextPath()%>/matchsearch.do">
        			
        			<input type="hidden" name="searchType" value="member">
        			<label><input type="radio" name="searchKeyword" value="3vs3" >3vs3</label>
        			<label><input type="radio" name="searchKeyword" value="4vs4" >4vs4</label>
        			<label><input type="radio" name="searchKeyword" value="5vs5" >5vs5</label>
        			<label><input type="radio" name="searchKeyword" value="6vs6" >6vs6</label>
        			<label><input type="radio" name="searchKeyword" value="7vs7" >7vs7</label>
        			<label><input type="radio" name="searchKeyword" value="8vs8" >8vs8</label>
        			
        			<button type="submit" class='simple'>검색</button>
        		</form>
        	</div>
        	
		</div>
		
		
		<br><br>
		<%if(userId!=null){ %>
		<button type="button" id="matchRegist" style="float: right;" class="simple" onclick="fn_enrollmatch();">매치등록</button>
				<br><br>	
		<%} %>	
			
		<div class="container2">
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
				<td><% if(m1.getRegist_status().equals("Y")){%>
				<input type="button" id="acBtn" class="acBtn" registnum="<%=m1.getRegist_num()%>" branchnum="<%=m1.getRegist_branch_num()%>" stadiumnum="<%= m1.getRegist_stadium_num()%>" reservationcode=<%=m1.getRegist_reservation_code()%> value="신청하기">
				 <%}else{ %>
				 	<input type="button" id="caBtn" class="caBtn" value="신청불가">
					<%} %>
				</td>
			
				
			</tr>
			<%} %>
			
			
		</tbody>
		<%} %>
		
	
	
	</table>
	</div>
		<div class ="" id="pageBar" style="text-align:center">
		<%=pagebar %></div>
		
	
	</div>

	
	
	<script>
		const fn_matchSearch=()=>{
			
		window.open("<%=request.getContextPath()%>/matchsearch.do", "matchSearch", "width=500, height=680");
		
	};
	
	const fn_enrollmatch=()=>{
		var userId='<%=userId%>';
		console.log(userId);
		if(userId!='null'){
			window.open("<%=request.getContextPath()%>/enrollmatch.do", "enrollmatch", "width=500, height=150");
		}else{
			 alert('로그인후이용가능');
			   location.assign('<%=request.getContextPath()%>/loginPage.do');
		}
		
	}
	$(document).on('click', '#acBtn', e=>{
		   var registnum = $(e.target).attr('registnum');		
		   var branchnum = $(e.target).attr('branchnum');		
		   var stadiumnum = $(e.target).attr('stadiumnum');		
		   var reservationcode = $(e.target).attr('reservationcode');
		   console.log(registnum);
		   console.log(branchnum);
		   console.log(stadiumnum);
		   console.log(reservationcode);
		   var userId = '<%=userId %>';
			console.log(userId);
			if(userId != 'null') {
				   if (confirm("신청하시겠습니까?") == true){
					   $.ajax({
							 url: '<%=request.getContextPath()%>/matchApplication.do',
							 data: {userId:userId,
								 regist_num:registnum,
								 branch_num:branchnum, stadium_num:stadiumnum,
								 reservation_code:reservationcode},
							 success: data=>{
								 console.log(data);
								 
								 if(data == 1) {
									 alert('자기팀에게신청불가');
								 }else if(data == 2){
									 alert('중복신청불가');							 
								 }else if(data == 3){
									 alert('신청되었습니다');							 
								 }else if(data == 0){
									 alert('팀없음');							 
								 }
							 }
						 });
					   
				   }else{
					     return false;
					     
				   }
			   }else {
				   alert('로그인후이용가능');
				   location.assign('<%=request.getContextPath()%>/loginPage.do');
			   }
	}
		);
	
	</script>
	<script>
		$(()=>{//자동실행함수
			$("#searchType").change(e=>{
				const type = $(e.target).val();
				console.log(type);
				$("#search-container>div[id!=search-]").hide();
				$("#search-"+type).css("display","inline-block");
			});
			$("#searchType").change();
			
		})
	
	
	</script>
		


<%@ include file="/views/common/footer.jsp" %>
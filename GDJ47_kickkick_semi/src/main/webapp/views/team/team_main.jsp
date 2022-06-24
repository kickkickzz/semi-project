<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List,com.team.model.vo.Team" %>
<%
	String loginUser = ((Member)session.getAttribute("loginMember")).getEmail();
	List<Team> list=(List<Team>)request.getAttribute("list");
	String searchType=request.getParameter("searchType");
	String keyword=request.getParameter("searchKeyword");
//나중에페이징처리
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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#content{
	width:100%;
	margin: 0 auto;
	text-align: center;
	/* font-size: 1.2vw; */
}
.container-for-carousel{
	width:100%;
}
.container-for-content{
		padding-top: 3%;
		margin-left: 5%;
		margin-right: 5%;
		padding-left: 10%;
		padding-right: 10%;
		padding-bottom: 5%;
		/*내용 컴포넌트를 넣는 곳*/
		background-color: rgba(223,232,225,0.1); 
}
td{
	font-size: 1.4em;
	margin:auto 0;
	color: gray;
}
.search-teamName{
	width:300px; 
  	height:50px;
	border:3px solid #ccc;
	
	
}
.search-teamName::placeholder{
	color: gray;
	font-size: large;
	font-weight:200;
}
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
    border-bottom: 3px solid #3f84b0;
    border-radius: 3px;
}
.simple:hover {background-color: lightgray;}


</style>

<section id="notice-container">

        
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
				<br><br>
				
				<% if(loginUser != null) { %>
				<button type="button" id="teamRegist" style="float: right;"
					class="simple" data-toggle="modal"
					data-target="#exampleModal" data-whatever="@mdo">팀등록</button>
				<% } %>
					<br>
				<br>
				<div class="wrap4" id="wrap4">
					<hr>
					<h1>팀정보 보기</h1>
					<hr>
					<table class="table table-hover">
						<thead class="thead-dark">
							<tr>
								<th scope="col">no</th>
								<th scope="col">팀이름</th>
								<th scope="col">성별</th>
								<th scope="col">연령대</th>
								<th scope="col">지역</th>
							</tr>
						</thead>
						<tbody>
						
							<%if(list.isEmpty()){ %>
				<tr>
					<td colspan="5">조회된 팀이 없습니다.</td>
				</tr>
			<%}else{
				for(Team t: list){%>
							
					<tr onclick="location.href='<%=request.getContextPath()%>/team/teamMemberInfo.do?team_code=<%=t.getTeam_code()%>'">
						<td><%=t.getTeam_name() %></td>
						<td>
							<%=t.getTeam_gender() %>
						</td>
						<td><%=t.getTeam_age() %></td>
						
						<td><%=t.getTeam_region() %></td>
							</tr>
				<%	}%>
				<%}%>
				
						</tbody>
					</table>
					<div style="left : 35%;">
		<form action="<%=request.getContextPath()%>/team/searchTeam.do">
			<input type="hidden" name="searchType" value="team_name" class="search-teamName">
			<input type="text" name="searchKeyword" size="40" style="float: left; " class="search-teamName"
			placeholder="검색할 이름을 입력하세요">
			<button type="submit" class="simple" style="float: left; margin-left:10px;">검색</button>
		</form>
    </div>
				</div>
			</div>
		</div>
		<div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>
	</section>
	
	<!-- modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">&lt;팀등록&gt;</h4>
				</div>
				<form action="<%= request.getContextPath() %>/team/teamRegistEnd.do" method="post" encType="multipart/form-data" onsubmit="return validate();">
					<div class="modal-body">
							
							<div class="form-group">
								<label for="recipient-name" class="control-label"
										style="font-size: 25px;">팀이름</label>
								<div class="input-group input-group-lg">
									<input type="text" class="form-control input-sm" id="team_name" name="team_name"
										placeholder="팀이름입력">
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group mb-3">
									<label for="recipient-name" class="control-label"
										style="font-size: 25px;">평균성별 : </label>
									&nbsp;&nbsp;&nbsp;&nbsp; <span class="input-group-addon">
										<i class="fa fa-user fa" aria-hidden="true"></i>
									</span> <select id="team_gender" name="team_gender">
										<option value="">팀 평균성별 선택</option>
										<option value="남자그룹">남자그룹</option>
										<option value="여자그룹">여자그룹</option>
										<option value="남녀그룹">남녀그룹</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group mb-3">
									<label for="recipient-name" class="control-label"
										style="font-size: 25px;">평균나이 : </label>
									&nbsp;&nbsp;&nbsp;&nbsp; <span class="input-group-addon">
										<i class="fa fa-user fa" aria-hidden="true"></i>
									</span> <select id="team_age" name="team_age">
										<option value="">팀 평균나이 선택</option>
										<option value="10대">10대</option>
										<option value="20대">20대</option>
										<option value="30대">30대</option>
										<option value="40대">40대</option>
										<option value="50대">50대</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group mb-3">
									<label for="recipient-name" class="control-label"
										style="font-size: 25px;">지역 : </label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="input-group-addon">
										<i class="fa fa-user fa" aria-hidden="true"></i>
									</span> <select id="sido1" name="sido1">
									</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select name="gugun1"
										id="gugun1" name="gugun1">
									</select>
								</div>
							</div>
							
							<div class="form-group" style="margin: 8px 0 8px;">
								<label for="recipient-name" class="control-label"
									style="font-size: 30px;">팀마크</label>
							</div>
							
							<div class="selectCover" style="padding-left: 0;">
								<img id="cover" src="<%= request.getContextPath() %>/views/common/images/마크등록.png"
									style="width: 282px; height: 268px;" />
							</div>
							<br>
						
							<input id="fileName" name="fileName" class="upload-name" value="파일선택" style="width: 200px;" readonly>
							<!-- <input type="file" id="" multiple="multiple"> -->
							<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1">
					</div>
					<div class="modal-footer">
						<button type="submit" id="teamRegistBtn" class="simple">팀 등록하기</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
			

	
</section>

<script type="text/javascript">
	
	function validate() {
		var userId = '<%= loginUser%>';
		var team_name = $('#team_name').val();
		var team_gender = $('#team_gender').val();
		var team_age = $('#team_age').val();
		var sido1 = $('#sido1').val();
		
		var team_region = $('#sido1').val() + ' ' + $('#gugun1').val();
		var team_mark = $('#fileName').val();
			
		if(team_name == "") {
			alert('팀이름 입력해주세요');
			$('#team_name').focus();
			return false;
		}else if(team_gender == ""){
			alert('평균성별 선택해주세요');
			$('#team_gender').focus();
			return false;
		}else if(team_age == ""){
			alert('평균나이 선택해주세요');
			$('#team_age').focus();
			return false;
		}else if(sido1 == "시/도 선택"){
			alert('시/도 선택해주세요');
			$('#sido1').focus();
			return false;
		}else if(team_mark == "파일선택"){
			alert('팀마크 등록해주세요');
			$('#fileName').focus();
			return false;
		}else {
			return true;
			
		}
	}

	
	function readURL(input) {
        if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
                $('#cover').attr('src', e.target.result);
                $('#fileName').val(input.files[0].name);
                console.log($('#fileName').val());
            }
          reader.readAsDataURL(input.files[0]);
        }
    }
	

	$('#thumbnailImg1').change(function() {
		readURL(this);
	});
	
	$('document').ready(function() {
		var area0 = [ "시/도 선택", "서울특별시", "인천광역시", "대전광역시","광주광역시", "대구광역시", "울산광역시", "부산광역시", "경기도","강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도" ];
		var area1 = [ "강남구", "강동구", "강북구", "강서구", "관악구", "광진구","구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구","마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" ];
		var area2 = [ "계양구", "남구", "남동구", "동구", "부평구", "서구","연수구", "중구", "강화군", "옹진군" ];
		var area3 = [ "대덕구", "동구", "서구", "유성구", "중구" ];
		var area4 = [ "광산구", "남구", "동구", "북구", "서구" ];
		var area5 = [ "남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군" ];
		var area6 = [ "남구", "동구", "북구", "중구", "울주군" ];
		var area7 = [ "강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군" ];
		var area8 = [ "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시", "가평군", "양평군", "여주군", "연천군" ];
		var area9 = [ "강릉시", "동해시", "삼척시", "속초시", "원주시", "춘천시", "태백시", "고성군", "양구군", "양양군", "영월군", "인제군", "정선군", "철원군", "평창군", "홍천군", "화천군", "횡성군" ];
	 	var area10 = [ "제천시", "청주시", "충주시", "괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "증평군", "진천군", "청원군" ];
		var area11 = [ "계룡시", "공주시", "논산시", "보령시", "서산시", "아산시", "천안시", "금산군", "당진군", "부여군", "서천군", "연기군", "예산군", "청양군", "태안군", "홍성군" ];
		var area12 = [ "군산시", "김제시", "남원시", "익산시", "전주시", "정읍시", "고창군", "무주군", "부안군", "순창군", "완주군", "임실군", "장수군", "진안군" ];
		var area13 = [ "광양시", "나주시", "목포시", "순천시", "여수시", "강진군", "고흥군", "곡성군", "구례군", "담양군", "무안군", "보성군", "신안군", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군" ];
		var area14 = [ "경산시", "경주시", "구미시", "김천시", "문경시", "상주시", "안동시", "영주시", "영천시", "포항시", "고령군", "군위군", "봉화군", "성주군", "영덕군", "영양군", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군" ];
		var area15 = [ "거제시", "김해시", "마산시", "밀양시", "사천시", "양산시", "진주시", "진해시", "창원시", "통영시", "거창군", "고성군", "남해군", "산청군", "의령군", "창녕군", "하동군", "함안군", "함양군", "합천군" ];
		var area16 = [ "서귀포시", "제주시", "남제주군", "북제주군" ];

		$("select[name^=sido]").each(function() {
			$selsido = $(this);
			$.each(eval(area0),function() {
				$selsido.append("<option value='"+this+"'>" + this + "</option>");
				
			});
			
			$selsido.next().append("<option value=''>구/군 선택</option>");
			
		});

		$("select[name^=sido]").change(function() {
			
			var area = "area" + $("option", $(this)).index($("option:selected",$(this)));
							
			var $gugun = $(this).next();
			$("option", $gugun).remove();
			if (area == "area0") {
				$gugun.append("<option value=''>구/군 선택</option>");
											
			}else {
				$.each(eval(area),function() {
					$gugun.append("<option value='"+this+"'>"+ this+ "</option>");
																
				});
											
			}
										
		});
		
			
	});
		
	
</script>
 
       
        
       

<%@ include file="/views/common/footer.jsp" %>
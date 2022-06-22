<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
      
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
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
    padding: 20px 45px;
    border: none;
    border-bottom: 3px solid lightgray;
    border-radius: 3px;
    width: 500px;
}
.simple:hover {background-color: #61a8d5;}
</style>
<body>
	<h4>&lt;매치 상세검색&gt;</h4>
	<br>
	<br>
	<form id="matchSearchForm">
		<div class="form-group">
			<label for="recipient-name" class="control-label"
				style="font-size: 25px;">팀이름 : </label>
			<div class="input-group input-group-lg">
				<input type="text" class="form-control input-sm" id="match_name"
					placeholder="팀이름입력">
			</div>
		</div>
		<div class="form-group">
			<div class="input-group mb-3">
				<label for="recipient-name" class="control-label"
					style="font-size: 25px;">성별 : </label> &nbsp;&nbsp;&nbsp;&nbsp; <span
					class="input-group-addon"> <i class="fa fa-user fa"
					aria-hidden="true"></i>
				</span> <select id="match_gender">
					<option value="">성별 선택</option>
					<option value="남자그룹">남자그룹</option>
					<option value="여자그룹">여자그룹</option>
					<option value="남녀그룹">남녀그룹</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group mb-3">
				<label for="recipient-name" class="control-label"
					style="font-size: 25px;">연령대 : </label> &nbsp;&nbsp;&nbsp;&nbsp; <span
					class="input-group-addon"> <i class="fa fa-user fa"
					aria-hidden="true"></i>
				</span> <select id="match_age">
					<option value="">연령 선택</option>
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
					style="font-size: 25px;">매치인원 : </label> &nbsp;&nbsp;&nbsp;&nbsp; <span
					class="input-group-addon"> <i class="fa fa-user fa"
					aria-hidden="true"></i>
				</span> <select id="match_matchMember">
					<option value="">매치인원 선택</option>
					<option value="3vs3">3 vs 3</option>
					<option value="4vs4">4 vs 4</option>
					<option value="5vs5">5 vs 5</option>
					<option value="6vs6">6 vs 6</option>
					<option value="7vs7">7 vs 7</option>
					<option value="8vs8">8 vs 8</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="recipient-name" class="control-label"
				style="font-size: 25px;">장소 : </label>
			<div class="input-group input-group-lg">
				<input type="text" class="form-control input-sm" id="branch_address"
					placeholder="장소입력">
			</div>
		</div>
		<div class="form-group">
			<div class="input-group mb-3">
				<label for="recipient-name" class="control-label"
					style="font-size: 25px;">이용날짜 : </label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="input-group-addon">
					<i class="fa fa-user fa" aria-hidden="true"></i>
				</span> 
				<input type="date" id="date">
			</div>
		</div>
		
	</form>
	<br>
	
	<input type="button" id="confirm" class="simple" value="매치검색" onclick="matchSearch();">
	<!-- <input type="button" id="cancel" value="취소" onclick="window.close();"> -->
	
	<script>
		
		function matchSearch(){
			opener.document.match.search_match_name.value = $("#match_name").val();
			opener.document.match.search_match_gender.value = $("#match_gender").val();
			opener.document.match.search_match_age.value = $("#match_age").val();
			opener.document.match.search_match_matchMember.value = $("#match_matchMember").val();
			opener.document.match.search_date.value = $("#date").val();
			opener.document.match.search_startTime.value = $("#startTime").val();
			opener.document.match.search_endTime.value = $("#endTime").val();
			opener.parent.matchSearch2();
			self.close();
		}
		
		
		
	</script>
</body>
</html>
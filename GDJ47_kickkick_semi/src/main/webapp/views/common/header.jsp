<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member" %>    
<% Member loginMember = (Member)session.getAttribute("loginMember"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ㅋ킥킥ㅋ</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/header-footer.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
</head>
<body>
<header>
	<nav id="header-container">
		<div id="header-top">
			<div id="logo"  onclick="location.assign('<%=request.getContextPath()%>')">
				<img id="semi-logo" src="<%=request.getContextPath()%>/images/세미로고.png">
				<p>ㅋ킥킥ㅋ</p>
			</div>
			<div id="search">

				<form action="<%=request.getContextPath()%>/searchstadium.do" onsubmit="return fn_searchData(event);">
				<input type="text" placeholder="찾으시는 지역 및 구장을 입력하세요!" name="searchstadium" onkeyup="checkData(event);" id="search-sta">

				<i id="searchicon" class="fa-solid fa-magnifying-glass"></i>
				</form>
			</div>
			<div id="icons">
				<%if(loginMember!=null){ %>
					<div id="wellcome-user"><%=loginMember.getName()%>님 환영합니다!</div>
				<%}%>
				<%if(loginMember!=null){ %>
					<i id="home" class="fa-solid fa-user" onclick="location.assign('<%=request.getContextPath()%>/member/memberView.do')"></i>
				<%}else{%>
					<i id="home" class="fa-solid fa-user" onclick="location.assign('<%=request.getContextPath()%>/loginPage.do')"></i>
				<%} %>
				<i id="alert" class="fa-solid fa-bell"></i>
				<i id="like" class="fa-solid fa-heart"></i>
				<i id="menu" class="fa-solid fa-bars"></i>
			</div>
		</div>
		<div id="header-bottom">
			<ul id="header-menu">
				<li id="notice" onclick="fn_board();">공지사항</li>
				<li>구장</li>
				<li onclick="fn_stadium();">예약</li>
				<li onclick="fn_team();">팀 관리</li>
				<li>매칭</li>
			</ul>
		</div>
	</nav>
</header>
<script>
	const fn_stadium=()=>{
		location.assign('<%=request.getContextPath()%>/stadium.do');
	}
	const fn_board=()=>{
		location.assign('<%=request.getContextPath() %>/boardlist.do');
	}

	const fn_team=()=>{
		location.assign('<%=request.getContextPath()%>/team.do');
	}


	const fn_searchData=e=>{
		if($(e.target).find("input").val().length==0){
			alert("값을 입력하고 조회하세요.");
			return false;
		}
	}

</script>


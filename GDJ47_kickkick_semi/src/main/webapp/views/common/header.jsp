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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/main.js"></script>
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
				<form action="<%=request.getContextPath()%>/searchstadium.do">
				<input type="text" placeholder="찾으시는 지역 및 구장을 입력하세요!" name="searchstadium" onkeyup="submit">
				
				<i id="searchicon" class="fa-solid fa-magnifying-glass"></i>
				</form>
			</div>
			<div id="icons">
				<%if(loginMember!=null){ %>
					<div id="wellcome-user">loginMember.getName()님 환영합니다!</div>
				<%}%>
				<i id="home" class="fa-solid fa-user" onclick="location.assign('<%=request.getContextPath()%>/member/memberView.do')"></i>
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
				<li>용품</li>
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
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ㅋ킥킥ㅋ</title>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
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
				<input type="text" placeholder="찾으시는 지역 및 구장을 입력하세요!">
				<i id="searchicon" class="fa-solid fa-magnifying-glass"></i>
			</div>
			<div id="icons">
				<i id="home" class="fa-solid fa-house-chimney"></i>
				<i id="alert" class="fa-solid fa-bell"></i>
				<i id="like" class="fa-solid fa-star"></i>
				<i id="menu" class="fa-solid fa-bars"></i>
			</div>
		</div>
		<div id="header-bottom">
		
		</div>
	</nav>
</header>


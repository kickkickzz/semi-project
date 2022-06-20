<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%
	Member m=(Member)request.getAttribute("member");
%>
<!-- 여기가 마이페이지 초기화면 -->
<link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/dashboard/">

<!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">


<script src="https://kit.fontawesome.com/3de5dd50e8.js" crossorigin="anonymous"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
</style>


<section>
<div class="container-fluid">
  <div class="row">
   <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
     <ul class="nav flex-column">
      <li class="nav-item">
      <li>
       <p></p>
      </li>
      <li>
       <p></p>
      </li>
      <li>
        <a class="nav-link active" href="<%=request.getContextPath()%>/updatemember.do" style="color: black">
         <i class="fa-solid fa-user"></i>
         <span data-feather="home"></span>
         회원 정보
        </a>
      </li>
      <li class="nav-item">
       <a class="nav-link" href="<%=request.getContextPath()%>/member/reservationlist.do" style="color: black">
       <i class="fa-solid fa-list"></i>
       <span data-feather="file"></span>
       예약현황
       </a>
      </li>
      <li class="nav-item">
       <a class="nav-link" href="<%=request.getContextPath()%>/member/myteam.do" style="color: black">
        <i class="fa-solid fa-people-group"></i>
        <span data-feather="shopping-cart"></span>
        나의 팀 정보
        </a>
      </li>
      <li class="nav-item">
       <a class="nav-link" href="<%=request.getContextPath()%>/logoutMember.do" style="color: black">
       <i class="fa-solid fa-arrow-right-from-bracket"></i>
       <span data-feather="users"></span>
       로그아웃
       </a>
      </li>
      <li class="nav-item">
       <a class="nav-link" href="" onclick="fn_delete();" style="color: black"> <!-- request.getContextPath()%>/deleteMember.do?email=loginMember.getEmail()%> -->
       <i class="fa-solid fa-arrow-right-from-bracket"></i>
       <span data-feather="users"></span>
       회원탈퇴
       </a>
      </li>
     </ul>  
    </div>
   </nav>
   

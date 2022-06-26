<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import = "com.reservation.model.vo.PayHistory,java.util.List" %>
 
<%
 List<PayHistory> list = (List<PayHistory>)request.getAttribute("paylist");
 %>
<%
List<Team> result = (List<Team>)request.getAttribute("result");
%>
 
<%@ include file="/views/common/header.jsp" %>
<%
String type=null;
if(loginMember!=null){
	type=loginMember.getType();	 
}
%>

 <link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/dashboard/">

    


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


<link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">


<script src="https://kit.fontawesome.com/3de5dd50e8.js" crossorigin="anonymous"></script>

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

    
    <!-- Custom styles for this template -->
    <link href="dashboard.css" rel="stylesheet">
  </head>
  <body>
    
<section>
<div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <li>
          	<p></p>
          </li>
          <li>
       		<p></p>
      	  </li>          
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath()%>/memberview.do" style="color: black">
              <i class="fa-solid fa-user"></i>
              <span data-feather="home"></span>
              회원 정보
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/member/reservationlist.do?email=<%=loginMember.getEmail()%>" style="color: black">
                <i class="fa-solid fa-list"></i>
                <span data-feather="file"></span>
              예약현황
            </a>
          </li>
           <%if(type.equals("M")){ %>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/branch.do" style="color: black">
               <i class="fa-solid fa-location-dot"></i>
                <span data-feather="file"></span>
              지점정보
            </a>
          </li>
          <%} %>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/member/myteam.do?email=<%=loginMember.getEmail()%>" style="color: black">
              <i class="fa-solid fa-people-group"></i>
              <span data-feather="shopping-cart"></span>
              나의 팀
            </a>
          </li>
      	<li class="nav-item">
	       	 <a class="nav-link" href="<%=request.getContextPath()%>" style="color: black">
	         <i class="fa-solid fa-user-plus"></i>
	         <span data-feather="shopping-cart"></span>
	         가입한 팀
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
            <i class="fa-solid fa-user-minus"></i>
            <span data-feather="users"></span>
            회원탈퇴
            </a>
          </li>		   
        </ul>
      </div>
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <br><br>
      <h2>가입한 팀</h2>
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>

            <tr>
              <th scope="col">No</th>
              <th scope="col">로고</th>
              <th scope="col">이름</th>
              <th scope="col">정보</th>
              <th scope="col">탈퇴</th>
            </tr>
          </thead>
          <tbody>
          <%if(result!=null&&result.size()>0) {%>
          	<%for(Team t : result){%>
          	<tr>
          		<td><%=t.getTeam_code()%></td>
          		<td><%=t.getTeam_mark_img()%></td>
          		<td><%=t.getTeam_name()%></td>
          		<td><%=t.getTeam_region()%></td>
          		<td><input id="btn_teammember" type="button" value="탈퇴"></td>
          	</tr>
          	<%} %>
          <%}else{%>
          <tr>
          		<td colspan="5" style="text-align:center;">가입한 팀이 없습니다.</td>
          </tr>
          <%} %>
         </tbody>
        </table>
      </div>
    </main>
  </div>
</div>
</section>
</body>
<style>
.col-lg-2 {
    height : 800px;
}
div.row{
	padding :0% 0% 0% 280px;
}
.col-md-9{
}
section{
	padding-top : 0px;
}
header{
    height : 136px;
}
.table{
	width : 65%;
}
</style>
<script>
const fn_delete = ()=>{
	open("<%=request.getContextPath()%>/deletemember.do?email=<%=loginMember.getEmail()%>","_blank","width=400, height=210 ,left=500, top=200");
}


$("#btn_teammember").click(e=>{
	var email = '<%=loginMember.getEmail()%>';
	if(confirm('한번 탈퇴한 팀은 다시 가입할 수 없습니다. 정말 탈퇴하시겠습니까?')){
		$.ajax({
			url : "<%=request.getContextPath()%>/deleteteammember.do",
			type : "post",
			data : {supporterEmail:email},
			success :(data)=>{
				if(data>0){
					alert('팀 탈퇴가 정상적으로 처리 되었습니다.');
					opener.location.reload();
				}else{
					alert('팀 탈퇴가 정상적으로 처리 되지 않았습니다.');
				}
			}
		})
	}else{
	}
})
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script><script src="dashboard.js"></script>

<%@ include file="/views/common/footer.jsp"%>
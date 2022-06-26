<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%
String type=null;
if(loginMember!=null){
	type=loginMember.getType();	 
}
Team team = (Team)request.getAttribute("team");
String[] newStr = team.getTeam_region().split("\\s+");
String si = newStr[0];
String gu = newStr[1];
%>
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
       	 <a class="nav-link" href="<%=request.getContextPath()%>/joinTeam.do?email=<%=loginMember.getEmail()%>" style="color: black">
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
      <h2>나의 팀 정보</h2><br>
      <%if(team==null){%>
      		<h2>조회된 팀이 없습니다.</h2>
      <%}else{ %>
      <form id="updateForm" action="<%=request.getContextPath()%>/teamUpdate.do" method="post">
      		<div>
		      <p style="margin-bottom : 3px;">팀마크</p>
		      <img src="<%=request.getContextPath() %>/resources/storage/<%= team.getTeam_leader()%>/team_img/<%= team.getTeam_mark_img()%>"
                        width="250px" height="200px" style="margin-bottom: 30px;"><br>
              <input id="fileName" name="fileName" class="upload-name" value="파일선택" style="width: 200px;" readonly>
							
			  <input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" style="margin-bottom:20px;">
		     </div>
      		<div>
		      <p style="margin-bottom : 3px;" class="pp">팀 이름</p>
		      <input type="text" value="<%=team.getTeam_name()%>" name="name" size="40" style="margin-bottom: 20px;">
		     </div>  
		     <div>
		      <p style="margin-bottom : 3px;" class="pp">팀 그룹</p>
		      <select name='team_gender' style="margin-bottom: 20px;">
				  <option value='<%=team.getTeam_gender()%>' selected><%=team.getTeam_gender()%></option>
				  <option value='남자그룹'>남자그룹</option>
				  <option value='여자그룹'>여자그룹</option>
				  <option value='남녀그룹'>남녀그룹</option>
			  </select>
		     </div>
		     <div>
		      <p style="margin-bottom : 3px;">팀 평균나이</p>
		      <select id="team_age" name="team_age" style="margin-bottom: 20px;">
					<option value='<%=team.getTeam_age() %>'><%=team.getTeam_age() %></option>
					<option value="10대">10대</option>
					<option value="20대">20대</option>
					<option value="30대">30대</option>
					<option value="40대">40대</option>
					<option value="50대">50대</option>
			  </select>
		     </div>  
		     <div>
		      <p style="margin-bottom : 3px;">지역</p>
		      <input type="text" name="region" value="<%=team.getTeam_region() %>" size="40" style="margin-bottom: 20px;">
		     </div>
		     <div>
		     <input type="button" onclick="fn_update();" value="수정">
      	     <input type="reset" value="취소">
      	     </div>
      	     <input type="hidden" name="email" value="<%=team.getTeam_leader()%>">
      	     </form>
      <%} %>
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
const fn_update= ()=>{
	$("#updateForm").submit();
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
</script>
<%@ include file="/views/common/footer.jsp" %>
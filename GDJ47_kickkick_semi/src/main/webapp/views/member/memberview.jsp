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
        <a class="nav-link active" href="" style="color: black">
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
   <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <br><br>
    <h2>회원 정보</h2>
    <form></form>
    <form id="updateForm" action="<%=request.getContextPath()%>/updatemember.do" method="post">
     <div>
      <p style="margin-bottom : 3px;" class="pp">이메일</p>
      <input type="text" name="email" value="<%=loginMember.getEmail()%>" style="margin-bottom : 7px;" size="63" readonly>
     </div>  
     <div>
      <p style="margin-bottom : 3px;" class="pp">이름</p>
      <input type="text" name="name" value="<%=loginMember.getName()%>" readonly style="margin-bottom : 10px;" size="63">
     </div>
     <div>
      <p style="margin-bottom : 3px;">비밀번호</p>
      <input type="button" onclick="updatePw()" value="비밀번호 변경">
     </div>  
     <div>
      <p style="margin-bottom : 3px;">생년월일</p>
      <input type="Date" name="birthday"  value="<%=loginMember.getBirthday()%>" placeholder="yyyyMMdd" style="margin-bottom : 10px;" size="63">
     </div>
     <div>
      <p style="margin-bottom : 3px;">연락처</p>
      <input type="text" name="phone" value="<%=loginMember.getPhone()%>" placeholder="-없이 입력" maxlength="11" style="margin-bottom : 10px;" size="63">
     </div>
     <div>
      <p style="margin-bottom : 3px;">주소</p>
      <input type="text" id="sample6_postcode" placeholder="우편번호" style="margin-bottom : 3px;">
	  <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" ><br>
	  <input type="text" id="sample6_address" name="address2" placeholder="주소" size="29" style="margin-bottom : 3px;">
	  <input type="text" id="sample6_extraAddress" name="address3" placeholder="참고항목"><br>
	  <input type="text" id="sample6_detailAddress"  name="address4" placeholder="상세주소" size="29" style="margin-bottom : 13px;">
     </div>
     <div class="update">
      <p style="margin-bottom : 3px;">성별</p>
      <input type="radio" name="gender" id="gender0" value="M" style="margin-bottom : 10px;" size="20">
       <label for="gender0">남</label>
      <input type="radio" name="gender" id="gender1" value="F">
       <label for="gender1">여</label>
     </div><br><br>
      <input type="button" onclick="fn_update();" value="수정"/>
      <input type="reset" value="취소">
    </form>

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

.bg-light {

}

</style>

<script>
const updatePw = ()=>{
	open("<%=request.getContextPath()%>/member/updatePassword.do?email=<%=loginMember.getEmail()%>","_blank","width=400, height=210 ,left=500, top=200"); /* 주소에 ?email=loginMember.getEmail() 추가해야함 */
}

const fn_delete = ()=>{
	open("<%=request.getContextPath()%>/deletemember.do?email=<%=loginMember.getEmail()%>","_blank","width=400, height=210 ,left=500, top=200");
}

const fn_update= ()=>{
	$("#updateForm").submit();
}


function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

</script>
<%@ include file="/views/common/footer.jsp" %>

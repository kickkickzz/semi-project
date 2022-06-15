<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Butterfly Bootstrap Template - Index</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Dosis:300,400,500,,600,700,700i|Lato:300,300i,400,400i,700,700i" rel="stylesheet">
  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@300&display=swap" rel="stylesheet">
  <script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
  <section id="contact" class="contact">
    <div id="container">
      <div class="row mt-5">
          <div class="col-lg-4">
            <div class="info">
              <div class="address">
                <i class="bi bi-person-circle"></i>
                <a href=""><h4>회원정보수정</h4></a>
              </div>
              <div class="email">
                <i class="bi bi-list-task"></i>
                <a href=""><h4>경기 신청내역</h4></a>
              </div>
              <div class="phone">
                <i class="bi bi-clipboard-data"></i>
                <a href=""><h4>나의 팀</h4></a>
              </div>
              <div class="logout">
                <i class="bi bi-door-open"></i>
                <a href=""><h4>로그아웃</h4></a>
              </div>
            </div>
          </div>
          <div class="col-lg-4">
           <form action="" method="post">
            <div class="update">
              <h2><strong>회원정보수정</strong></h2>
            </div>
            <div class="update">
              <p>이메일</p>
              <input type="text" value="" readonly>
            </div>  
            <div class="update">
              <p>이름</p>
              <input type="text" value="" readonly>
            </div>
            <div class="update">
              <p>비밀번호</p>
              <button onclick="">비밀번호 변경</button>
            </div>  
            <div class="update">
              <p>생년월일</p>
              <input type="text" name="birthday" value="" value="" placeholder="yyyyMMdd">
            </div>
            <div class="update">
              <p>연락처</p>
              <input type="text" name="phone" value="" placeholder="-없이 입력" maxlength="11">
            </div>
            <div class="update">
              <p>주소</p>
              <input type="text" name="address" value="" placeholder="띄어쓰기로 구분">
            </div>
            <div class="update">
              <p>성별</p>
              <input type="radio" name="gender" id="gender0" value="M">
							<label for="gender0">남</label>
							<input type="radio" name="gender" id="gender1" value="F">
							<label for="gender1">여</label>
            </div>
           </form>
           <input type="button" value="수정">
           <input type="button" value="취소">
          </div>
       </div>
    </div>
    </section>

    <div id="footer2">
      <footer>
        <div id="footer-header">
          <div id="fheader-left">
            <ul>
              <li>회사소개</li>
              <li>이용약관</li>
              <li>개인정보처리방침</li>
              <li>청소년보호방침</li>
              <li>광고/제휴문의</li>
              <li>매장안내</li>
            </ul>	
          </div>
          <div id="fheader-right">
            <ul>
              <li id="right1">판매자서비스</li><i class="fa-solid fa-chevron-down" style="font-size:10px;vertical-align: middle;"></i>
              <li id="right2">파트너센터</li><i class="fa-solid fa-chevron-down" style="font-size:10px;vertical-align: middle;"></i>
            </ul>
          </div>
        </div>
        <div id="footer-body">
          <div id="fbody-left">
            <h3>ㅋ킥킥ㅋ</h3>
            <p>
              대표이사 : 유병승&nbsp&nbsp&nbsp|&nbsp&nbsp
              서울시 금천구 가산디지털2로 115, 509호(가산동, 대륭테크노타운3차)&nbsp&nbsp&nbsp|&nbsp&nbsp
              호스팅서비스 사업자 : 구디아카데미<br>
              사업자번호 : 111-22-333444&nbsp&nbsp&nbsp|&nbsp&nbsp
              E-mail : kickkick47@naver.com
            </p>
            <br>
            <p>
              전국 각지의 풋살장을 한 곳에 모아 간편하게 구장을 예약하고, 이용할 수 있습니다.<br>
              깨끗한 용품을 통해 쾌적하고 즐거운 경기를 즐길 수 있습니다.
            </p>
          </div>
          <div id="fbody-right">
            <h3>고객센터 1588-1588</h3>
            <p>
              운영시간 : 평일 09:00 ~ 18:00<br>
              고객센터 바로가기&nbsp&nbsp<i class="fa-solid fa-chevron-right"style="font-size:3px;"></i><br>
              <br>
              <img id="qr" src="<%=request.getContextPath()%>/images/세미용.png">
              <p id="qrbox">
                <span style="font-size:13px; font-weight:bold; color:black;">ㅋ킥킥ㅋAPP다운</span><br>
                &nbsp&nbsp&nbsp스마트폰에서 QR 스캔
              </p>
            </p>
          </div>	
        </div>
        
      </footer>
    </div>
  
<%@ include file="/views/common/footer.jsp" %>
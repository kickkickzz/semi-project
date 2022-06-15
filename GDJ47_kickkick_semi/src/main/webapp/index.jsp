<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>


<section>
<!-- 이미지 슬라이더 시작 -->
	<div id="carouselExampleControls" class="carousel slide " data-bs-ride="carousel">
  		<div class="carousel-inner">
    		<div class="carousel-item active">
      			<img src="https://static-breeze.nike.co.kr/kr/ko_kr//cmsstatic/structured-content/65250/220401_fb_p1_bg.jpg" class="d-block w-100" alt="...">
    		</div>
    		<div class="carousel-item">
      			<img src="http://www.funsoccer.co.kr/design/funsoccer/greenfood/images/main_slide07071701.png" class="d-block w-100" alt="...">
    		</div>
    		<div class="carousel-item">
      			<img src="http://www.funsoccer.co.kr/design/funsoccer/greenfood/images/main_slide071301.jpg" class="d-block w-100" alt="...">
    		</div>
  		</div>
  		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
	    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    	<span class="visually-hidden">Previous</span>
  		</button>
  		<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
  		</button>
	</div>
<!-- 이미지 슬라이더 끝 -->

<!-- 공지사항 목록 -->
	<div id="notice-box1">
		<div class="card border-light mb-3 text-center" style="max-width: 18rem; padding:0px 15px;">
		  <div class="card-header">공지사항 제목</div>
		  <div class="card-body">
		    <h5 class="card-title">Light card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card border-light mb-3 text-center" style="max-width: 18rem; padding:0px 15px;" >
		  <div class="card-header">공지사항 제목</div>
		  <div class="card-body">
		    <h5 class="card-title">Light card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card border-light mb-3 text-center" style="max-width: 18rem ; padding:0px 15px;">
		  <div class="card-header">공지사항 제목</div>
		  <div class="card-body">
		    <h5 class="card-title">Light card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card border-light mb-3 text-center" style="max-width: 18rem; padding:0px 15px;">
		  <div class="card-header">공지사항 제목</div>
		  <div class="card-body">
		    <h5 class="card-title">Light card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
	</div>
<!-- 공지사항 목록 끝 -->
<!-- 구장 리스트 시작 -->
	<div id="stadium-list">
		<div class="row row-cols-1 row-cols-md-3 g-4">
			 <div class="col">
			 	<div class="card">
			    	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			        	<h5 class="card-title">7월 서서울호수공원 풋살장 대여 (주말주간,야간) 6월14일 오후2시 접수</h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">장소명</b> <div class="sch-rslt txt2 card-line">서서울호수공원(양천구)</div></li>
							<li class="li-user"><b class="user">이용대상</b> <div class="div-user txt2 card-line">제한없음</div></li>
							<li class="li-date"><b class="date1">접수기간</b> 2022.06.14 ~ 2022.07.30</li>
							<li class="li-date"><b class="date2">이용기간</b> 2022.07.01 ~ 2022.07.31</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			  	<div class="card">
			      	<img src="./images/함송 족구장.jpeg" class="card-img-top" alt="...">
			      	<div class="card-body">
			        	<h5 class="card-title">응애 풋살장</h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">장소명</b> <div class="sch-rslt txt2 card-line">서서울호수공원(양천구)</div></li>
							<li class="li-user"><b class="user">이용대상</b> <div class="div-user txt2 card-line">제한없음</div></li>
							<li class="li-date"><b class="date1">접수기간</b> 2022.06.14 ~ 2022.07.30</li>
							<li class="li-date"><b class="date2">이용기간</b> 2022.07.01 ~ 2022.07.31</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			        	<h5 class="card-title">응애 풋살장</h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">장소명</b> <div class="sch-rslt txt2 card-line">서서울호수공원(양천구)</div></li>
							<li class="li-user"><b class="user">이용대상</b> <div class="div-user txt2 card-line">제한없음</div></li>
							<li class="li-date"><b class="date1">접수기간</b> 2022.06.14 ~ 2022.07.30</li>
							<li class="li-date"><b class="date2">이용기간</b> 2022.07.01 ~ 2022.07.31</li>
						</ul>
			      </div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<img src="./images/함송 족구장.jpeg" class="card-img-top" alt="...">
			      	<div class="card-body">
			       		<h5 class="card-title">응애 풋살장</h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">장소명</b> <div class="sch-rslt txt2 card-line">서서울호수공원(양천구)</div></li>
							<li class="li-user"><b class="user">이용대상</b> <div class="div-user txt2 card-line">제한없음</div></li>
							<li class="li-date"><b class="date1">접수기간</b> 2022.06.14 ~ 2022.07.30</li>
							<li class="li-date"><b class="date2">이용기간</b> 2022.07.01 ~ 2022.07.31</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			       		<h5 class="card-title">응애 풋살장</h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">장소명</b> <div class="sch-rslt txt2 card-line">서서울호수공원(양천구)</div></li>
							<li class="li-user"><b class="user">이용대상</b> <div class="div-user txt2 card-line">제한없음</div></li>
							<li class="li-date"><b class="date1">접수기간</b> 2022.06.14 ~ 2022.07.30</li>
							<li class="li-date"><b class="date2">이용기간</b> 2022.07.01 ~ 2022.07.31</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<img src="./images/함송 족구장.jpeg" class="card-img-top" alt="...">
			      	<div class="card-body">
			       		<h5 class="card-title">응애 풋살장</h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">장소명</b> <div class="sch-rslt txt2 card-line">서서울호수공원(양천구)</div></li>
							<li class="li-user"><b class="user">이용대상</b> <div class="div-user txt2 card-line">제한없음</div></li>
							<li class="li-date"><b class="date1">접수기간</b> 2022.06.14 ~ 2022.07.30</li>
							<li class="li-date"><b class="date2">이용기간</b> 2022.07.01 ~ 2022.07.31</li>
						</ul>
			      	</div>
			    </div>
			  </div>
		</div>
	</div>
<!-- 구장 리스트 끝 -->
</section>

<%@ include file="/views/common/footer.jsp" %>


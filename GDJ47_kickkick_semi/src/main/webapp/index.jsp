<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List, com.board.model.vo.Board" %>
<%-- <%
	List<Board> list = (List<Board>)request.getAttribute("top-4-list");
	Board[] bArr = null;
	for(Board b : list){
		
	}
	
%> --%>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">


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
	<div id="content-banner-box">
		<div id="content-banner">
			<p id="p1">
				풋살이 하고싶다면!?<br>
				지금 바로 <span>킥킥</span>에서 찾아보세요!!
			</p>
			<img id="p1-img" src="https://img2.itravelgo.co.kr/data/service/8/php6hZjeD.jpg">
		</div>
		<div id="content-banner-2">
			<img id="p1-img" src="https://file.mk.co.kr/meet/yonhap/2020/04/22/image_readtop_2020_421056_0_204211.jpg">
			<p id="p2">
				직접 구성한 팀원들과 함께!!<br>
				원하는 상대팀을 선택하여 <br>진행하는 <span style="color:lime">풋살</span>게임!
			</p>
		</div>
	</div>

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
	

<!-- 공지사항 목록 -->
<section class="notice-container">
	<div class="main-sc9-notice">
			<div class="main-sc9-tit">
				<img id="megaphone" src="https://cdn-icons-png.flaticon.com/512/214/214347.png">
				알려드립니다!
			</div>
			<div class="main-notice-list">
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=181">
							<dl>
								<dt>
									[브랜드 론칭 이벤트] 신규 브랜드 '킥킥' 초성퀴즈 이벤트 당첨자 발표
								</dt>
								<dd>
									2022-05-30
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=68">
							<dl>
								<dt>
									브랜드 명 변경 안내(22/05/29)
								</dt>
								<dd>
									2022-05-24
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=10">
							<dl>
								<dt>
									이용후기 / 만족도조사 이벤트 변경 안내
								</dt>
								<dd>
									2022-05-11
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=11">
							<dl>
								<dt>
									이용상품권 사용안내
								</dt>
								<dd>
									2022-05-11
								</dd>
							</dl>
						</a>
					</div>
			</div>
		</div>
		<div class="main-sc9-review">
			<div class="main-sc9-tit">
				<img id="megaphone" src="https://cdn-icons-png.flaticon.com/512/1240/1240417.png">
				이용자 리뷰!
			</div>
			<div class="main-notice-list">
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=181">
							<dl>
								<dt>
									[브랜드 론칭 이벤트] 신규 브랜드 '킥킥' 초성퀴즈 이벤트 당첨자 발표
								</dt>
								<dd>
									2022-05-30
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=68">
							<dl>
								<dt>
									브랜드 명 변경 안내(22/05/29)
								</dt>
								<dd>
									2022-05-24
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=10">
							<dl>
								<dt>
									이용후기 / 만족도조사 이벤트 변경 안내
								</dt>
								<dd>
									2022-05-11
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="/customer/noticeView?bbsDetailSeq=11">
							<dl>
								<dt>
									이용상품권 사용안내
								</dt>
								<dd>
									2022-05-11
								</dd>
							</dl>
						</a>
					</div>
			</div>
		</div>
</section>
<!-- 공지사항 목록 끝 -->

</section>



<%@ include file="/views/common/footer.jsp" %>


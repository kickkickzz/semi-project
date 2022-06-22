<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List, com.board.model.vo.Board" %>

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
			    <%-- <img src="./images/<%=sArr[0].getBranch_img()%>" class="card-img-top" alt="..."> --%>
			    	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			        	<h5 class="card-title"><a id="resertag" href="<%=request.getContextPath() %>/reservation.do?stanum=<%=sArr[0].getStadium_num()%>"><%=sArr[0].getStadium_name() %></a></h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">주소</b> <div class="sch-rslt txt2 card-line"><%=sArr[0].getBranch_address() %></div></li>
							<li class="li-user"><b class="user">매치인원</b> <div class="div-user txt2 card-line"><%=sArr[0].getStadium_match_member() %></div></li>
							<li class="li-date"><b class="date1">전화번호</b> <%=sArr[0].getBranch_phone() %></li>
							<li class="li-date"><b class="date2">이용시간</b> <%=sArr[0].getStadium_reservation_start_time() %>:00~<%=sArr[0].getStadium_reservation_end_time() %>:00</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			  	<div class="card">
			      	<%-- <img src="./images/<%=sArr[1].getBranch_img()%>" class="card-img-top" alt="..."> --%>
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			        	<h5 class="card-title"><a id="resertag" href="<%=request.getContextPath() %>/reservation.do?stanum=<%=sArr[1].getStadium_num()%>"><%=sArr[1].getStadium_name() %></a></h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">주소</b> <div class="sch-rslt txt2 card-line"><%=sArr[1].getBranch_address() %></div></li>
							<li class="li-user"><b class="user">매치인원</b> <div class="div-user txt2 card-line"><%=sArr[1].getStadium_match_member() %></div></li>
							<li class="li-date"><b class="date1">전화번호</b> <%=sArr[1].getBranch_phone() %></li>
							<li class="li-date"><b class="date2">이용시간</b> <%=sArr[1].getStadium_reservation_start_time() %>:00~<%=sArr[1].getStadium_reservation_end_time() %>:00</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<%-- <img src="./images/<%=sArr[2].getBranch_img()%>" class="card-img-top" alt="..."> --%>
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			        	<h5 class="card-title"><a id="resertag" href="<%=request.getContextPath() %>/reservation.do?stanum=<%=sArr[2].getStadium_num()%>"><%=sArr[2].getStadium_name() %></a></h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">주소</b> <div class="sch-rslt txt2 card-line"><%=sArr[2].getBranch_address() %></div></li>
							<li class="li-user"><b class="user">매치인원</b> <div class="div-user txt2 card-line"><%=sArr[2].getStadium_match_member() %></div></li>
							<li class="li-date"><b class="date1">전화번호</b> <%=sArr[2].getBranch_phone() %></li>
							<li class="li-date"><b class="date2">이용시간</b> <%=sArr[2].getStadium_reservation_start_time() %>:00~<%=sArr[2].getStadium_reservation_end_time() %>:00</li>
						</ul>
			      </div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<%-- <img src="./images/<%=sArr[3].getBranch_img()%>" class="card-img-top" alt="..."> --%>
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			       		<h5 class="card-title"><a id="resertag" href="<%=request.getContextPath() %>/reservation.do?stanum=<%=sArr[3].getStadium_num()%>"><%=sArr[3].getStadium_name() %></a></h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">주소</b> <div class="sch-rslt txt2 card-line"><%=sArr[3].getBranch_address() %></div></li>
							<li class="li-user"><b class="user">매치인원</b> <div class="div-user txt2 card-line"><%=sArr[3].getStadium_match_member() %></div></li>
							<li class="li-date"><b class="date1">전화번호</b> <%=sArr[3].getBranch_phone() %></li>
							<li class="li-date"><b class="date2">이용시간</b> <%=sArr[3].getStadium_reservation_start_time() %>:00~<%=sArr[3].getStadium_reservation_end_time() %>:00</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<%-- <img src="./images/<%=sArr[4].getBranch_img()%>" class="card-img-top" alt="..."> --%>
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			       		<h5 class="card-title"><a id="resertag" href="<%=request.getContextPath() %>/reservation.do?stanum=<%=sArr[4].getStadium_num()%>"><%=sArr[4].getStadium_name() %></a></h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">주소</b> <div class="sch-rslt txt2 card-line"><%=sArr[4].getBranch_address() %></div></li>
							<li class="li-user"><b class="user">매치인원</b> <div class="div-user txt2 card-line"><%=sArr[4].getStadium_match_member() %></div></li>
							<li class="li-date"><b class="date1">전화번호</b> <%=sArr[4].getBranch_phone() %></li>
							<li class="li-date"><b class="date2">이용시간</b> <%=sArr[4].getStadium_reservation_start_time() %>:00~<%=sArr[4].getStadium_reservation_end_time() %>:00</li>
						</ul>
			      	</div>
			    </div>
			  </div>
			  <div class="col">
			    <div class="card">
			      	<%-- <img src="./images/<%=sArr[5].getBranch_img()%>" class="card-img-top" alt="..."> --%>
			      	<img src="./images/달월.jpg" class="card-img-top" alt="...">
			      	<div class="card-body">
			       		<h5 class="card-title"><a id="resertag" href="<%=request.getContextPath() %>/reservation.do?stanum=<%=sArr[5].getStadium_num()%>"><%=sArr[5].getStadium_name() %></a></h5>
			        	<ul class="card-body-list">
							<li class="place-name"><b class="place">주소</b> <div class="sch-rslt txt2 card-line"><%=sArr[5].getBranch_address() %></div></li>
							<li class="li-user"><b class="user">매치인원</b> <div class="div-user txt2 card-line"><%=sArr[5].getStadium_match_member() %></div></li>
							<li class="li-date"><b class="date1">전화번호</b> <%=sArr[5].getBranch_phone() %></li>
							<li class="li-date"><b class="date2">이용시간</b> <%=sArr[5].getStadium_reservation_start_time() %>:00~<%=sArr[5].getStadium_reservation_end_time() %>:00</li>
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
					<dl id="notice-list-name">
						<dt>제목</dt>
						<dt>등록날짜</dt>
					</dl>
					<div class="notice-list-item">
						<a href="<%=request.getContextPath()%>/detailBoard.do?bId=<%=bArr[0].getBoardNum()%>">
							<dl>
								<dt>
									<%=bArr[0].getBoardTitle() %>
								</dt>
								<dd>
									<%=bArr[0].getBoardDate() %>
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="<%=request.getContextPath()%>/detailBoard.do?bId=<%=bArr[1].getBoardNum()%>">
							<dl>
								<dt>
									<%=bArr[1].getBoardTitle() %>
								</dt>
								<dd>
									<%=bArr[1].getBoardDate() %>
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="<%=request.getContextPath()%>/detailBoard.do?bId=<%=bArr[2].getBoardNum()%>">
							<dl>
								<dt>
									<%=bArr[2].getBoardTitle() %>
								</dt>
								<dd>
									<%=bArr[2].getBoardDate() %>
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="<%=request.getContextPath()%>/detailBoard.do?bId=<%=bArr[3].getBoardNum()%>">
							<dl>
								<dt>
									<%=bArr[3].getBoardTitle() %>
								</dt>
								<dd>
									<%=bArr[3].getBoardDate() %>
								</dd>
							</dl>
						</a>
					</div>
			</div>
		</div>
		<div class="main-sc9-review">
			<div class="main-sc9-tit">
				<img id="megaphone" src="https://cdn-icons-png.flaticon.com/512/921/921347.png">
				팀 정보!
			</div>
			<div class="main-notice-list">
					<div class="notice-list-item">
							<dl id="team-list-name">
								<dd>팀마크</dd>
								<dd>팀이름</dd>
								<dd>팀연령</dd>
								<dd>활동지역</dd>
								<dd>팀성별</dd>
							</dl>
						<a href="<%=request.getContextPath()%>/team/teamMemberInfo.do?team_code=<%=tArr[0].getTeam_code()%>">
							<dl>
								<dd>
									<%if(tArr[0].getTeam_mark_img()!=null){ %>
										<img src="./images/<%=tArr[0].getTeam_mark_img() %>">
									<%}else{ %>
										<img src="./images/세미로고.png">
									<%} %>
								</dd>
								<dd>
									<%=tArr[0].getTeam_name() %>
								</dd>
								<dd>
									<%=tArr[0].getTeam_age()%>
								</dd>
								<dd>
									<%=tArr[0].getTeam_region() %>
								</dd>
								<dd>
									<%=tArr[0].getTeam_gender() %>
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="<%=request.getContextPath()%>/team/teamMemberInfo.do?team_code=<%=tArr[1].getTeam_code()%>">
							<dl>
								<dd>
									<%if(tArr[1].getTeam_mark_img()!=null){ %>
										<img src="./images/<%=tArr[1].getTeam_mark_img() %>">
									<%}else{ %>
										<img src="./images/세미로고.png">
									<%} %>
								</dd>
								<dd>
									<%=tArr[1].getTeam_name() %>
								</dd>
								<dd>
									<%=tArr[1].getTeam_age()%>
								</dd>
								<dd>
									<%=tArr[1].getTeam_region() %>
								</dd>
								<dd>
									<%=tArr[1].getTeam_gender() %>
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
					<a href="<%=request.getContextPath()%>/team/teamMemberInfo.do?team_code=<%=tArr[2].getTeam_code()%>">
							<dl>
								<dd>
									<%if(tArr[2].getTeam_mark_img()!=null){ %>
										<img src="./images/<%=tArr[2].getTeam_mark_img() %>">
									<%}else{ %>
										<img src="./images/세미로고.png">
									<%} %>
								</dd>
								<dd>
									<%=tArr[2].getTeam_name() %>
								</dd>
								<dd>
									<%=tArr[2].getTeam_age()%>
								</dd>
								<dd>
									<%=tArr[2].getTeam_region() %>
								</dd>
								<dd>
									<%=tArr[2].getTeam_gender() %>
								</dd>
							</dl>
						</a>
					</div>
					<div class="notice-list-item">
						<a href="<%=request.getContextPath()%>/team/teamMemberInfo.do?team_code=<%=tArr[3].getTeam_code()%>">
							<dl>
								<dd>
									<%if(tArr[3].getTeam_mark_img()!=null){ %>
										<img src="./images/<%=tArr[3].getTeam_mark_img() %>">
									<%}else{ %>
										<img src="./images/세미로고.png">
									<%} %>
								</dd>
								<dd>
									<%=tArr[3].getTeam_name() %>
								</dd>
								<dd>
									<%=tArr[3].getTeam_age()%>
								</dd>
								<dd>
									<%=tArr[3].getTeam_region() %>
								</dd>
								<dd>
									<%=tArr[3].getTeam_gender() %>
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


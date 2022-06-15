<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reservation.css">




<%@ include file="/views/common/header.jsp" %>
<section class="section" id="branch-info-section">
	<div id="main-container-content">
			<!-- 구장정보박스 -->
			<div class="info-box-1" id="branch-info-box">

				<!-- 지점/구장 이름 박스-->
				<div class="info-box-2" id="branch-name-info-box">
					<!-- 구장의 지점 번호 : STADIUM_BRANCH_NUM-->
					<input type="hidden" id="STADIUM_BRANCH_NUM"
						name="STADIUM_BRANCH_NUM" value="">

					<!-- 구장 번호: STADIUM_NUM -->
					<input type="hidden" id="STADIUM_NUM" name="STADIUM_NUM"
						value="">

					<!-- 구장 이름박스 -->
					<div id="stadium-name-box">
						<!-- 구장 이름 : STADIUM_NAME-->
						<h1 class="section-title" id="STADIUM_NAME"></h1>

						<!--  branch_branchInfo -->
						<%
							
						%>
						<h3 id="BRANCH_BRANCHINFO"></h3>
						<%
							
						%>
					</div>
				</div>


				<!-- 지점이미지 박스-->
				<div class="info-box-2" id="branch-img-box">

					<!-- 지점 이미지: BRANCH_IMG 
                        ./img/fb.png는 샘플 이미지입니다.
                    -->
					<img
						src=""
						alt="BRANCH_IMG" id="BRANCH_IMG">
				</div>



				<!--지점 상세 정보 박스-->
				<div class="info-box-2" id="branch-detail-info-box">

					<!-- 지점옵션 -->
					<div id="branch-options">
						<h2 class="section-sub-title">지점 옵션</h2>

						<div class="branch-options-group3">
							<%
								
							%>
							<div class="branch_option" id="BRANCH_OPTION_SHOWER">
								<img
									src=""
									alt="shower">
								<p>샤워</p>
							</div>
							<%
								
							%>


							<%
								
							%>
							<div class="branch_option" id="BRANCH_OPTION_PARK">
								<img
									src=""
									alt="parking">
								<p>주차장 유무</p>
							</div>
							<%
								
							%>

							<%
								
							%>
							<div class="branch_option" id="BRANCH_OPTION_UNIFORM">
								<img
									src=""
									alt="uniform">
								<p>유니폼 대여 가능 여부</p>
							</div>
							<%
								
							%>
						</div>


						<div class="branch-options-group3">
							<%
								
							%>
							<div class="branch_option" id="BRANCH_OPTION_SHOES">
								<img
									src="<%=request.getContextPath()%>/resources/common/images/img_option/shoes.png"
									alt="shoes">
								<p>풋살화 대여 가능 여부</p>
							</div>
							<%
								
							%>

							<%
								
							%>
							<div class="branch_option" id="BRANCH_OPTION_BALL">
								<img
									src=""
									alt="ball">
								<p>볼 대여 가능 여부</p>
							</div>
							<%
								
							%>


							<%
								
							%>
							<div class="branch_option" id="BRANCH_OPTION_INOUT">
								<img
									src=""
									alt="실내실외">
								<p>
									실내/실외 <br>(여기는 데이터 불러와야될듯)
								</p>
							</div>
							<%
							%>
						</div>
						<script>
                        $(function(){
                        	let branch_info=$('.branch-options-group3').text();
                        	
                       		if(branch_info.trim()==''){
                       			console.log(branch_info.trim()=='');
                       			$('#branch-options').css('display','none');
                       		}
                       		
                        });
                        	
                        </script>
					</div>
				</div>

				<!-- 구장 상세정보 박스 -->
				<div class="info-box-2" id="stadium-detail-info-box">
					<h2 class="section-sub-title">지점 상세 정보</h2>

					<div id="stadium-detail-info">
						<!-- 지점이름 -->
						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">구장 이름</label>
							</div>
							<span></span>
						</div>


						<!-- 매치유형 -->
						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">인원 수</label>
							</div>
							<span> 
							</span>
						</div>

						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">별점</label>
							</div>
							<span> 
									
									
							</span>
						</div>


						<!-- 지점주소 -->
						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">지점 주소</label>
							</div>
							<span></span>
						</div>

						<!-- 지점 전화번호 -->
						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">지점 전화번호</label>
							</div>
							<span></span>
						</div>

						<!-- sns -->
						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">SNS</label>
							</div>
							<span><a id="sns-website"
								href=""></a></span>
						</div>


						<!-- 영업시간 박스 -->
						<div class="stadium-detail-box">
							<div class=" stadium-detail-info-label-box ">
								<label class=" stadium-detail-info-label">영업시간</label>
							</div>

							<!--영업시작/ 종료 시간 박스  -->
							<div class="open-close-times">
								<span id="STADIUM_RESERVATION_START_TIME"></span>
								<span>~</span> <span id="STADIUM_RESERVATION_END_TIME"></span>
							</div>
						</div>

						
						<div class="stadium-detail-box">
							<div class="stadium-detail-info-label-box">
								<label class="stadium-detail-info-label">공간 소개</label>
							</div>

							<div class="stadium-describe-box">
								
							</div>
						</div>
						<%
							
						%>

					</div>
				</div>
			</div>
		</section>



		<!--이용 안내사항-->
		<section class="section" id="stadium-notice-info-section">
			<div class="info-box-1">
				<h1 class="section-title">이용 안내사항</h1>


				<div class="info-box-2  stadium-notices">
					
					<h2 class="section-sub-title">예약 주의사항</h2>
					<ul class="stadium-notices-ul" style="color: red;">
						<li></li>
					</ul>
					

					<h2 class="section-sub-title">이용 규칙</h2>
					<ul class="stadium-notices-ul" style="list-style: square">
						<li>풋살장 예약 시간 준수</li>
						<li>풋살장 내 취사, 흡연 및 음주행위, 지나친 소음행위 금지(적발 시 이용불가)</li>
						<li>시설 사용 후 정리정돈 ( 쓰레기 반드시 처리 )</li>
						<li>고의 및 과실로 인한 시설물 훼손 및 파손시 사용자가 배상하며 경기중 부상은 본인이 책임집니다.</li>
						<li>잔디보호와 부상방지를 위하여 스터드가 있는 축구화(SG, FG, HG, AG)는 착용이 금지되며
							풋살화(TF)만 착용 가능 합니다.</li>

						<li>사회적 거리두기 2단계 동안에는 다음내용이 적용됩니다.
							<ul style="list-style: circle;" class="stadium-notices-ul2">
								<li>운동시에는 마스크를 꼭 착용해주셔야합니다. 호흡이 어려운 경우 운동템포와 휴식시간을 조정해주세요.</li>
								<li>실내구장의 경우에는 휴식시에도 마스크를 착용해주셔야합니다.</li>
								<li>야외구장의 경우에는 휴식시 2M 이상 거리를 유지해주세요.</li>
							</ul>
						</li>

						<li style="font-weight: bold;">위 내용이 지켜지지 않을 경우 퇴장조치 될 수 있으니
							예약시 꼭 참고부탁드립니다.</li>

						<li style="font-weight: bold;"><span style="color: red;">
								규정 외 요청은 적용이 불가합니다. 예약 전 반드시 확인해 주시기 바랍니다. </span></li>
					</ul>
				</div>
			</div>
		</section>

		<!-- 변경 및 환불안내 사항 -->
		<section class="section" id="stadium-changerf-info-section">
			<div class="info-box-1">
				<h1 class="section-title">변경 및 환불 안내 사항</h1>

				<!-- 변경 -->
				<div class="info-box-2 stadium-notices">
					<h2 class="section-sub-title">변경 규정</h2>

					<ul align="left" class="stadium-notices-ul"
						style="list-style: square;">
						<li>시작시간이 지난 후 요청 했을 경우에는 환불 및 변경 모두 불가능합니다.
						<li>구장 이용 중 비가 오기 시작 할 경우 환불 및 변경가능 여부는 구장 관리자가 결정합니다.
						<li>아래의 조건에 해당될 경우 내용을 아이엠그라운드 카카오톡 채널로 예약 시작 시간 전까지 남겨주시면
							고객센터 운영 시간에 확인 후 처리해 드리겠습니다.
						<li>변경은 하단 환불 규정 기준 100% 환불인 경우에만 가능하며, 변경 가능한 횟수는 1회 입니다.</li>
						<li>1회 변경된 예약은 환불 및 재변경이 불가능합니다.</li>
						<li>날씨에 의한 변경은 야외구장에만 적용됩니다.</li>
					</ul>

				</div>

				<!-- 환불 -->
				<div class="info-box-2 stadium-notices">
					<h2 class="section-sub-title">환불 규정</h2>

					<ul align="left" class="stadium-notices-ul"
						style="list-style: square;">
						<li>환불은 예약날짜를 기준으로만 적용합니다. (예약 시간으로는 적용되지 않습니다)</li>
						<li>예를들어, 5월 7일 예약일 경우 다음과 같습니다
							<ul class="stadium-notices-ul">
								<li>이용 5일 전까지 : <b style="color: red;">100% 환불</b>
								</li>
								<li>4일 전 ~ 3일 전: <b style="color: red;">50% 환불</b>
								</li>
								<li>2일 전 ~ 대관 당일 : <b style="color: red;">환불 불가</b></li>
							</ul>
						<li>다음과 같은 경우에는 상단 규정대로 처리됩니다.<br>
							<ul style="list-style: circle;" class="stadium-notices-ul2">
								<li>고객의 사정으로 예약된 날짜에 구장 이용을 못하는 경우</li>
								<li>구장, 날짜, 시간 등을 실수로 잘못 선택했을 경우</li>
								<li>단순 변심으로 인해 환불이나 변경을 요청하는 경우</li>
							</ul>
						<li>날씨에 의한 환불은 야외구장에만 적용됩니다</li>
						<li>다음과 같을 때는 환불이 가능합니다.<br>
							<ul style="list-style: circle;" class="stadium-notices-ul2">
								<li>예약 당일, 기상청에서 천재지변에 해당하는 특보를 예약한 지역에 발표한 경우에 적용 (특보가 해제된
									후에는 적용이 불가능합니다.)</li>
								<li>구장, 날짜, 시간 등을 실수로 잘못 선택했을 경우</li>
								<li>단순 변심으로 인해 환불이나 변경을 요청하는 경우</li>
							</ul>
					</ul>
				</div>
			</div>
		</section>
		<section class="section" id="stadium-reservation-section">
			<h1 class="section-title">예약</h1>

			<div class="info-box-2 stadium-notices">
				<!-- 예약날짜, 예약 시간,  예약금액 -->

				<!-- 예약날짜 -->
				<div class="info-box2">
					<h2 class="section-sub-title">예약 날짜 선택</h2>
					<div class="container" id="reservation_date_select_box">
						<div class="my-calendar clearfix">
							<div class="clicked-date">
								<div class="cal-day"></div>
								<div class="cal-date"></div>
							</div>
							<div class="calendar-box">
								<div class="ctr-box clearfix">
									<button type="button" title="prev" class="btn-cal prev">
									</button>
									<span class="cal-month"></span> <span class="cal-year"></span>
									<button type="button" title="next" class="btn-cal next">
									</button>
								</div>
								<table class="cal-table">
									<thead>
										<tr>
											<th>일</th>
											<th>월</th>
											<th>화</th>
											<th>수</th>
											<th>목</th>
											<th>금</th>
											<th>토</th>
										</tr>
									</thead>
									<tbody class="cal-body"></tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
		
		</div>

</body>
</html>
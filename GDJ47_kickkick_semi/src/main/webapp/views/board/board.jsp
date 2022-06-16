<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.member.model.vo.*, com.board.model.vo.* , java.util.List"%>
<%
	//로그인 하지 않아도 다 열람 가능
	//작성 권한은 관리자만
	//
	
%>
<%-- header을 호출 --%>
	<%@include file="/views/common/header.jsp"%>
<!--파비콘아이콘-->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/common/favicon/favicon.ico"
	type="image/x-icon">

<!-- api : bootstrap -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<body>
	<div class="main-container" id="main-container">
		<div class="board-main-container">
			<div class="board_total_container">
				<div class="board_title_box">
					<h1 class="board-title">공지사항</h1>
					<hr>
				</div>
				<!-- 공지작성은 관리자만-->
				<div class="board_button_box">
					<div></div>
					<button type="button" class="btn btn-primary" id="insertBtn" onclick="location.href='writeBoardForm.do'">등록하기</button>
				</div>
				<!-- 테이블 형식으로 구성되어있다. -->
				<div class="board_table_container">
					<table class="table table-hover" id="listArea">
						<thead class="thead-dark">
							<!-- table attributes -->
							<tr>
								<th scope="col">No</th>
								<th scope="col">작성날짜</th>
								<th scope="col" colspan=3>공지내용</th>
								<th scope="col">작성자</th>
							</tr>
						</thead>
						<tbody>
							<!-- jsp로 데이터 삽입-->
							<tr>
								<td colspan="6">공지사항이 없습니다!</td>
							</tr>
							<tr>
								<th scope="row">
									<input type="hidden" value=""/>
								</th>
								<!--번호-->
								<td></td>
								<!--작성날짜 -->
								<td colspan=3></td>
								<!-- 제목 -->
								<td></td>
								<!-- 작성자 -->
							</tr>
						</tbody>
					</table>
				</div>
				<!-- pagenation을 담는 컨테이너-->
				<div class="board_pagenation">
					<nav>
						<ul class="pagination">
							<%-- 맨처음버튼: 현재페이지를 1로 한다. --%>
							<li class="page-item"><button id="initial_previous"
								class="page-link" 
								onclick="">
							</button></li>
							<%-- 이전버튼 --%>
							<li class="page-item">
								<button id="previous" class="page-link" onclick=""></button>
							</li>
							<%--현재페이지에서 10개를 불러온다. --%>
							<li class="page-item active">
								<button class="page-link" disabled="disabled"></button>
							</li>
							<li class="page-item">
								<button class="page-link" onclick=""></button>
							</li>
							<%-- 다음버튼 --%>
							<li class="page-item">
								<button id="next" class="page-link"	onclick=""></button>
							</li>
							<%-- 가장마지막 버튼 --%>
							<li class="page-item">
								<button id="last_next" class="page-link" onclick=""></button>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<%-- footer을 호출 --%>
	<%@include file="/views/common/footer.jsp"%>
<script>
	
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.member.model.vo.*, com.board.model.vo.* , java.util.List"%>
<%
	//로그인 하지 않아도 다 열람 가능
	//작성 권한은 관리자만
	List<Board> list=(List<Board>)request.getAttribute("list");
	
	
%>
<link href="<%=request.getContextPath()%>/css/boardList.css" type="text/css" rel="stylesheet">
<%@include file="/views/common/header.jsp"%>
<body>
	<div class="main-container" id="main-container">
		<div class="board-main-container">
			<div class="board_total_container">
				<div class="board_title_box">
					<h1 class="board-title">공지사항</h1>
					<hr>
				</div>
				<!-- 공지작성은 관리자만 회원 유형 적용해서 권한부여 가능-->
				<%//if(loginMember!=null&&loginMember.getEmail().equals("admin")){ %>
				<div class="board_button_box">
					<div></div>
					<button type="button" class="btn btn-primary" id="insertBtn" onclick="location.assign('<%=request.getContextPath() %>/writeBoardForm.do')">등록하기</button>
				</div>
				<%//} %>
				<!-- 테이블 형식으로 구성 -->
				<div class="board_table_container">
					<table class="table table-hover" id="listArea">
						<thead class="thead-dark">
							<!-- table 요소-->
							<tr>
								<th scope="col">No</th>
								<th scope="col">작성날짜</th>
								<th scope="col" colspan=3>공지내용</th>
								<th scope="col">작성자</th>
							</tr>
						</thead>
						<tbody>
						<!-- jsp로 데이터 삽입-->
						<%if(list.isEmpty()){ %>
							<tr>
								<td colspan="6">공지사항이 없습니다!</td>
							</tr>
						<%}else{
							for(Board b:list){%>	
							<tr>
								<th scope="row">
									<input type="hidden" value="<%=b.getBoardNum()%>"/>
								</th>
								<!--번호-->
								<td><%b.getBoardNum(); %></td>
								<!--작성날짜 -->
								<td><%b.getBoardDate(); %></td>
								<!-- 공지내용 요약-제목 -->
								<td colspan=3><%b.getBoardTitle(); %></td>
								<!-- 작성자 -->
								<td><%b.getBoardWriter(); %></td>
							</tr>
						<% }
						}%>	
						</tbody>
					</table>
				</div>
				<!-- 페이징처리-->
				<div class="board_pagenation">
					<nav>
						<ul class="pagination">

							<%-- 가장처음버튼: 현재페이지를 1로 한다. --%>
							<li class="page-item"><button id="initial_previous"
								class="page-link" 
								onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo?currentPage=1'"> &lt;&lt;
							</button></li>

							<%-- 이전버튼 --%>
							<li class="page-item">
								<button id="previous" class="page-link"
									onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo?currentPage'"> &lt;</button>
									</li>
							
							<%--현재페이지에서 10개를 불러온다. --%>
								<%//for(int p=startPage; p<=endPage; p++){ 
									//if(p==currentPage)
									//p가 현재페이지(currentPage)와 같다면
									//현재 페이지는 선택 못하도록%>
									<li class="page-item active">
										<button class="page-link" disabled="disabled">!</button>
									</li>
								<%//}else{ %>
									<li class="page-item">
										<button class="page-link" onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo?currentPage='">?</button>
									</li>
								<%//	} %>
								<%//} %>
	
							<%-- 다음버튼 --%>
							<li class="page-item">
								<button id="next" class="page-link"	onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo?currentPage='">&gt; </button>
							</li>
							

							<%-- 가장마지막 버튼 --%>
							<li class="page-item">
								<button id="last_next" class="page-link"	onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo?currentPage='">&gt;&gt; </button>
							</li>
							
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
<%@include file="/views/common/footer.jsp"%>
<script>
	
</script>


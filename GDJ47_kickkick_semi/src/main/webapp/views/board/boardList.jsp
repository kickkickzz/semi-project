<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
 import="com.member.model.vo.*, com.board.model.vo.* , java.util.ArrayList"%>
<%
	//로그인 하지 않아도 다 열람 가능
	//작성 권한은 관리자만
	/* Member loginMember = (Member) session.getAttribute("loginMember");
	System.out.println("현재 로그인 회원 정보 => " + loginMember); */
	
	
	List<Board> boards=(List<Board>)request.getAttribute("boards");
	String pageBar=(String)request.getAttribute("pageBar");
	
	
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
				<!-- 공지작성은 관리자만 회원 유형 적용해서 권한부여 가능 loginMember.getType().equals("M")-->
				<%if(loginMember!=null&& loginMember.getType().equals("M")){ //회원유형으로 설정 %>
				<div class="board_button_box">
					<div></div>
					<button type="button" class="btn btn-primary" id="insertBtn" onclick="location.assign('<%=request.getContextPath() %>/writeBoardForm.do')">등록하기</button>
				</div>
				<%} %>
				
				<!-- 테이블 형식으로 구성 -->
				<div class="board_table_container">
					<table class="table table-hover" id="listArea">
						<thead class="thead-dark">
							<tr>
								<th scope="col">No</th>
								<th scope="col">작성날짜</th>
								<th scope="col" colspan=3>공지내용</th>
								<th scope="col">작성자</th>
							</tr>
						</thead>
						<tbody>
						<!-- 데이터 삽입-->
						<%if(!boards.isEmpty()){ 
							for(Board b : boards){%>
							<tr>
								<th scope="row">
									<input type="hidden" value="<%=b.getBoardNum()%>"/>
								</th>
								<!--번호-->
								<td><%=b.getBoardNum() %></td>
								<!--작성날짜 -->
								<td><%=b.getBoardDate() %></td>
								<!-- 공지내용 요약-제목 -->
								<td colspan=3><%=b.getBoardTitle() %></td>
								<!-- 작성자 -->
								<td><%=b.getBoardWriter() %></td>
							</tr>
						<%	}
						 }else{ %>
							<tr>
								<td colspan='6'>조회된 결과가 없습니다.</td>
							</tr>
						<%} %>
						</tbody>
					</table>
					<div id="pageBar">
						<%=pageBar %>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>
<%@include file="/views/common/footer.jsp"%>
<script>
	
</script>


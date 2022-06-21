<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.member.model.vo.*, com.board.model.vo.*, java.util.List"%>
<%

	Board b=(Board)request.getAttribute("board");
	
%>
<link
	href="https://fonts.googleapis.com/css2?family=Alata&family=Do+Hyeon&display=swap"
	rel="stylesheet">
<!-- 정적파일 css, js-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/boardWriter.css">
<!-- header을 호출 --!>
<%@include file="/views/common/header.jsp"%>
<body>
	<!-- 메인페이지 컨테이너-->
	<div class="main-content-container">
		<div class="board_writer-total-container">
			<div class="board-writer-form-container">

				<div class="board_title_box">
					
					<h1 class="board_title">공지사항 수정하기</h1>
					<hr>
				</div>
				<form action="<%=request.getContextPath()%>/updateBoard.do"  
					id="updateBoardContent" method="post" encType="multipart/form-data">
					<input type="hidden" name="bId" value="<%=b.getBoardNum()%>">
					<!-- 타이틀 영역박스-->
					<div class="title-container">
						<input id="board-write-title" type="text" name="title"
							placeholder="<%=b.getBoardTitle() %>">
						<hr class="my-4">
					</div>
					<!-- 이미지 등록박스-->
					<div class="img-container">
						<div class="browse_img_box">
							<%//보류 %>
						</div>
						<div></div>
					</div>
					<!--내용 영역-->
					<div class="content-container">
						<div class="content_text">
							<textarea name="content" id="board-content" style="resize: none;"
								cols=30 placeholder="내용을 입력해주세요..."
								onkeyup=""></textarea>
							<div class="content-byte-box">
								<p class="content-data-count">
									<em id="Byte">0</em>/1000 bytes
								</p>
							</div>
						</div>
						
					</div>
					</form>

					<!--버튼 등록-->
					<div class="button-container">
						<div class="cancel_write_board-box">
							<button class="btn btn-secondary" onclick="cancelUpdate();">취소</button>
						</div>
						<div></div>
						<div class="insert_write_board-box">
							<!-- button은 input:submit 과 동일함-->
							<button type="submit" class="btn btn-primary"  form="updateBoardContent">수정</button>
						</div>
					</div>
			</div>
		</div>
	</div>
<!--footer삽입 -->
<%@include file="/views/common/footer.jsp"%>
<script>
	function cancelUpdate(){
		let result=confirm('수정을 취소하시겠습니까?');
		if(result==true){
			alert('수정을 취소합니다.');
			//수정 취소
			location.href='<%=request.getContextPath()%>/showBoardList.do';
		}
	}
</script>

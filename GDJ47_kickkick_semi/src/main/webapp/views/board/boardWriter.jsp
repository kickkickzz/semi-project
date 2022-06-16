<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.member.model.vo.*, com.board.model.vo.Board,java.util.List"%>
<link
	href="https://fonts.googleapis.com/css2?family=Alata&family=Do+Hyeon&display=swap"
	rel="stylesheet">

<!-- 정적파일 css, js-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/boardWriter.css">
</head>
<%@include file="/views/common/header.jsp"%>
<body>
	<!-- 메인페이지 컨테이너-->
	<div class="main-content-container">
		<div class="board_writer-total-container">
			<div class="board-writer-form-container">
				<div class="board_title_box">
					<h1 class="board_title">공지사항 등록하기</h1>
					<hr>
				</div>
				<form action="<%=request.getContextPath()%>/writeBoard.do" id="write_board_form" method="post" encType="multipart/form-data">
					<!-- 타이틀 영역박스-->
					<div class="title-container">
						<input id="board-write-title" type="text" name="title"
							placeholder="제목을 입력해주세요">
						<hr class="my-4">
					</div>
					<!-- 이미지 등록박스-->
					<div class="img-container">
						<div class="browse_img_box">
							<label> <input type="file" class="file-input"
								accept="image/*" name="img" id="board_img_file_import"
								onchange="loadImg(this);" />
							</label> <span class="filename">파일을 선택해주세요</span>
							<div class="photo-box">
								<img id="photoArea" alt="photo" width="500px" height="500px">
							</div>
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
									
								</p>
							</div>
						</div>
						<script>
							
						</script>
					</div>
					</form>

					<!--버튼 등록-->
					<div class="button-container">
						<div class="cancel_write_board-box">
							<button class="btn btn-secondary" 
									onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo'">취소</button>
						</div>
						<div></div>
						<div class="insert_write_board-box">
							<!-- button은 input:submit 과 동일함-->
							<button type="submit"  form="write_board_form" class="btn btn-primary">작성</button>
						</div>
					</div>
				
			</div>
			
		</div>
		
	</div>
	<!--footer삽입 -->
	<%@include file="/views/common/footer.jsp"%>

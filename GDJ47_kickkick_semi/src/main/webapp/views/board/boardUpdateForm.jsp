<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.member.model.vo.*, com.board.model.vo.*, java.util.List"%>
<%

	Board b=(Board)request.getAttribute("board");
	
%>
<!-- 정적파일 css, js -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/boardWriter.css">
<!-- header을 호출 -->
<%@include file="/views/common/header.jsp" %>

<body>

	<!-- 메인페이지 컨테이너-->
	<div class="main-content-container"><br>
		<div class="board_writer-total-container"><br>
			<div class="board-writer-form-container"><br>

				<div class="board_title_box">
					
					<h1 class="board_title">공지사항 수정</h1>
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
					<%-- <div class="img-container">
						<div class="browse_img_box">
							<%if(img!=null){ 
								//이미지가 존재하면.. => fId를 받아라.
							%>
								<input type="hidden" name="fId" value="<%=img.getFileId()%>">
							<%}else{
								//이미지가 존재하지 않는다면 => fId는 0이다.
							%>
								<input type="hidden" name="fId" value=<%=0%>>
							<%} %>
							<label> 
								<input type="file" class="file-input"
										accept="image/*" name="img" id="board_img_file_import"
										onchange="loadImg(this);" />
							</label> 
							
							<span class="filename">파일을 선택해주세요</span>

							<div class="photo-box">
								<img id="photoArea" alt="photo" width="500px" height="500px">
							</div>
						</div>
						<div></div>
					</div> --%>
					<!--내용 영역-->
					<div class="content-container">
						<div class="content_text">
							<textarea name="content" id="board-content" style="resize: none;"
								cols=30 placeholder="내용을 입력해주세요..."
								onkeyup="countContentLength(this);"></textarea>
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
							<button type="submit" class="btn btn-primary" form="updateBoardContent">수정</button>
						</div>
					</div>
			</div>
		</div>
	</div>
<!--footer삽입 -->
<%@include file="/views/common/footer.jsp"%>
<script>
	//파일을 올리면, 올린 파일이름을 띄운다.
	$(document).on(	'change','.file-input',	function() {
		var $filedirs = $(this).val().split('\\');
		var $filename = $filedirs[$filedirs.length - 1];
		console.log($filename);
		if ($filename == '')
			$filename = '파일을 선택해주세요';
		$('.filename').text($filename);
	});
	
	function loadImg(photo) {	
		$photo = $('#photoArea');
		console.log(photo.files);
	
		if (photo.files && photo.files[0]) {
	
			var reader = new FileReader();
			reader.onload = function(e) {
				$photo.css('display', 'block');
				$photo.attr('src', e.target.result);
			}
		}
		reader.readAsDataURL(photo.files[0]);
	}
	
	function cancelUpdate(){
		let result=confirm('수정을 취소하시겠습니까?');
		if(result==true){
			alert('수정을 취소합니다.');
			//수정 취소
			location.href='<%=request.getContextPath()%>/showBoardList.do';
		}
	}
	
	let limitByte = 1000; //byte최대 크기
	function countContentLength(content) {
		//byte check : textarea에 입력한 길이
		let check_length = document
				.getElementById('Byte');
		let message = content.value;
		// console.log(messageLength);

		let totalByte = 0;
		for (var i = 0; i < message.length; i++) {
			var currentByte = message.charCodeAt(i);
			if (currentByte > 128)
				totalByte += 2;
			else
				totalByte++;
		}

		check_length.innerText = totalByte;

		//최대 바이트크기를 넘으면 못쓰게한다.
		if (totalByte > limitByte) {
			alert('1000Byte 이내로 작성해주세요!');
		}
	}
</script>

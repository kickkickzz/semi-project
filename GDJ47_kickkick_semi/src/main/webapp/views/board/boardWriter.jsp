<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.member.model.vo.*, com.board.model.vo.Board,java.util.ArrayList"%>
<!-- 정적파일 css, js-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/boardWriter.css">

<%@include file="/views/common/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<body>
<!-- naver smartediter 적용 방안 찾는 중 -->
	<!-- 메인페이지 컨테이너-->
	<div class="main-content-container"><br>
		<div class="board_writer-total-container">
			<div class="board-writer-form-container">
				<div class="board_title_box">
					<h1 class="board_title">공지사항 등록하기</h1>
					<hr>
				</div>
				<form action="<%=request.getContextPath()%>/writeBoard.do" id="write_board_form" method="post" encType="multipart/form-data">
					<!-- 타이틀 영역박스-->
					<div class="title-container">
						<input id="board-write-title" type="text" name="title" placeholder="제목을 입력해주세요">
						<hr class="my-4">
					</div>
					<!-- 이미지 등록박스-->
					<div class="img-container">
						<div class="browse_img_box">
							<label> <input type="file" class="file-input"
								accept="image/*" name="img" id="board_img_file_import" onchange="loadImg(this);" />
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
								cols=30 placeholder="내용을 입력해주세요..." onkeyup="countContentLength(this);"></textarea>
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
							<button class="btn btn-secondary" 
									onclick="location.href='<%=request.getContextPath()%>/showBoardList.do'">취소</button>
						</div>
						<div></div>
						<div class="insert_write_board-box">
							<!-- button은 input:submit 과 동일함-->
							<button type="submit" form="write_board_form" class="btn btn-primary">작성</button>
						</div>
					</div>
			</div>
		</div>
	</div>
	<br><br><br>
	<textarea name="ir1" id="ir1" rows="10" cols="100">네이버 스마트에디터 적용-서버 적용해야함</textarea>
	<button type="button" class="btn">작성내용 콘솔에!!</button>
<!--footer삽입 -->
<%@include file="/views/common/footer.jsp"%>
<script>
	//올린 파일이름 출력
	$(document).on('change','.file-input',function() {
		var $filedirs=$(this).val().split('\\');
		var $filename=$filedirs[$filedirs.length-1];
		console.log($filename);
		if ($filename=='')
			$filename='파일을 선택해주세요';
		$('.filename').text($filename);
	});

	
	//이미지 로딩
	function loadImg(photo){
		$photo=$('#photoArea');
		console.log(photo.files);
		if(photo.files&&photo.files[0]) {
			var reader=new FileReader();
			reader.onload=function(e){
				$photo.css('display', 'block');
				$photo.attr('src', e.target.result);
			}
		}
		reader.readAsDataURL(photo.files[0]);
	}
	
	
	//Content
	let limitByte=1000; //byte최대 크기
	function countContentLength(content) {
		//byte check : textarea에 입력한 길이를 여기 출력
		let check_length=document.getElementById('Byte');
		let message=content.value;
		// console.log(messageLength);
		let totalByte=0;
		for(var i=0;i<message.length;i++) {
			var currentByte=message.charCodeAt(i);
			if(currentByte>128){
				totalByte += 2;
			}else{
				totalByte++;
			}
		}
		check_length.innerText=totalByte;
		//최대 바이트크기를 넘으면 못쓰도록
		if(totalByte>limitByte){
			alert('1000Byte 이내로 작성해주세요!');
		}
	}
	
	
	//smart editor 적용
	var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "ir1",
			sSkinURI: "/lib/smarteditor/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
		
	$(".btn").on("click", function() {
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		var value = document.getElementById("ir1").value;
		console.log(value);
	});
</script>

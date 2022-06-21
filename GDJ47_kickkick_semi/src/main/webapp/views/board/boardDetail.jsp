<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.board.model.vo.*, java.util.ArrayList, com.member.model.vo.*"
%>

<%
	//세션으로 로그인한 멤버부르기
	//Member loginUser=(Member)session.getAttribute("loginUser");
	Board board=(Board)request.getAttribute("board");
	BoardAttachment img=(BoardAttachment)request.getAttribute("img");
	
	System.out.println("공지사항-상세보기");
 	
	System.out.println(board);
	System.out.println(img); 
	
	int fId=0;
	if(img!=null){
		fId=img.getFileId();
	}
%>    
<!-- 정적 파일 불러오기 css/js -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/boardDetail.css">
<script src="<%=request.getContextPath()%>/js/boardDetail.js"></script>
<body>
	<!-- 본내용 -->
    <div class="main-content-container" id="main-container">
        <div class="board-detail-main-container">
                <!-- 타이틀-->
                <div class="board-title-box">
                	<input type="hidden" name="bId" id="bId" value="<%=board.getBoardNum() %>" >
                    <h1 id="title"><%=board.getBoardTitle() %></h1>
                </div>

                <!-- 이미지와 내용 -->
                <div class="board-main-content-box">

                    <!-- 이미지 -->
                    <div class="board-img-box">
                    	<%if(img!=null){ 
                    		//등록한 이미지가 존재 시
                    	%>
                        	<img id="image" src="<%=request.getContextPath() %>/upload/storage/board_img/<%=img.getChangeName() %>" alt="게시판 이미지">
                        <%} %>
                    </div>

                    <!-- 내용 -->
                    <div class="board-content-box">
                        <textarea name="content" id="content" cols=30 style="resize:none;" readonly><%=board.getBoardContent() %></textarea>
                    </div>
                </div>

                <!-- 버튼박스 목록: 모든회원 가능//// 수정/삭제: 관리자만 가능 -->
                <div class="board-btn-box">
                    	
                    <%//if(loginUser!=null && loginUser.getMember_type().equals("R")) {
                    	//관리자 회원인경우에만 버튼을 클릭
                    %>
                    	<button id="goListBoardAd" type="button" class="btn btn-secondary btn-lg"
                    		onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo'">목록</button>
	                    
	                    <button id="editBoard" type="button" class="btn btn-primary btn-lg"
	                    	onclick="updateBoard();">수정</button>
	                    
	                    <button id="removeBoard" type="button" class="btn btn-danger btn-lg" 
	                    	onclick="deleteBoard();">삭제</button>
                    <%//}else{ %>
                    	<button id="goListBoard" type="button" class="btn btn-secondary btn-lg"
                    		onclick="location.href='<%=request.getContextPath()%>/showBoardList.bo'">목록</button>
                    <%//} %>
                	
                </div>  
        </div>
    </div>
	
<%@include file="/views/common/footer.jsp" %>
<script>
	function updateBoard(){
		let bId=<%=board.getBoardNum()%>;
		location.href='<%=request.getContextPath() %>/updateBoardForm.bo?bId='+bId;
		return true;
	}


	function deleteBoard(){
		let bId=<%=board.getBoardNum()%>;
		let fId=<%=fId%>;
		let result=confirm('삭제하시겠습니까?');
		if(result==true){
			alert('공지사항 삭제가 완료되었습니다.');
			
			$.ajax({
				url:'deleteBoard.do',
				type:'post',
				data: {bId: <%=board.getBoardNum( )%>,fId:<%=fId%> },
				success:function(response){
					let result= response['result'];
					console.log(result);
					if(result>0){
						//삭제 성공
						alert('성공적으로 삭제하였습니다.');
					}
					return location.href='<%=request.getContextPath() %>/showBoardList.bo';
				}
				
			});
		}else{
			alert('공지사항 삭제를 취소합니다.');
		}
	}
</script>
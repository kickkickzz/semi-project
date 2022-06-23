<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
    
<%@ page import = "com.member.model.vo.Member" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%Member loginMember = (Member)session.getAttribute("loginMember");
String userId=null;
if(loginMember!=null){
	userId=loginMember.getEmail();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="modal fade" id="exampleModal" 
		>
		<div class="modal-dialog" style="text-align:center">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">&lt;매치등록&gt;</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="control-label"
								style="font-size: 25px;">예약코드 : </label>
							<div class="input-group input-group-lg">
								<input type="text" class="form-control input-sm" id="reservation_code"
									placeholder="예약코드입력">
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="matchRegistBtn" class="simple" onclick="fn_enroll2();">매치
						등록하기</button>
					<button type="button" class="btncancel" data-dismiss="modal" onclick="fn_cancel();">닫기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script>
const fn_cancel=()=>{
	window.close();
}

const fn_enroll2=()=>{
	var userId = '<%=userId%>';
	console.log(userId);
	if(userId !='null'){
		var reservation_code = $('#reservation_code').val();
		if(reservation_code == ""){
			alert('예약코드 입력해주세요');
			 $("#reservation_code").focus();
		}else{
			if (confirm("등록하시겠습니까?") == true){
				$.ajax({
					 url: '<%=request.getContextPath()%>/matchregist.do',
					 data: {userId:userId, reservation_code:reservation_code},
					 success: function(data) {
						 console.log(data);
						 
						 		if(data == 1) {
							 		alert('중복등록불가');
						 		}else if(data == 2){
									 alert('등록되었습니다');
									 opener.location.reload();
							   		 window.close();
								}else if(data == 3){
							 		alert('예약코드등록불가');
								 }else if(data == 4){
									 alert('예약코드없음');
						 		}else {
							 		alert('팀없음');
							 
						 			}
					 			}
				 		});
			}else{//아니오 누르면
				return false;
			
			}
		}
		
		
	}else{
		alert('로그인후이용가능');
		location.assign('<%=request.getContextPath()%>/loginPage.do');
		
	}
	
	
	
	
	
	
	
}
const fn_enroll=()=>{
	
	var userId = '<%=userId%>';
	if(userId !='null'){
	var reservation_code = $('#reservation_code').val();
	if(reservation_code == "") {
		alert('예약코드 입력해주세요');
		 $("#reservation_code").focus(); 
	}else {
		if (confirm("등록하시겠습니까?") == true){
			$.ajax({
				 url: '<%=request.getContextPath()%>/matchregist.do',
				 data: {userId:userId, reservation_code:reservation_code},
				 success: function(data) {
					 console.log(data);
					 
					 		if(data == 1) {
						 		alert('중복등록불가');
					 		}else if(data == 2){
								 alert('등록되었습니다');
								 opener.location.reload();
						   		 window.close();
							}else if(data == 3){
						 		alert('예약코드등록불가');
							 }else if(data == 4){
								 alert('예약코드없음');
					 		}else {
						 		alert('팀없음');
						 
					 			}
				 			}
			 });
				}else{
						console.log("닫혀라")
						window.close();
		     			return false;
		     			
					}
			}
	}else {
	alert('로그인후이용가능');
	location.assign('<%=request.getContextPath()%>/loginPage.do');
		}
};


</script>
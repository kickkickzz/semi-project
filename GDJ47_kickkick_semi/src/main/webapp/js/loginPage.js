let flag = 0;
$(()=>{	
	
	$("#bb").click(function() {
		var bval = $("#bb").val();
		if(bval=="회원가입하기"){
			const form = $("#h-form");
			const email = $(".email").val();
			const password = $(".password").val();
			const name = $(".name").val();
			const phone = $(".phone").val();
			$("#email").val(email);
			$("#password").val(password);
			$("#name").val(name);
			$("#phone").val(phone);
			form.attr("action","http://localhost:9090/GDJ47_kickkick_semi/enrollMember.do");
			form.submit();
		}else{
			const form = $("#h-form");
			const email = $(".email").val();
			const password = $(".password").val();
			$("#email").val(email);
			$("#password").val(password);
			form.attr("action","http://localhost:9090/GDJ47_kickkick_semi/loginMember.do");
			form.submit();
		}
	});
	

	
	

	
	$(".signin").on("click", function(){
	  if(flag == 0){
	    $(".move").addClass("moving");
	    $(".move").removeClass("start");
	    
	    $(".form").addClass("movingForm");
	    $(".form").removeClass("startForm");
	    
	    $(".hello").show();
	    $(".welcome").hide();
	    $(".login-error-msg").hide();
	    $(".move").css("background-position", "right");
	    
	    setTimeout(function(){
	      $(".title").text("회원가입");
	      $(".icons").show();
	      $(".name").show();
	      $(".phone").show();
	      $("#in").hide();
	      $(".p-button").text("로그인하기");
	      $(".b-button").attr("value","회원가입하기");
	      $(".forgot").hide();
	      $(".form").css("border-radius","10px 0px 0px 10px");
	      $(".move").css("border-radius","0px 10px 10px 0px");
	    }, 200);
	    
	    flag=1;
	  }else{
	    $(".move").removeClass("moving");
	    $(".move").addClass("start");
	    
	    $(".form").removeClass("movingForm");
	    $(".form").addClass("startForm");
	    $(".icons").show();
	    
	    $(".hello").hide();
	    $(".welcome").show();
	    
	    $(".move").css("background-position", "left");
	    
	    setTimeout(function(){
	      $(".title").text("로그인");
	      $(".name").hide();
	      $(".phone").hide();
	      $("#up").hide();
	      $(".p-button").text("회원가입하기");
	      $(".b-button").attr("value","로그인하기");
	      $(".forgot").show();
	      $(".form").css("border-radius","0px 10px 10px 0px");
	      $(".move").css("border-radius","10px 0px 0px 10px");
	    }, 200);
	    
	    flag=0;
	  }
	});
});




function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
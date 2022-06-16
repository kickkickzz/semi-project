let flag = 0;
$(()=>{	
	//버튼값에 따라 로그인할지 회원가입할지 정하기
	//form태그 생성해서 데이터 넣어서 보내기
	$("#bb").click(function() {
	const bval = $("#bb").val();
		if(bval=="회원가입하기"){
			var form = document.createElement('form');
			var objs;
			//이메일 담기
			objs = document.createElement('input');
			objs.setAttribute('type', 'text');
			objs.setAttribute('name', 'email');
			objs.setAttribute('value', $('.email').val());
			form.appendChild(objs);
			//비밀번호담기
			objs = document.createElement('input');
			objs.setAttribute('type', 'text');
			objs.setAttribute('name', 'password');
			objs.setAttribute('value', $('.password').val());
			form.appendChild(objs);
			//이름담기
			objs = document.createElement('input');
			objs.setAttribute('type', 'text');
			objs.setAttribute('name', 'name');
			objs.setAttribute('value', $('.name').val());
			form.appendChild(objs);
			//전화번호담기
			objs = document.createElement('input');
			objs.setAttribute('type', 'text');
			objs.setAttribute('name', 'phone');
			objs.setAttribute('value', $('.phone').val());
			form.appendChild(objs);
			//전송
			form.setAttribute('method', 'post');
			form.setAttribute('action', "<%=request.getContextPath()%>/enrollMember.do");
			document.body.appendChild(form);
			form.submit();


		}else{
			
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
	    
	    
	    $(".move").css("background-position", "right");
	    
	    setTimeout(function(){
	      $(".title").text("로그인");
	      $(".light").text("소셜아이디로 로그인하세요!");
	      $(".icons").hide();
	      $(".name").hide();
	      $(".phone").hide();
	      $(".p-button").text("회원가입하기");
	      $(".b-button").attr("value","로그인하기");
	      $(".forgot").show();
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
	      $(".title").text("회원가입");
	      $(".light").text("소셜아이디로 회원가입하세요!");
	      $(".name").show();
	      $(".phone").show();
	      $(".p-button").text("로그인하기");
	      $(".b-button").attr("value","회원가입하기");
	      $(".forgot").hide();
	      $(".form").css("border-radius","0px 10px 10px 0px");
	      $(".move").css("border-radius","10px 0px 0px 10px");
	    }, 200);
	    
	    flag=0;
	  }
	});
});
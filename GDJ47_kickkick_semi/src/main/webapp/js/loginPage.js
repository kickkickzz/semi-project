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
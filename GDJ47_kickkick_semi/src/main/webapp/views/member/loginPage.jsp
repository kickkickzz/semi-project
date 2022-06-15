<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="/views/common/header.jsp" %> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/loginPage.css">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<div id="login-container">
	<div id="login-left">
		<img id="semi-logo" src="<%=request.getContextPath()%>/images/세미로고.png">
	</div>
	<div id="login-right">
		<img id="bg-img" src="<%=request.getContextPath() %>/images/login-bg.jpg">
		<div class="container">
  			<div class="move">
    			<div class="p-button normal signin animated pulse">로그인하기</div>
  			</div>
  			<div class="welcome">
    			<h4 class="bold welcome-text">로그인</h4>
    			<p class="normal text">아이디가 있다면 로그인을 하세요! <br>로그인을 통해 더 많은 서비스를<br> 이용할 수 있습니다.</p>
  			</div>
  			<div class="hello">
    			<h4 class="bold welcome-text">회원가입</h4>
    			<p class="normal text">아직 아이디가 없다면<br> 회원가입을 하세요!<br>더 많은 서비스들이 당신을 기다리고 있습니다.</p>
  			</div>
  			<div class="form">
    			<h4 class="bold title">회원가입</h4>
    			<div class="icons">
      				<div class="icon"><i class="fab fa-facebook-f"></i></div>
      				<div class="icon"><i class="fab fa-github"></i></div>
      				<div class="icon"><i class="fab fa-twitter"></i></div>
    			</div>
    			<p class="normal light">소셜아이디로 회원가입하세요!</p>
	    		<input type="text" placeholder="이름" class="normal name">
	    		<input type="text" placeholder="이메일" class="normal">
	    		<br/>
		    	<input type="password" placeholder="비밀번호" class="normal"><br>
		    	<p class="normal forgot">비밀번호를 잊어버리셨습니까?</p>
		    	<button class="b-button normal">회원가입하기</button>
  			</div>
		</div>
	</div>
</div>





<script>
let flag = 0;


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
      $(".name").hide();
      $(".p-button").text("회원가입하기");
      $(".b-button").text("로그인하기");
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
    
    
    $(".hello").hide();
    $(".welcome").show();
    
    $(".move").css("background-position", "left");
    
    setTimeout(function(){
      $(".title").text("회원가입");
      $(".light").text("소셜아이디로 회원가입하세요!");
      $(".name").show();
      $(".p-button").text("로그인하기");
      $(".b-button").text("회원가입하기");
      $(".forgot").hide();
      $(".form").css("border-radius","0px 10px 10px 0px");
      $(".move").css("border-radius","10px 0px 0px 10px");
    }, 200);
    
    flag=0;
  }
});
</script>

<%-- <%@ include file="/views/common/footer.jsp" %> --%>
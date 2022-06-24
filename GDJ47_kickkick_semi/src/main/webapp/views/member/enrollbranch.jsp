<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import ="com.member.model.vo.Member" %>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
	String email = null;
	if(loginMember!=null){
		email=loginMember.getEmail();  
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my_page</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
   body{
      height:700px;
      text-align:center;
   }

   .form-horizontal{
      width:100%;
      margin:0 auto;
      padding:0 50px;
      box-sizing:border-box;
   }

   .form-group{
      width:300px;
      height:35px;
      margin: 10px 0 30px 0;
      position:relative;
      display:inline-block;
   } 
   
   .form-group > input{
      width:100%;
      height:100%;
      border:none;
      border:2px solid gray;
      font-size:18px;
   }
   
   .form-group > input[type="date"]{
      font-size:12px;
   }
   
   .form-group > label{
      position:absolute;
      top:2px;
      left:15px;
      transform:translateY(-50%);
      font-size:15px;
      color:gray;
      padding:0px;
      background:#fff;
   }

   .form-detail textarea{
      padding-top:10px;
   }
   
   .form-group > textarea{
      border-color:gray;
      border-width:2px;
   }
   
   .radio-group{
      width:300px;
      border:2px solid gray;
      position:relative;
      margin-bottom:25px;
      display:inline-block;
   }
   
   .radio-group > label:first-of-type{
      position:absolute;
      top:2px;
      left:15px;
      transform:translateY(-50%);
      font-size:15px;
      color:gray;
      padding:0px;
      background:#fff;
   }
   
   /*파일선택 버튼*/
   .file_input label{
      position:relative;
       cursor:pointer;
       display:inline-block;
       vertical-align:middle;
       overflow:hidden;
       width:100px;
       height:30px;
       background:#777;
       color:#fff;
       text-align:center;
       line-height:30px;
       top:5px;
   }
   
   .file_input label input{
      position:absolute;
      display:none;
      overflow:hidden;
   }
   
   .file_input input[type=text]{
      display:inline-block;
      height:20px;
      vertical-align:middle;
      width:300px;
   }
   
   /* 카카오 */
   .kakao-group{
      width:300px;
      height:25px;
      display:inline-flex;
      flex-wrap:wrap;
      justify-content:space-between;
   } 
   
   .kakao-group > input:not(.addressBtn){
      width:45%;
      height:100%;
      border:none;
      border:2px solid gray;
      font-size:18px;
      margin-bottom:5px;
   }
   
   .kakao-group >.addressBtn{
      width:45%;
      height:100%;
      border:none;
      font-size:18px;
      margin-right:-10px;
   }
   
   
   /* hr */
   hr{
      border:0;
      height: 3px;
      background:#ccc;
   }
   
   /* 버튼 */
   .btn-groups{
      text-align:center;
   }
   
   .btn-groups button{
      background-color:gray;
      color:#fff;
      border:0;
      width:70%;
      height:40px;
   }
   
   .btn-groups > button:first-of-type {
      margin-right:10px;
   }
</style>
</head>
<body>
    <form class="form-horizontal" action="<%= request.getContextPath() %>/enrollbranchend.do" method="post" encType="multipart/form-data" id="registForm" name="registForm" onsubmit="return validate();">
      <div class="form-group">
         <label for="branchName">지점명</label>
         <input type="text" name="branchName" id="branchName"/>
         <input type="button" id="branchCheck" value="중복확인" style="border:none; margin-top:5px; box-sizing:conten-box; padding:0px;">
      </div><br><br>
      <div class="form-group">
         <label for="placeInfo">지점 한줄 소개 <span id="count1">(0 / 최대 20자)</span></label>
         <input type="text" name="branchInfo" id="branchInfo" maxlength="20" style="font-size:12px;"/>
      </div>
      <div class="form-group form-detail">
         <label for="detailInfo">공간 소개 <span id="count2">(0 / 최대 1000자 (최소 30자))</span></label>
         <textarea rows="10" cols="36" name="detailInfo" id="detailInfo" maxlength="1000" required="required"></textarea>
      </div>
      <br><br>
      <div class="radio-group">
         <label for="facility">이용가능</label><br>
         샤워실 이용
         <input type="radio" name="shower" id="shower" value="Y" />
         <label for="showerok">가능</label>
         <input type="radio" name="shower" id="shower" value="N" />
         <label for="showernop">불가능</label><br>
         주차 이용
         <input type="radio" name="parking" id="parking" value="Y" />
         <label for="parkingok">가능</label>
         <input type="radio" name="parking" id="parking" value="N" />
         <label for="parkingnop">불가능</label><br>
         유니폼 대여
         <input type="radio" name="uniform" id="uniform" value="Y" />
         <label for="uniformok">가능</label>
         <input type="radio" name="uniform" id="uniform" value="N" />
         <label for="uniformnop">불가능</label><br>
         풋살화 대여
         <input type="radio" name="shoes" id="shoes" value="Y" />
         <label for="shoesok">가능</label>
         <input type="radio" name="shoes" id="shoes" value="N" />
         <label for="shoesnop">불가능</label><br>
         볼 대여
         <input type="radio" name="ball" id="ball" value="Y" />
         <label for="ballok">가능</label>
         <input type="radio" name="ball" id="ball" value="N" />
         <label for="ballnop">불가능</label><br>
         실내/실외
         <input type="radio" name="place" id="place" value="I" />
         <label for="inside">실내</label>
         <input type="radio" name="place" id="place" value="O" />
         <label for="outside">실외</label><br>
      </div>   
      
      <div class="form-group">
         <label for="notes">예약시 주의사항 <span id="count3">(0 / 최대 100자)</span></label>
         <input type="text" name="notes" id="notes"/>
      </div>
      <div class="form-group">
         <label for="sns">SNS</label>
         <input type="text" name="sns" id="sns"/>
      </div>
      <div class="form-group">
         <label for="phone">연락처</label>
         <input type="tel" name="phone" id="phone" required="required"/>
      </div>
      <input type="hidden" name="email" id="email" value="<%=email%>"/>
   	
		   	
      <div class="selectCover" style="padding-left: 0;">
         <img id="cover"
            src="<%=request.getContextPath()%>/images/풋살장.PNG"
            style="width: 282px; height: 268px;" />
      </div>
      <div class="file_input" style="margin-bottom: 20px;">
      <input id="fileName" name="fileName" class="upload-name" value="파일선택" style="width: 200px; height:25px;" readonly>
      <!-- <input type="file" id="" multiple="multiple"> -->
      <label style="position: relative; top: 0; background-color: rgb(239, 239, 239); color: black; font-size: 18px;">
      	파일선택
      <input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1">
      </label>
      </div>   
   
      <!-- 카카오 우편번호 서비스 -->
      <div class="kakao-group">
         <input type="button" onclick="sample4_execDaumPostcode()" name="address" class="addressBtn" value="우편번호 찾기"><br>
         <input type="text" id="sample4_postcode" name="address1" placeholder="우편번호">
         <input type="text" id="sample4_roadAddress" name="address2" placeholder="도로명주소">
         <input type="text" id="sample4_jibunAddress" name="address3" placeholder="지번주소">
            <span id="guide" style="color: #999; display: none"></span>
         <input type="text" id="sample4_detailAddress" name="address4" placeholder="상세주소">
         <input type="text" id="sample4_extraAddress" name="address5" placeholder="참고항목">
      </div>
      <br><br><br><br><br><br>
      <hr>
      
      <!-- 버튼 그룹 -->
      <div class="btn-groups">
         <button type="submit">지점 등록</button>
      </div>
      <br><br><br>
      <!-- 버튼 그룹 끝  -->
   </form>
   <script>
   
   
   
      function validate() {
         var branchName = $('#branchName').val();
         var placeInfo = $('#placeInfo').val();
         var detailInfo = $('#detailInfo').val();
         var shower = $('#shower').val();
         var parking = $('#parking').val();
         var uniform = $('#uniform').val();
         var shoes = $('#shoes').val();
         var ball = $('#ball').val();
         var place = $('#place').val();
         var notes = $('#notes').val();
         var sns = $('#sns').val();
         var phone = $('#phone').val();
         var branchImg = $('#fileName').val();
            
         if(branchName == "") {
            $('#branchName').focus();
            alert('지점이름입력해주세요');
            return false;
         }else if(placeInfo == ""){
            alert('지점한줄소개해주세요');
            $('#placeInfo').focus();
            return false;
         }else if(detailInfo == ""){
            alert('공간소개해주세요');
            $('#detailInfo').focus();
            return false;
         }else if(shower == ""){
            alert('옵션체크해주세요');
            $('#shower').focus();
            return false;
         }else if(parking == ""){
            alert('옵션체크해주세요');
            $('#parking').focus();
            return false;
         }else if(uniform == ""){
            alert('옵션체크해주세요');
            $('#uniform').focus();
            return false;
         }else if(shoes == ""){
            alert('옵션체크해주세요');
            $('#shoes').focus();
            return false;
         }else if(ball == ""){
            alert('옵션체크해주세요');
            $('#ball').focus();
            return false;
         }else if(place == ""){
            alert('옵션체크해주세요');
            $('#place').focus();
            return false;
         }else if(notes == ""){
            alert('예약시주의사항적어주세요');
            $('#notes').focus();
            return false;
         }else if(sns == ""){
            alert('SNS적어주세요');
            $('#sns').focus();
            return false;
         }else if(phone == ""){
            alert('연락처적어주세요');
            $('#phone').focus();
            return false;
         }else if(branchImg == ""){
            alert('이미지등록해주세요');
            $('#branchImg').focus();
            return false;
         }else {
            return true;
            
         }
      }
      const fn_checkBranch=()=>{
    	  window.open("<%=request.getContextPath()%>/checkbranch.do", "checkBranchForm", "width=300, height=200");
      }
      
   
      $('#branchInfo').keyup(function (e){
          var content = $(this).val();
          $('#count1').html("("+content.length+" / 최대 20자)");
   
          if (content.length > 20){
              $(this).val(content.substring(0, 20));
              $('#count1').html("(20 / 최대 20자)");
          }
      });
      
      $('#detailInfo').keyup(function (e){
          var content = $(this).val();
          $('#count2').html("("+content.length+" / 최대 1000자 (최소 30자))");
          
          if (content.length > 1000){
              alert("최대 1000자까지 입력 가능합니다.");
              $(this).val(content.substring(0, 1000));
              $('#count2').html("(1000 / 최대 1000자 (최소 30자))");
          }
      });
      
      $('#notes').keyup(function (e){
          var content = $(this).val();
             $('#count3').html("("+content.length+" / 최대 100자)");
             
             if (content.length > 100){
                 $(this).val(content.substring(0, 100));
                 $('#count3').html("(100 / 최대 100자)");
             } 
      });
      
      function readURL(input) {
           if (input.files && input.files[0]) {
           var reader = new FileReader();
           reader.onload = function (e) {
                   $('#cover').attr('src', e.target.result);
                   $('#fileName').val(input.files[0].name);
                   console.log($('#fileName').val());
               }
             reader.readAsDataURL(input.files[0]);
           }
       }
      
      $('#thumbnailImg1').change(function() {
         readURL(this);
      });
   
      function sample4_execDaumPostcode() {
         new daum.Postcode(
               {
                  oncomplete : function(data) {
                     var roadAddr = data.roadAddress;
                     var extraRoadAddr = '';
                     if (data.bname !== ''
                           && /[동|로|가]$/g.test(data.bname)) {
                        extraRoadAddr += data.bname;
                     }
                     if (data.buildingName !== ''
                           && data.apartment === 'Y') {
                        extraRoadAddr += (extraRoadAddr !== '' ? ', '
                              + data.buildingName : data.buildingName);
                     }
                     if (extraRoadAddr !== '') {
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                     }
                     document.getElementById('sample4_postcode').value = data.zonecode;
                     document.getElementById("sample4_roadAddress").value = roadAddr;
                     document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                     if (roadAddr !== '') {
                        document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                     } else {
                        document.getElementById("sample4_extraAddress").value = '';
                     }
                     var guideTextBox = document.getElementById("guide");
                     if (data.autoRoadAddress) {
                        var expRoadAddr = data.autoRoadAddress
                              + extraRoadAddr;
                        guideTextBox.innerHTML = '(예상 도로명 주소 : '
                              + expRoadAddr + ')';
                        guideTextBox.style.display = 'block';
                     } else if (data.autoJibunAddress) {
                        var expJibunAddr = data.autoJibunAddress;
                        guideTextBox.innerHTML = '(예상 지번 주소 : '
                              + expJibunAddr + ')';
                        guideTextBox.style.display = 'block';
                     } else {
                        guideTextBox.innerHTML = '';
                        guideTextBox.style.display = 'none';
                     }
                  }
               }).open();
      }
      
      $(function(){

		    $("#phone").on('keydown', function(e){
		       // 숫자만 입력받기
		        var trans_num = $(this).val().replace(/-/gi,'');
			var k = e.keyCode;
						
			if(trans_num.length >= 11 && ((k >= 48 && k <=126) || (k >= 12592 && k <= 12687 || k==32 || k==229 || (k>=45032 && k<=55203)) ))
			{
		  	    e.preventDefault();
			}
		    }).on('blur', function(){ // 포커스를 잃었을때 실행합니다.
		        if($(this).val() == '') return;

		        // 기존 번호에서 - 를 삭제합니다.
		        var trans_num = $(this).val().replace(/-/gi,'');
		      
		        // 입력값이 있을때만 실행합니다.
		        if(trans_num != null && trans_num != '')
		        {
		            // 총 핸드폰 자리수는 11글자이거나, 10자여야 합니다.
		            if(trans_num.length==11 || trans_num.length==10) 
		            {   
		                // 유효성 체크
		                var regExp_ctn = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
		                if(regExp_ctn.test(trans_num))
		                {
		                    // 유효성 체크에 성공하면 하이픈을 넣고 값을 바꿔줍니다.
		                    trans_num = trans_num.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");                  
		                    $(this).val(trans_num);
		                }
		                else
		                {
		                    alert("유효하지 않은 전화번호 입니다.");
		                    $(this).val("");
		                    $(this).focus();
		                }
		            }
		            else 
		            {
		                alert("유효하지 않은 전화번호 입니다.");
		                $(this).val("");
		                $(this).focus();
		            }
		      }
		  });  
		    
		    //0912
		    var num = 1;
		      $('#detailInfo').on('blur', function(){
		    	  var count = 30;
		    	  var length = $(this).val().length;
		    	  count = length;
		          
		    	  console.log(num);
		   			  if(num % 2 == 1){
		   				if(count < 30){
		   				  alert("최소 30자 입력 해주세요.");
		               	  num += 1;
		               	  $(this).focus();
		   			  	}
		           	  
		           	} else{num += 1;}
		    
		      });
		});
      
      
      $("#branchCheck").click(e=>{
    	  
    	  var branchname = $("#branchName").val();
    	  console.log(branchname);
    	  
    	  $.ajax({
    		  url:"<%=request.getContextPath()%>/checkbranch.do",
    		  type:'post',
    		  data:{branchname:branchname},
    		  success:(data)=>{
    			  console.log(data);
    			  if(data==1){
    				  alert("중복된 지점명입니다.");
    				  $("#branchName").text("");
    			  }else if(data==0){
    				  alert("사용가능한 지점명입니다.")
    				  $("#branchName").text(branchname);
    			  }
    		  }
    	  });
      })
      
      const fn_branchcheck=()=>{
    	 
    	 
      }
      
   </script>
</body>
</html>
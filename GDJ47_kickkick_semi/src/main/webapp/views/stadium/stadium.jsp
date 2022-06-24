<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import= "com.reservation.model.vo.Stadium,java.util.List" %>
    
<%@ include file="/views/common/header.jsp" %>
<% List<Stadium> result = (List<Stadium>)request.getAttribute("staArr");
   String pagebar = (String)request.getAttribute("pageBar");
String userId=null;
if(loginMember!=null){
   userId=loginMember.getEmail();
}
   
 String type ="default";
if(loginMember!=null){
   type=loginMember.getType();
}
%>
<style>
.Search{
   width:300px; 
     height:50px;
   border:3px solid #ccc;
}
.Search::placeholder{
   color: gray;
   font-size: large;
   font-weight:200;
}

</style>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/7bb5347123.js" crossorigin="anonymous"></script>
      
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/3de5dd50e8.js" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <section>
   <div class="containerr">
      
      <div>
      <br>
      <br>
      <form action="<%=request.getContextPath()%>/searchstadium.do" onsubmit="return fn_searchDataa(event);">
      <input type="text" name="searchstadium" placeholder="검색할 구장명을 입력하세요" size="25" onkeyup="submit" class="Search"> 
      </form>
      
      
      <%if(type.equals("M")) {%> 
         <button type="button" style="float: right;" data-toggle="modal" data-target="#myModal">구장 등록</button>
      <%} %>
      <br>
      <br>
         </div>
         
      <table class="table table-hover table-striped text-center">
      <thead>
         <tr>
            <td>no</td>
            <td>이미지</td>
            <td>장소</td>
            <td>구장명</td>
            <td>구장</td>
            <td>매치</td>
            <td>예약가능</td>
            <td>전화번호</td>         
         </tr>
      </thead>
      <%if(result==null){ %>
      <tbody>
         <tr>
            <td colspan="7"></td>
         </tr>
      </tbody>
      <%}else{ 
      
         for(Stadium s:result){%>
         <tbody>
         
         <tr onclick="location.assign('<%=request.getContextPath()%>/reservation.do?stanum=<%=s.getStadium_num()%>')">
            <td><%=s.getStadium_num() %></td>
            <td><img src="<%=request.getContextPath()%>/resources/storage/branch_img/<%=s.getBranch_img() %>" width="100" height="80"></td>
            <td><%=s.getBranch_address() %></td>
            <td><%=s.getBranch_num() %></td>
            <td><%=s.getStadium_name() %></td>
            <td><%=s.getStadium_match_member() %></td>
            <td><%=s.getStadium_reservation_start_time()%> : 00 ~ <%=s.getStadium_reservation_end_time()%> : 00</td>
            
            <td><%=s.getBranch_phone()%></td>
            
         </tr>
         
         <%} %>
      </tbody>
      <%} %>
   
   
   </table>
      <div class ="" id="pageBar" style="text-align:center">
      <%=pagebar %></div>
      
   

   </div>

   </div>

   </section>
   <div class="modal fade" id="myModal" role="dialog"> <!-- 사용자 지정 부분① : id명 -->
<div class="modal-dialog">

 <!-- Modal content-->
<div class="modal-content">
<div class="modal-header">
   <h4 class="modal-title" id="exampleModalLabel">&lt;구장등록&gt;</h4>
   
</div>
<div class="modal-body">
   <form>
                  <div class="form-group">
                     <label for="recipient-name" class="control-label" style="font-size: 25px;">구장이름 : </label>
                     <div class="input-group input-group-lg">
                        <input type="text" class="form-control input-sm" id="stadium_name"
                           placeholder="구장이름입력">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="input-group mb-3">
                        <label for="recipient-name" class="control-label"
                           style="font-size: 25px;">매치인원 : </label>
                        &nbsp;&nbsp; 
                        <span class="input-group-addon" style="margin-left:30px">
                           <i class="fa fa-user fa" aria-hidden="true"></i>
                        </span> 
                        <select id="stadium_matchMember" style="margin-left:10px">
                           <option value="">매치인원 선택</option>
                           <option value="3vs3">3 vs 3</option>
                           <option value="4vs4">4 vs 4</option>
                           <option value="5vs5">5 vs 5</option>
                           <option value="6vs6">6 vs 6</option>
                           <option value="7vs7">7 vs 7</option>
                           <option value="8vs8">8 vs 8</option>
                        </select>
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="input-group mb-3">
                        <label for="recipient-name" class="control-label" style="font-size: 25px;">지점선택 : </label>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="input-group-addon">
                           <i class="fa fa-user fa" aria-hidden="true"></i>
                        </span>
                        <select name="branch_num" id="branch_num" style="margin-left:10px">
                           <option value="------">------</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" id="branchBtn" class="simple">지점조회</button>
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="input-group mb-3">
                        <label for="recipient-name" class="control-label"
                           style="font-size: 25px;">예약가능시간 : </label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="input-group-addon">
                           <i class="fa fa-user fa" aria-hidden="true"></i>
                        </span>
                        <select name="startTime" id="startTime" style="margin-left:10px">
                           <option value="">시간선택</option>
                           <option value=9>09:00</option>
                           <option value="10">10:00</option>
                           <option value="11">11:00</option>
                           <option value="12">12:00</option>
                           <option value="13">13:00</option>
                           <option value="14">14:00</option>
                           <option value="15">15:00</option>
                           <option value="16">16:00</option>
                           <option value="17">17:00</option>
                           <option value="18">18:00</option>
                           <option value="19">19:00</option>
                           <option value="20">20:00</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp; 
                        <select name="endTime" id="endTime">
                           <option value="">시간선택</option>
                           <option value="10">10:00</option>
                           <option value="11">11:00</option>
                           <option value="12">12:00</option>
                           <option value="13">13:00</option>
                           <option value="14">14:00</option>
                           <option value="15">15:00</option>
                           <option value="16">16:00</option>
                           <option value="17">17:00</option>
                           <option value="18">18:00</option>
                           <option value="19">19:00</option>
                           <option value="20">20:00</option>
                           <option value="21">21:00</option>
                        </select>
                     </div>
                  </div>
               </form>
</div>
<div class="modal-footer">
<button type="button" id="stadiumRegistBtn" class="btn btn-default" data-dismiss="">구장등록하기</button>
<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>
</div>
</div>
</div>




<script>

      const fn_searchDataa=e=>{
         if($(e.target).find("input").val().length==0){
            alert("값을 입력하고 조회하세요.");
            return false;
         }
      }
   
      
      function fn_stadiuminsert(){
         open("<%=request.getContextPath()%>/views/stadium/stadiuminsert.jsp","_blank","width=500, height=600 ,left=700, top=250");
      }
      
      
      //지점 등록 버튼
      $("#branchBtn").click(e=>{
         var branchNum = "";
         
         var email = '<%=userId%>';
         $.ajax({
            url: "<%=request.getContextPath()%>/stadiumSearch.do",
            type :"post",
            data : {email:email},
            success :(data)=>{ //json을 이용해서 배열로 data를 받아올려고 하는데 그러면
               console.log(data);
               if(data.length!=0){
                  console.log(data);
                  alert("지점이 조회되었습니다.");
                   $('#branch_num').empty();  //받아온 데이터를 for문 돌릴려고 하는데
                  $.each(data , function(key,value){ 
                     
                     
                     
                     console.log(key+data[key])
                     //value가 서블릿에서 받아온 data를 value에 하나씩 넣어줌
                     branchNum += "<option value="+ value+">"+ value+"지점</option>";
                  });
                  $('#branch_num').html(branchNum);//branchNum 배열에 하나씩 들어감 option 값이 그러면 이제 append로 select 에 추가
               }else{
                  alert("조회된 지점이 없습니다.");
                  branchNum += '<option value="------">'+"------"+'</option>';
               }
               
            }
         });
      });
      
      
      //구장 등록하기 버튼 
      $("#stadiumRegistBtn").click(e=>{
         var email = '<%=userId%>'
         const stadiumName = $("#stadium_name").val(); //구장이름
         const stadiumMatchMember = $("#stadium_matchMember").val();//구장매치 인원
         const branchNum = $("#branch_num").val();//지점 이름
         const starttime = $("#startTime").val();
         const startTime = parseInt(starttime); 
         const endtime =  $("#endTime").val();
         const endTime = parseInt(endtime); 
         if(stadiumName == ""){
            alert("구장이름을 입력하세요");
         }else if(stadiumMatchMember == ""){
            alert("구장 매치 인원을 선택하세요");
         }else if(branchNum =="------"){ //나중에 여기 != > == 로 바꿔야함!!
            alert("지점을 선택하세요");
         }else if(startTime>endTime){
            alert("예약가능시간이 잘못되었습니다 다시 선택해주세요.");
         }else{
            $.ajax({
               url : "<%=request.getContextPath()%>/insertStadium.do",
               type : "post",
               data : {
                  email:email
                  ,stadiumName:stadiumName
                  ,stadiumMatchMember:stadiumMatchMember
                  ,branchNum:branchNum
                  ,startTime:startTime
                  ,endTime:endTime},
               success :(data)=>{  //data에 result값이 넘어옴
                  if(data>0){
                     alert("구장 등록이 완료되었습니다.");
                  }else{
                     alert("구장 등록에 실패하였습니다.");
                  }
               }
            })
         }

      })


  

</script>
   


<style>
div.containerr{
margin : 0px 100px 0px 100px;

}
.modal-header{
   align-items: center;
   justify-content: center;
}
</style>


<%@ include file="/views/common/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<!-- 여기가 마이페이지 초기화면 -->
<%@ include file="/views/common/memberviewsidebar.jsp" %>  
  <div class="main">
	<form action="" method="post">
                    <div class="update">
                        <p style="margin-bottom : 3px;">이메일</p>
                        <input type="text" value="" style="margin-bottom : 7px;" size="40">
                    </div>  
                    <div class="update">
                          <p style="margin-bottom : 3px;">이름</p>
                          <input type="text" value="" readonly style="margin-bottom : 7px;" size="40">
                    </div>
                    <div class="update">
                          <p style="margin-bottom : 3px;">비밀번호</p>
                          <button onclick="">비밀번호 변경</button>
                    </div>  
                    <div class="update">
                          <p style="margin-bottom : 3px;">생년월일</p>
                          <input type="text" name="birthday"  value="" placeholder="yyyyMMdd" style="margin-bottom : 7px;" size="40">
                    </div>
                    <div class="update">
                          <p style="margin-bottom : 3px;">연락처</p>
                          <input type="text" name="phone" value="" placeholder="-없이 입력" maxlength="11" style="margin-bottom : 7px;" size="40">
                    </div>
                    <div class="update">
                          <p style="margin-bottom : 3px;">주소</p>
                          <input type="text" name="address" value="" placeholder="띄어쓰기로 구분" style="margin-bottom : 7px;" size="40">
                    </div>
                    <div class="update">
                          <p style="margin-bottom : 3px;">성별</p>
                          <input type="radio" name="gender" id="gender0" value="M" style="margin-bottom : 80px;">
                                        <label for="gender0">남</label>
                                        <input type="radio" name="gender" id="gender1" value="F">
                                        <label for="gender1">여</label>
                    </div>
                    <input type="button" value="수정">
                    <input type="button" value="취소">
               </form>
  		</div>
  </section>
  <div class="asd">
        <h2>회원정보수정</h2>
   </div>
  <hr width="400px" height="10px;">
 
<%@ include file="/views/common/footer.jsp" %>
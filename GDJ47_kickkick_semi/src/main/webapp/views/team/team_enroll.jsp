<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.team.model.vo.Team,com.member.model.vo.Member, javax.servlet.http.HttpSession " %>
<%@ include file="/views/common/header.jsp" %>
<%
%>
<context>
<title>팀 등록 화면</title>

<br><br><br><br><br><br><br><br>

 <h2>팀 정보 입력</h2>
        <form name="enrollMemberFrm" action="<%=request.getContextPath() %>/team/teamRegist.do" method="post" 
        onsubmit="return fn_enrollmemberValidate();" >
        <table>
			<tr>
				<th>팀 이름</th>
				<td>
					<input type="text" placeholder="5글자이하" name="userId" id="userId_" >
					<input type="button" value="중복확인"
					onclick="fn_idDuplicate();">
				</td>
			</tr>
			
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
				<th>성별 </th>
				<td>
					<select id="team_gender" name="team_gender">
						<option value="">팀 성별 선택</option>
						<option value="남자그룹">남자그룹</option>
						<option value="여자그룹">여자그룹</option>
						<option value="남녀그룹">남녀그룹</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>평균나이 </th>
				<td>
					<select id="team_gender" name="team_age">
						<option value="10대">10대</option>
						<option value="20대">20대</option>
						<option value="30대">30대</option>
						<option value="40대">40대</option>
						<option value="50대">50대</option>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit" value="생성" >
		<input type="reset" value="취소">
        </form>
        <form name="idDuplicateFrm">
        	<input type="hidden" name="userId">
        </form>
    </section>
    <script>
    	const fn_idDuplicate=()=>{
    		const userId=$("#userId_").val().trim();
    		if(userId.length<4){
    			alert("아이디는 5글자 이하이어야 합니다.");
    			$("#userId_").focus();
    		}else{
    			const url="<%=request.getContextPath()%>/team/idDuplicate.do";
    			const title="idDuplicateFrm";
	    		open("",title,"width=300,height=200");
	    		idDuplicateFrm.userId.value=userId;
	    		idDuplicateFrm.method="post";
	    		idDuplicateFrm.action=url;
	    		idDuplicateFrm.target=title;
	    		idDuplicateFrm.submit();
    		}
    		
    		
    		
    	}
    	const fn_enrollmemberValidate=()=>{
    		//아이디의 길이 4이상
    		const userId=$("#userId_").val();
    		if(userId.trim().length<4){
    			alert("팀 명은 5글자 이하로 작성하세요");
    			$("#userId_").focus();
    			return false;
    		}
    		return true;
    	}
    </script>
 
							
							
<%@ include file="/views/common/footer.jsp" %>
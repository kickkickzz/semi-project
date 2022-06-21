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
        <form name="teamEnroll" action="<%=request.getContextPath() %>/team/teamRegistEnd.do" method="post" 
        onsubmit="return fn_enrollteamValidate();" enctype="multipart/form-data" >
        <table>
			<tr>
				<th>팀 이름</th>
				<td>
					<input type="team_name" placeholder="5글자이하" id="team_name" name="team_name" value=""><input type="button" id="namecheck" value="중복확인" onclick="fn_nameDuplicate();">
				</td>
			</tr>
			
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
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
					<select id="team_age" name="team_age">
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
        <form name="nameDuplicate">
        	<input type="hidden" name="team_name">
        </form>
    </section>
    <script>
    	function fn_nameDuplicate(){
    		const team_name=$("#team_name").val();
    		console.log(team_name);
    		if(team_name.length>5){
    			
    			alert("팀 명은 5글자 이하이어야 합니다.");
    			
    		}else{
    			const url="<%=request.getContextPath()%>/team/idDuplicate.do";
    			
    			const title="nameDuplicate";
    			
	    		open("",title,"width=300,height=200");
	    		console.log(nameDuplicate);
	    		
	    		nameDuplicate.team_name.value=team_name;
	    		nameDuplicate.method="post";
	    		nameDuplicate.action=url;
	    		nameDuplicate.target=title;
	    		nameDuplicate.submit();
    		}
    		
    		
    		
    		
    	}
    	const fn_enrollteamValidate=()=>{
    		//아이디의 길이 4이상
    		const team=$("#team_name").val();
    		console.log(team);
    		if(team.length>5){
    			alert("팀 명은 5글자 이하로 작성하세요");
    			return false;
    		}
    		return true;
    	}
    </script>
 
							
							
<%@ include file="/views/common/footer.jsp" %>
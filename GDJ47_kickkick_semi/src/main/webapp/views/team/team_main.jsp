<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List,com.team.model.vo.Team " %>
<%
	List<Team> list=(List<Team>)request.getAttribute("list");
//나중에페이징처리
%>

<section id="notice-container">
        <h2>팀 정보</h2>
      
        	
       
        <table id="tbl-notice">
            <tr>
            
                <th>팀 명</th>
                <th>그룹 성별</th>
                <th>연령대</th>
                <th>지역</th>
               
            </tr>
			<%if(list.isEmpty()){ %>
				<tr>
					<td colspan="5">조회된 팀이 없습니다.</td>
				</tr>
			<%}else{
				for(Team t: list){%>
					<tr>
						<td><%=t.getTeam_name() %></td>
						<td>
							<%=t.getTeam_gender() %>
						</td>
						<td><%=t.getTeam_age() %></td>
						
						<td><%=t.getTeam_region() %></td>
					</tr>
			<%	}
			  }%>
        </table>
        
        <button type="button" onclick="location.assign('<%=request.getContextPath()%>/team/teamRegist.do')">팀 등록</button>
    </section>
 
       
        
       

<%@ include file="/views/common/footer.jsp" %>
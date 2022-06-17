<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List,com.team.model.vo.Team " %>
<%
	List<Team> t=(List<Team>)request.getAttribute("t");

	
//나중에페이징처리
%>
	<table class="table table-striped" style="table-layout: fixed;">
	 <tr>
            
                <th>지역</th>
                <th>그룹 성별</th>
                <th>연령대</th>
                <th>팀 별점</th>
               
            </tr>
			<%if(t.isEmpty()){ %>
				<tr>
					<td colspan="5">조회된 팀이 없습니다.</td>
				</tr>
			<%}else{
				for(Team team: t){%>
					<tr onclick="location.href='<%=request.getContextPath()%>/team/teamMemberInfo.do?team_code=<%=team.getTeam_code()%>'">
					
						<td><%=team.getTeam_region() %></td>
						<td>
							<%=team.getTeam_gender() %>
						</td>
						<td><%=team.getTeam_age() %></td>
						
						<td><%for (int i = 0; i < team.getTeam_point(); i++) { %><span class="fa fa-star checked"></span> <%}%>
                            <%for (int i = 0; i < 5-team.getTeam_point(); i++) { %><span class="fa fa-star"></span> <% }%></h1></td>
					</tr>
			<%	}
			  }%>
	
								<%-- 
  <table id="tbl-notice">
            <tr>
         		<th scope="col">이름</th>
				<th scope="col">성별</th>
				<th scope="col">연락처</th>
				<th scope="col">나이</th>
				<th scope="col">포지션</th>
               
            </tr>
			<% if(teamMemberArr.isEmpty()){ %>
								<tr>
									<td colspan="5">존재하는용병이없습니다</td>
								</tr>
								<%} else { %>
									
								<%		for(int i = 0; i <teamMemberArr.size(); i++ ) {%>
								<tr>
									<td><%= teamMemberArr.get(i).getName() %></td>
									<td><%= teamMemberArr.get(i).getGender() %></td>
									<td><%= teamMemberArr.get(i).getPhone() %></td>
									<td><%= teamMemberArr.get(i).getAge()%></td>
									<td><%= teamMemberArr.get(i).getPosition() %></td>
								</tr>
								<%		}%>
								<%}%>
        </table> --%>

<%@ include file="/views/common/footer.jsp" %>
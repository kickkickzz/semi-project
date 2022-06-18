<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List, java.util.ArrayList,com.team.model.vo.*,com.member.model.vo.* " %>
<%
	ArrayList<TeamMemberInfo> teamMemberArr=(ArrayList<TeamMemberInfo>)request.getAttribute("teamMemberArr");
	Team teamInfo = (Team)request.getAttribute("teamInfo");
	String searchType=request.getParameter("searchType");
	String keyword=request.getParameter("searchKeyword");
	
%>

	<br><br><br><br><br><br><br>
	<div class="media-left">
							<input type="hidden" size="50" name="team_leader"
								id="team_leader" value="<%= teamInfo.getTeam_leader()%>">
							<h2><%= teamInfo.getTeam_name()%></h2>
							<a href="#"> 
							<img src="./resources/storage/<%= teamInfo.getTeam_leader()%>/team_img/<%= teamInfo.getTeam_mark_img()%>"
								width="250px" height="200px">
							</a>
						</div>
	<table class="table table-striped" style="table-layout: fixed;">
								<tr>
									<th>지역</th>
									<td><%= teamInfo.getTeam_region()%></td>
								</tr>
								<tr>
									<th>성별</th>
									<td><%= teamInfo.getTeam_gender()%></td>
								</tr>
								<tr>
									<th>연령대</th>
									<td><%= teamInfo.getTeam_age()%></td>
								</tr>
								<tr>
									<th>팀별점</th>
									<td>
										<%for (int i = 0; i < teamInfo.getTeam_point(); i++) { %><span class="fa fa-star checked"></span> <%}%>
                            			<%for (int i = 0; i < 5-teamInfo.getTeam_point(); i++) { %><span class="fa fa-star"></span> <% }%></h1>
									</td>
								</tr>

							</table>
	
								
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
									
								<%		for(TeamMemberInfo t:teamMemberArr ) {%>
								<tr>
									<td><%= t.getName() %></td>
									<td><%= t.getGender() %></td>
									<td><%= t.getPhone() %></td>
									<td><%= t.getAge()%></td>
									<td><%= t.getPosition() %></td>
								</tr>
								<%		}%>
								<%}%>
        </table> 
         <div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>

<%@ include file="/views/common/footer.jsp" %>
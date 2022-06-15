<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List,com.team.model.vo.Team " %>
<%
	List<Team> list=(List<Team>)request.getAttribute("teamArr");
	String pagebar = (String)request.getAttribute("pageBar");
%>
 <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/team_main.css" rel="stylesheet" />
         <section class="pt-4">
            <div class="container px-lg-5">
                <!-- Page Features-->
                <div class="row gx-lg-5">
                    <div class="col-lg-6 col-xxl-4 mb-5">
                        <div class="card bg-light border-0 h-100">
                            <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                               <%if(list.isEmpty()){ %>
                              		<h2>내용없다.</h2>
                               	<%}else{
									for(Team t : list){%>
                                 <div class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">사진</div>
                                <h2 class="fs-4 fw-bold">t.getTeamName</h2>
                                <p class="mb-0">t.getTeamGender</p>
                                <%} 
                                }%>
                            </div>
                        </div>
                    </div>
                   
                </div>
            </div>
        </section>
        
        <script src="/scripts.js"></script>

<%@ include file="/views/common/footer.jsp" %>
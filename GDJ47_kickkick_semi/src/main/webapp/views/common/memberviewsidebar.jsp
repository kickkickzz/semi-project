<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <style>
	  #sidebar{
	        border : 1px solid black;
	        border-collapse: collapse;
	        list-style-type : none;
	        background-color : #ccc;
	        /* width : 25%; */
	        padding : 0;
	        margin : 0;
	        /*position : fixed;*/
	        height : 50%;
	        overflow : auto;
	        margin-top : 10%;
	    }
	  td{
	        border : 1px solid black;
	    }
	  td a{
	  	text-decoration : none;
	  	display : block;
	  	color : #000;
	  	padding : 8px 15px 8px 15px;
	  	font-weight : bold;
	  }
	  td a.job{
	  
	  }
	  td a:hover:not(.job){
	  	background-color : #333;
	  	color : #fff;
	  }
	  .side{
	  display : flex;
	  margin-left : 25%;
	  
	  }
	  .main{
	  	margin-left : 8%;
	  	margin-top : 10%;
	  	margin-bottom : 3%;
	  }
	  .asd{
	  		position:absolute;
            top:200px;
            left:700px;
      }
      hr{
      		position:absolute;
            top : 240px;
            left:700px;
      }
  </style>
<section class="side">
 <table id="sidebar">
        <tr>
            <td><a href="<%=request.getContextPath()%>/member/memberView.do">회원정보수정</a></td>
        </tr>
        <tr>
            <td><a href="<%=request.getContextPath()%>/member/playlist.do">신청내역</a></td>
        </tr>
        <tr>
            <td><a href="<%=request.getContextPath()%>/member/myteam.do">나의 팀</a></td>
        </tr>
        <tr>
            <td><a href="">회원탈퇴</a></td>
        </tr>
        <tr>
            <td><a href="">로그아웃</a></td>
        </tr>
  </table>
  <!--각 기능마다 밑에 </section> 써야함  -->
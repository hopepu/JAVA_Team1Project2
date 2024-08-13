<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
 table#menu{
 width : 100%;
 height:50px;
 border : 0px solid;
 border-collapse: collapse;
 table-layout: auto;
 vertical-align: middle;
 background-color: #EAEAEA;
 }
</style>
<table id="menu">
	<tr>
	<td width = 70% align="left"><h2>[로고] 오늘의 TV SHOW</h2></td>
	<td width = 10% align="center">
			<!--로그인 상태를 보고 회원가입 | 마이페이지 글자 출력  --> 
			<%
			if(session.getAttribute("UserId")!=null){//로그인한 상태면 ->회원가입%>
				<a href = "../LoginService/MyPage.jsp">마이페이지</a>
			<% }else{//로그아웃한 상태면->로그인%>
				<a href = "../LoginService/Register.jsp">회원가입</a>
			<% }%>
		</td>
		<td width = 10% align="center">
			<!--로그인 상태를 보고 로그인 | 로그아웃 글자 출력  --> 
			<%
			if(session.getAttribute("UserId")!=null){//로그인한 상태면 ->로그아웃%>
				<a href = "../LoginService/Logout.jsp">로그아웃</a>
			<% }else{//로그아웃한 상태면->로그인%>
				<a href = "../LoginService/LoginMain.jsp">로그인</a>
			<% }%>
		</td>
		<td  width = 10% align="center">
		<a href="../01Board/List.jsp">게시판</a></td>
	</tr>




</table>
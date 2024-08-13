<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	//String findID = CookieManager.readCookie(request, "FindID");
	String findID = request.getAttribute("FindID").toString();
	
	System.out.println("넘어온 ID:"+findID);
%>	
		<form>
			<h1 class="h3 mb-3 fw-normal">아이디 찾기 결과</h1>

			<div class="alert alert-info" role="alert">회원님의 아이디 : <%=findID %></div>
			
			<hr class="my-4">
			<div class="btn-group" role="group" aria-label="Default button group">
				<button type="button" class="btn btn-outline-primary" type="button"
					onclick="location.href='../find_pw.jsp'">비밀번호 찾기</button>
				<button type="button" class="btn btn-outline-primary" type="button"
					onclick="location.href='../login.jsp'">로그인</button>
			</div>
		</form>
	<script src="./resources/js/bootstrap.bundle.min.js"></script>

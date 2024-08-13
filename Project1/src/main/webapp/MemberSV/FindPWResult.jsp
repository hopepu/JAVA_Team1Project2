<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String tempPW = request.getAttribute("TempPW").toString();
	
	System.out.println("임시 pw:"+tempPW);
%>	
<!DOCTYPE html>
		<form>
			<h1 class="h3 mb-3 fw-normal">패스워드 찾기 결과</h1>

			<div class="alert alert-info" role="alert">회원님의 임시 비밀번호 : <%=tempPW %></div>
			<p class="lead">로그인 후 [마이페이지]-[회원정보변경]에서 비밀번호를 변경해 주세요.</p>
			<hr class="my-4">
			<div class="btn-group" role="group" aria-label="Default button group">
				<button type="button" class="btn btn-outline-primary" type="button"
					onclick="location.href='/Project1/login.jsp'">로그인</button>
			</div>
		</form>

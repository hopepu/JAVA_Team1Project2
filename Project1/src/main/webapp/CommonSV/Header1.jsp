<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container px-4 px-lg-5">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="/Project1/index.jsp"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
				<svg width="22" height="22" fill="currentColor"
					class="bi bi-house-fill" viewBox="0 0 16 16">
  			<path
						d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z" />
  			<path
						d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z" />
		</svg> <span class="fs-4">Home</span>
			</a>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<%
				if (session.getAttribute("UserId") == null) {
				%>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/Project1/login.jsp">로그인</a></li>

				<li class="nav-item"><a class="nav-link" href="/Project1/register.jsp">회원가입</a></li>
				<li class="nav-item dropdown">
					<button class="nav-link dropdown-toggle" id="navbarDropdown"
						href="#" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">회원정보찾기</button>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="/Project1/find_id.jsp"> 아이디찾기 </a></li>
						<li><a class="dropdown-item" href="/Project1/find_pw.jsp"> 비밀번호찾기 </a></li>
					</ul> <%
 } else {
 %>
				
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/Project1/MemberSV/Logout.jsp">로그아웃</a></li>
				<li class="nav-item"><a class="nav-link" href="/Project1/modify.jsp">나의 정보보기</a></li>
				<li class="nav-item"><a class="nav-link" href="/Project1/withDraw.jsp">회원탈퇴</a></li>

				<%
				}
				%>
			</ul>

		</div>
	</div>
</nav>
<%@page import="DTO.ShowDTO"%>
<%@page import="DAO.ShowDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ShowDAO dao = new ShowDAO(application);
Map<String, Object> view = new HashMap<String, Object>();

String title = request.getParameter("title");
view.put(title, "title");
session.setAttribute("field", "rtitle");
session.setAttribute("data", title);
ShowDTO dto = dao.selectView(title);

String[] platform = dto.getPlatform().split("\r\n");

dao.close();
%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<div class="container">
		<div class>
			<div class>
				<table width=90%" align="center">
					
					<!-- Post content-->

						<tr>
							<td>
								<!-- Post header--> <header class>
									<!-- Post title-->
									<h1 class="fw-bolder mb-1"><%=dto.getTitle()%></h1>
									<!-- Post meta content-->
									<div class="text-muted fst-italic mb-2"><%=dto.getInfo_day()%></div>
									<!-- Post categories-->
									<%
									if (dto.getPlatform().contains("Netflix")) {
									%>
									<span class="badge text-bg-danger rounded-pill">Netflix</span>

									<%
									}
									if (dto.getPlatform().contains("Tving")) {
									%>
									<span class="badge text-bg-dark rounded-pill">Tving</span>
									<%
									}
									if (dto.getPlatform().contains("Disney+")) {
									%>
									<span class="badge text-bg-primary rounded-pill">Disney+</span>
									<%
									}
									%>

								</header> <!-- Preview image figure-->
								<figure class="mb-4">
									<img class="img-fluid rounded"
										src="resources/image/<%=dto.getInfo_jpg()%>.jpg"
										alt="상세정보 메인사진" />
								</figure>
							</td>
							<td>
								<!-- Post content-->
								<section class="mb-5">
									<h2 class="fw-bolder mb-4 mt-5">정보</h2>
									<h6 class="fs-5 mb-4">
										제작 :
										<%=dto.getDirector()%></h6>
									<h6 class="fs-5 mb-4">
										배우 :
										<%=dto.getActor()%></h6>
									<p class="fs-5 mb-4"><%=dto.getContents()%></p>

								</section>
							</td>
						</tr>
						</table>
						<%
						if (session.getAttribute("UserName") != null) {
						%>
						<!-- Comments section-->
						<jsp:include page="ReviewWrite.jsp" />
						<jsp:include page="reviewSV.jsp" />

						<%} else {%>
						<div class="card bg-light">
							<div class="card-body" align="center">
								<h2>다른 사람들과 이 영화에 대해서 의견을 나누고 싶으면 로그인해주세요!</h2>
								<jsp:include page="../MemberSV/LoginForm.jsp" />
							</div>
						</div>
						<%
						}
						%>
					
					
			</div>
		</div>
	</div>

</body>
</html>

<%@page import="java.util.List"%>
<%@page import="DAO.ReviewDAO"%>
<%@page import="DTO.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
ReviewDAO dao = new ReviewDAO(application);//1단계, 2단계 완료
String field = (String) session.getAttribute("field");
System.out.println(field);
String data = (String) session.getAttribute("data");
System.out.println(data);
//1단계
int totalCount = dao.selectCount(field, data); // 검색 조건을 파라미터로 dao로 넘어가고 게시물 수를 int로 받음.
// 전체 페이지 수 계산

List<ReviewDTO> reviewList = dao.reviewList(field, data);
dao.close(); //5단계 종료
%>
    
    
    
<%
 if (reviewList.isEmpty()) {// DAO에서 리스트로 나온 값이 비었을 때
 %>

				<div class="contatiner">
					<h3 align="center">해당하는 정보가 없습니다.</h3>
				</div> <%
 } else { //등록된 게시물이 있으면?

 int virtualNum = 0; // 가상 번호 생성(화면 출력용)
 int countNum = 0; //페이징 처리용 개선
 for (ReviewDTO dto : reviewList) {
	 
	 String starpoint = " ";
		switch(dto.getStarPoint().charAt(0)-48){
		case 1 : 
			starpoint="★☆☆☆☆ 1점";
			break;
		case 2 : 
			starpoint="★★☆☆☆ 2점";
			break;
		case 3 : 
			starpoint="★★★☆☆ 3점";
			break;
		case 4 : 
			starpoint="★★★★☆ 4점";
			break;
		case 5 :
			starpoint="★★★★★ 5점";
			break;
			
		
		}


 	//virtualNum = totalCount-- ; // 게시물의 총 개수 (돌때 - - - - - - 번호가 하나씩 줄어듦)
 	virtualNum = totalCount--;
 %>

						<table border="1" width="90%" align="center">
						<tr>
							<td width="20%">
							<%if(field.equalsIgnoreCase("rtitle")){%>
							<%=dto.getRnickName() %>
							<%} else {%>
							<%=dto.getRtitle()%>
							<%} %>
							
							</td width="50%" >
							<td><%=starpoint %>
							<td align="right" width="30%"><%=dto.getRdate()%></td>
						</tr>
						<tr>
							<td colspan="2"><%=dto.getReview()%></td>
							<td>
							<%
							if(session.getAttribute("UserName")!=null){
							if (session.getAttribute("UserName").toString().equalsIgnoreCase(dto.getRnickName())){
								%>
							<div class="find-btn" align="right">
							 <form action="" method="post">
								<button type="button" onclick="location.href='/Project1/editReview.jsp?rNo=<%=dto.getRno()%>'">수정</button></form> 
							<form action="" method="post">
								<button type="button" onclick="location.href='/Project1/BoardSV/DeleteReview.jsp?rNo=<%=dto.getRno()%>'">삭제</button></form>
							</div>
							<%} }%>
							</td>
							</tr>
							</table>

			
				<%
 } //for문 종료

 } //if문 종료
 %>

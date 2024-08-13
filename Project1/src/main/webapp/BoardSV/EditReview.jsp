<%@page import="java.sql.Date"%>
<%@page import="DTO.ReviewDTO"%>
<%@page import="DAO.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String rNo = request.getParameter("rNo");
	System.out.println(rNo);
	ReviewDAO reviewDAO = new ReviewDAO(application); 
	ReviewDTO dto = reviewDAO.reviewListD(rNo);	// 객체로 내용을 가져와~
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
		starpoint="현재 ★★★★★ 5점";
		break;
		
	
	}
	
	
	
	
	/* String sessionnickName = session.getAttribute("nickName").toString();
	if(!sessionnickName.equals(reviewDTO.getRnickName())) {
		JSFunction.alertBack("작성자 본인만 수정 가능합니다.", out); //경고창 + 뒤 로 감.
		return;
		
	} */
	reviewDAO.close(); 
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EditReview.jsp : 리뷰 수정 페이지</title>

<script type="text/javascript">
function validateForm(form) { //폼 내용 검증
		if(form.starPoint.value == "") {
    		alert("별점을 입력하세요.");
    		form.starPoint.focus();
    		return false;
    	}
    	
    	if(form.content.value == "") {
    		alert("내용을 입력하세요.");
    		form.content.focus();
    		return false;
    	}
		
	}
</script>

</head> 
<body>
<form name="EditFrm" method="post" action="/Project1/BoardSV/EditProcess.jsp" onsubmit="return validateForm(this); ">
<span type="date" name="rdate"><%=dto.getRdate() %></span>
<input type="hidden" name="rNo" value="<%=dto.getRno()%>"></input>
<table border="1" width="90%">

		<tr>
			<td> 영화 </td>
				<td>
					<span type="text" name="title" style="width: 90%;" value=""><%=dto.getRtitle()%></span>
				</td>
				
		</tr>
		<tr>
			<td> 내용 </td>
			<td>
				<textarea name="review" style="width: 90%; height: 100px;"><%=dto.getReview() %></textarea>
			<!-- 100PX과 boardDTO 사이에 여백이 있으면 인식이 되기 때문에 웹페이지에서 가운데로 표시 되므로 여백을 제거해야 작성된 내용이 왼쪽으로 나타난다. -->
			</td>
		</tr>
			<td> 별점 </td>
			<td>
				<select name="starPoint">
					<option value="<%=dto.getStarPoint()%>"><%=starpoint%></option>
					<option value="5">★★★★★ 5점</option>
					<option value="4">★★★★☆ 4점</option>
					<option value="3">★★★☆☆ 3점</option>
					<option value="2">★★☆☆☆ 2점</option>
					<option value="1">★☆☆☆☆ 1점</option>				
				</select>
			</td>			
		<tr>
			<td colspan="2" align="center">
				<button type="submit">수정 완료</button>			
				<button type="reset">다시 수정</button>
				<button type="button" onclick="location.href='/Project1/index.jsp';">목록 보기</button>
			</td>
		</tr>				
	</table>
	</form>
</body>
</html>
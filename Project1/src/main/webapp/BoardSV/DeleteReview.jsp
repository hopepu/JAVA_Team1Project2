<%@page import="Utils.JSFunction"%>
<%@page import="java.sql.Date"%>
<%@page import="DTO.ReviewDTO"%>
<%@page import="DAO.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String rNo = request.getParameter("rNo");
	System.out.println(rNo);
	ReviewDAO reviewDAO = new ReviewDAO(application); 
	int result = reviewDAO.rdelete(rNo);	// 객체로 내용을 가져와~
	
	if(result == 1) {//성공
		JSFunction.alertLocation("삭제 성공", "/Project1/view.jsp?title="+session.getAttribute("data").toString(), out);
				
	}else {//실패
		JSFunction.alertBack("삭제 실패", out); // 메세지 출력 후 뒤로 가기
	
	}
	reviewDAO.close();
	%>
	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
</title>
<body>
</body>
</html>

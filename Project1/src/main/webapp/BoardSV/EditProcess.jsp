<%@page import="Utils.JSFunction"%>
<%@page import="DAO.ReviewDAO"%>
<%@page import="DTO.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// 수정할 값 = 리뷰, 별점/ 쪼껀 = 닉네임
	request.setCharacterEncoding("utf-8");
	
	String review = request.getParameter("review");
	String starpoint = request.getParameter("starPoint");
	System.out.println(request.getParameter("rNo"));
	System.out.println(request.getParameter("starPoint"));
	
	int rNo = Integer.parseInt(request.getParameter("rNo")); 
	
	
	
	ReviewDTO reviewDTO = new ReviewDTO();
	reviewDTO.setReview(review);
	reviewDTO.setStarPoint(starpoint);
	reviewDTO.setRno(rNo);
	
	reviewDTO.toString();
	
	ReviewDAO reviewDAO = new ReviewDAO(application);//1단계 2단계
	int affected = reviewDAO.editReview(reviewDTO);//3단계 4단계
	
	reviewDAO.close(); // 5단계
	
	
	
	if(affected == 1) {//성공
		JSFunction.alertLocation("수정 성공", "/Project1/view.jsp?title="+session.getAttribute("data").toString(), out);
				
	}else {//실패
		JSFunction.alertBack("수정 실패", out); // 메세지 출력 후 뒤로 가기
	
	}

	
%>
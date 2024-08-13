<%@page import="Utils.JSFunction"%>
<%@page import="DAO.ReviewDAO"%>
<%@page import="DTO.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String review = request.getParameter("review");
String starpoint = request.getParameter("starPoint");
String rtitle = session.getAttribute("data").toString();
String nickname = session.getAttribute("UserName").toString();
System.out.println(review);
System.out.println(starpoint);
System.out.println(rtitle);
System.out.println(nickname);

ReviewDTO reviewDTO = new ReviewDTO();
reviewDTO.setReview(review);
reviewDTO.setStarPoint(starpoint);
reviewDTO.setRtitle(rtitle);
reviewDTO.setRnickName(nickname);
reviewDTO.toString();

ReviewDAO reviewDAO = new ReviewDAO(application);//1단계 2단계
int affected = reviewDAO.rwriter(reviewDTO);//3단계 4단계

reviewDAO.close(); // 5단계

if (affected == 1) {//성공
	JSFunction.alertLocation("입력 성공", "/Project1/index.jsp", out);

} else {//실패
	JSFunction.alertBack("입력 실패", out); // 메세지 출력 후 뒤로 가기

}
%>
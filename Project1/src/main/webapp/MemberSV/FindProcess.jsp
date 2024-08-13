<%@page import="DAO.MemberKDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@page import="Utils.Automatic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//1. findID에서 넘어온 정보 받기 & 변환
String user_name = request.getParameter("user_name");
String birth = request.getParameter("birth");
//String birth_month = request.getParameter("monthval");
//String birth_day = request.getParameter("dayval");
String user_birth = Automatic.birthChange(birth);
String sex = request.getParameter("sex");
String pno_front = request.getParameter("pno_front");
String pno_mid = request.getParameter("pno_mid");
String pno_end = request.getParameter("pno_end");
String user_phone = Automatic.phoneJoin(pno_front, pno_mid, pno_end);

System.out.println("user_name:"+user_name);
System.out.println("user_birth:"+user_birth);
System.out.println("sex:"+sex);
System.out.println("user_phone:"+user_phone);

//2. dao넘기기 위한 dto세팅
MemberDTO findMember = new MemberDTO();
findMember.setName(user_name);
findMember.setBirth(user_birth);
findMember.setpNo(user_phone);
//3. db연결-dbpool사용
MemberKDAO memberKDAO = new MemberKDAO();
findMember = memberKDAO.searchID(findMember);
memberKDAO.close();
//4. 받은 결과로 응답
if(findMember.isUsability()){ //id검색 성공
	request.setAttribute("FindID", findMember.getId());
	request.getRequestDispatcher("../FindIDResult.jsp").forward(request, response); //받은 값을 가지고 간다.
	
    System.out.println("찾은 ID:"+findMember.getId());
	//CookieManager.makeCookie(response, "FindID", findMember.getId(), 60*60); //1시간짜리 쿠키 만들어서 저장해줌
}else{//검색 실패
	request.setAttribute("errorMSG", "회원정보를 찾을 수 없습니다.");
	request.getRequestDispatcher("FindID.jsp").forward(request, response); //에러메시지를 가지고 돌아간다.
}
%>
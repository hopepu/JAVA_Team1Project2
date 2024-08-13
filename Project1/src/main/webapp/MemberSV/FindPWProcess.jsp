<%@page import="DAO.MemberKDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@page import="Utils.Automatic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//1. findID에서 넘어온 정보 받기 & 변환
String user_id = request.getParameter("user_id");
String birth = request.getParameter("birth");

String user_birth = Automatic.birthChange(birth);
String sex = request.getParameter("sex");
String pno_front = request.getParameter("pno_front");
String pno_mid = request.getParameter("pno_mid");
String pno_end = request.getParameter("pno_end");
String user_phone = Automatic.phoneJoin(pno_front, pno_mid, pno_end);

//System.out.println("user_id:"+user_id);
//System.out.println("user_birth:"+user_birth);
//System.out.println("sex:"+sex);
//System.out.println("user_phone:"+user_phone);

//2. dao넘기기 위한 dto세팅
MemberDTO findMember = new MemberDTO();
findMember.setId(user_id);
findMember.setBirth(user_birth);
findMember.setpNo(user_phone);
//3. db연결-dbpool사용
MemberKDAO memberKDAO = new MemberKDAO();
int result = memberKDAO.searchPW(findMember);

//4. 받은 결과로 응답
if(result==1){ //회원검증 완료 - 임시비번 제공
	String tempPW = Integer.toString((int) (Math.random() * 1000000) + 1); // 임의의 6자리 숫자를 임시 pw로 받기
	findMember.setPw(tempPW);
	memberKDAO.inserttempPW(findMember);
	request.setAttribute("TempPW", findMember.getPw());
	request.getRequestDispatcher("../FindPWResult.jsp").forward(request, response); //받은 값을 가지고 간다.
	memberKDAO.close();
    System.out.println("찾은 ID:"+findMember.getId());
	//CookieManager.makeCookie(response, "FindID", findMember.getId(), 60*60); //1시간짜리 쿠키 만들어서 저장해줌
}else{//검색 실패
	request.setAttribute("errorMSG", "회원정보를 찾을 수 없습니다.");
	request.getRequestDispatcher("FindPW.jsp").forward(request, response); //에러메시지를 가지고 돌아간다.
}
%>
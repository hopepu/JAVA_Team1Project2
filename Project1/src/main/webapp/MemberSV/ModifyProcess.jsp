<%@page import="Utils.JSFunction"%>
<%@page import="DAO.MemberDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>post java로 값 입력
    <%
    String nickname = request.getParameter("memberNickName");
    System.out.println(nickname);
    String pw = request.getParameter("memberPw");
    System.out.println(pw);
    String name = request.getParameter("memberName");
    System.out.println(name);
    String pno = request.getParameter("memberPno");
    System.out.println(pno);
    String mail = request.getParameter("memberMail");
    System.out.println(mail);
    
    MemberDTO DTO = new MemberDTO();
    DTO.setNickName(nickname);
    DTO.setName(name);
    DTO.setpNo(pno);
    DTO.setPw(pw);
    DTO.setMail(mail);
    DTO.setId(session.getAttribute("UserId").toString());
    
    MemberDAO DAO = new MemberDAO(application);
    int result = DAO.modifyMember(DTO);
    
    DAO.close();
   
    if(result == 1) {//성공
		JSFunction.alertLocation("수정 성공", "/Project1/index.jsp", out);
				
	}else {//실패
		JSFunction.alertBack("수정 실패", out); // 메세지 출력 후 뒤로 가기
	
	}
    
 %>
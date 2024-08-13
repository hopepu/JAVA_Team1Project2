<%@page import="Utils.JSFunction"%>
<%@page import="DAO.MemberDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    MemberDTO dto = new MemberDTO();
    dto.setId(request.getParameter("memberId"));
    dto.setPw(request.getParameter("memberPw"));
    dto.setName(request.getParameter("memberName"));
    dto.setNickName(request.getParameter("memberNickName"));
    dto.setBirth(request.getParameter("memberBirth"));
    dto.setSex(request.getParameter("memberSex"));
    dto.setMail(request.getParameter("memberEmail"));
    dto.setpNo(request.getParameter("memberPhoneNumber"));
    System.out.println(dto.toString());
    
    MemberDAO dao=new MemberDAO();
    int result = dao.register(dto);
    
    dao.close();
    if(result == 1){
    	JSFunction.alertLocation("가입에 성공하였습니다. 로그인해주세요.", "/Project1/login.jsp", out);
    } else {
    	JSFunction.alertLocation("가입에 실패하였습니다. 관리자에게 문의해주세요.", "/Project1/index.jsp", out);
    }


    %>

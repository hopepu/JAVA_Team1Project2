<%@page import="Utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("UserId") == null) {
		String msg="로그인 후 이용해 주세요.";
		String url="../LoginService/LoginMain.jsp";
		JSFunction.alertLocation(msg, url, out);
		return;		
}
%>

<%@page import="Utils.JSFunction"%>
<%@page import="DTO.MemberDTO"%>
<%@page import="DAO.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginProcess.jsp : form으로 받은 request값 처리 </title>
</head>
<body>
	<!-- dto와 dao를 이용하여 로그인 처리 -->
	<% /* LoginForm에서 넘오는 id, pw */
		String userId = request.getParameter("user_id");
		System.out.println(userId);	
		String userPwd = request.getParameter("user_pw");
		System.out.println(userPwd);	
		// 폼에서 넘어온 데이터를 변수로 넣음
		
		MemberDAO memberDAO =  new MemberDAO(application);
		MemberDTO memberDTO = memberDAO.memLogin(userId, userPwd); //3단계 4단계
		memberDAO.close();  // 5단계
		//id, pw를 넣고 객체를 받음. -> 성공 dto, 실패 null
		
		// 성공시 세션 -> 실패는 돌아감
		
		if(memberDTO.getId() != null){
			// db에 정보가 있음
			session.setAttribute("UserId", memberDTO.getId());     // 세션에 id 넣음
			session.setAttribute("UserName", memberDTO.getNickName()); // 세션에 nickName 넣음
			// 암호를 가지고 있으면 해킹 위험있다.
	//		session.setAttribute("LastTime", session.getLastAccessedTime());
			// 돌아가야 함
			JSFunction.alertLocation(memberDTO.getId()+"님 환영합니다!", "/Project1/index.jsp" , out);
			/* response.sendRedirect("../index.jsp"); */
		}else{
			JSFunction.alertLocation("id나 pw를 확인해주세요", "/Project1/login.jsp" , out);
			/* // db에 정보가 없다. -> 로그인 실패
			request.setAttribute("LoginErrMsg", "id나 pw를 확인해주세요");
			// 정보를 가지고 홈으로 돌아감
			request.getRequestDispatcher("/Project1/login.jsp").forward(request, response); */
		}
	
	%>
	
	
	
</body>
</html>
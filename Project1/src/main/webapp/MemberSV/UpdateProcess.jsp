<%@page import="Utils.JSFunction"%>
<%@page import="DAO.MemberKDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@page import="Utils.Automatic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%
	String id = request.getParameter("user_id"); //고정값
	String pw = request.getParameter("user_pw");
	String name = request.getParameter("user_name"); //고정값
	String nickName = request.getParameter("user_nick");
	String birth = request.getParameter("birth");	//고정값

	String sex = request.getParameter("sex"); //고정값
	String pno_front = request.getParameter("pno_front");
	String pno_mid = request.getParameter("pno_mid");
	String pno_end = request.getParameter("pno_end");
	String upphone = Automatic.phoneJoin(pno_front, pno_mid, pno_end);
	String mail_front = request.getParameter("mail_front");
	String mail_end = request.getParameter("mail_end");
	String upMail= Automatic.mailJoin(mail_front, mail_end);
	
	
	System.out.println("birth:"+birth);
	System.out.println("upphone:"+upphone);
	System.out.println("upMail:"+upMail);
	
	
	//2. dao넘기기 위한 dto세팅
	MemberDTO updateMem = new MemberDTO();
	updateMem.setId(id);	
	updateMem.setName(name); 
	updateMem.setBirth(birth);//회원확인용 정보
	//변경정보 세팅
	updateMem.setPw(pw);
	updateMem.setNickName(nickName);
	updateMem.setpNo(upphone);
	updateMem.setMail(upMail);
	
	//3. dao에서 정보변경처리
	MemberKDAO dao = new MemberKDAO(); //1,2단계
	int result = dao.updateMem(updateMem);
	out.print(result);
	dao.close(); //5단계
	
	//3. 가입 성공/실패처리
	if(result==1){//성공
		String msg = "정보변경이 완료되었습니다. 변경하신 정보로 다시 로그인해주세요.";
		JSFunction.alertBack(msg, out);
	}else{//실패
		JSFunction.alertBack("정보변경이 실패했습니다. 관리자에게 문의하세요.", out);
	}
%>    

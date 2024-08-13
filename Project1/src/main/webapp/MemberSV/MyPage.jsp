<%@page import="Utils.Automatic"%>
<%@page import="DAO.MemberKDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@include file="./Common/IsLoginCheck.jsp" %>  --%>
<%
 	//1. 세션에서 회원정보 받기
 	String user_ID = session.getAttribute("UserId").toString();
	String user_nick=session.getAttribute("UserName").toString();

 	MemberDTO nowMem = new MemberDTO(); //받은 정보 넣을 객체
 	nowMem.setId(user_ID);
 	nowMem.setNickName(user_nick);
 	
 	//2. DB에서 정보 가져오기
 	MemberKDAO kdao = new MemberKDAO();
 	nowMem = kdao.searchMem(nowMem);
 	String[] phones = Automatic.phoneDiv(nowMem);
 	
 	String[] mails = Automatic.mailDiv(nowMem);
 	String birth = Automatic.birthReChange(nowMem.getBirth()).substring(0, 11);
 	
 	String sex = nowMem.getSex();
 	int decSex = (sex.equals("남"))? 1:2; //남-1, 여-2 번호 부여
%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<%@include file="../CommonSV/Font.jsp"%>
<script src="./resources/js/color-modes.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<script src="./resources/js/checkout.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>MyPage</title>

<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/checkout.css" rel="stylesheet">
<link href="./resources/css/font.css" rel="stylesheet">
</head>
<body>
	<%@ include file="../CommonSV/ColorMode.jsp"%>

<div class="container">
		<main>
			<div class="py-5 text-center">
				<img class="d-block mx-auto mb-4" src="./resources/icons/logo.svg" width="72" height="57">
				<h2>회원정보 관리</h2>
			</div>
			<div class="row">

				<div class="col-12">
					<h4 class="mb-3">회원정보 입력</h4>
					<form class="needs-validation" action="/Project1/MemberSV/UpdateProcess.jsp" method="post" onsubmit="return valform(this);">
						<div class="row g-3">
							<div class="col-2">
								<label for="id" class="form-label">아이디</label>
							</div>
							<div class="col-10">
								<input type="text" class="form-control" name="user_id"
									id="user_id" value="<%=nowMem.getId()%>" readonly>
								<div class="invalid-feedback">중복검사 필요</div>
							</div>

							<div class="col-2">
								<label for="password" class="form-label">패스워드</label>
							</div>
							<div class="col-10">
								<input type="password" class="form-control" name="user_pw"
									id="user_pw" placeholder="" value="<%=nowMem.getPw()%>" required>
								<div class="invalid-feedback"></div>
							</div>

							<div class="col-2">
								<label for="name" class="form-label">이름</label>
							</div>
							<div class="col-10">
								<input type="text" class="form-control" name="user_name"
									id="user_name" placeholder="" value="<%=nowMem.getName()%>" readonly>
								<div class="invalid-feedback"></div>
							</div>

							<div class="col-2">
								<label for="nickName" class="form-label">닉네임</label>
							</div>
							<div class="col-10">
								<input type="text" class="form-control" name="user_nick"
									id="user_nick" placeholder="" value="<%=nowMem.getNickName()%>" required>
								<div class="invalid-feedback"></div>
							</div>
							<!--생년월일  -->
					
						<div class="col-2">
							<label for="birth" class="form-label">생년월일</label>
						</div>

						<div class="col-10">
							<input type="text" class="form-control" name="birth"
								id="birth" value="<%=birth%>" readonly>
							
						</div>			
							<!--성별  -->							
							<div class="col-2">
								<label for="sex" class="form-label">성별</label>
							</div>
							<div class="col-10">
							<%switch(decSex){
							case 1: //남자일때%>
							<input id="man" name="man" type="radio" class="form-check-input" checked><label class="form-check-label" for="man">남</label>								
							<input id="woman" name="woman" type="radio" class="form-check-input" readonly> <label class="form-check-label" for="woman">여</label>
							<%break;						
							case 2: //여자일때%>
							<input id="man" name="man" type="radio" class="form-check-input" readonly><label class="form-check-label" for="man">남</label>								
							<input id="woman" name="woman" type="radio" class="form-check-input" checked> <label class="form-check-label" for="woman">여</label>						
							<%break;								
							}//--switch()
							%>
							</div>

							<!--전화번호  -->
							
							<div class="col-4">
								<label for="phone" class="form-label">휴대폰번호</label></div>
							
							<div class="row gy-3">
								<div class="col-4 ">
								<div class="input-group has-validation">
									<select class="form-select" name="pno_front" id="pno_front" value="<%=phones[0]%>" required>
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option> </select>
									<span class="input-group-text">-</span></div>
									<div class="invalid-feedback"></div>
								</div>
								<div class="col-4 ">
								<div class="input-group has-validation">
									<input type="text" class="form-control" name="pno_mid" id="pno_mid"  value="<%=phones[1]%>" required> <span
										class="input-group-text">-</span></div>
									<div class="invalid-feedback"></div>
								</div>
								<div class="col-4">
									<input type="text" class="form-control" name="pno_end"
										id="pno_end"  value="<%=phones[2]%>" required>
									<div class="invalid-feedback"></div>
								</div>
							</div>

							<!--이메일 -->
							<div class="row gy-3">
							<div class="col-2">
								<label for="mail" class="form-label">email</label>
							</div>
								<div class="col-5">
									<input type="text" class="form-control" name="mail_front"
										id="mail_front"  value="<%=mails[0]%>" required>
								</div>
								<div class="col-5">
									<select class="form-select" name="mail_end" id="mail_end" value="@<%=mails[1]%>" required>
									<option value="@naver.com">@naver.com</option>
									<option value="@gmail.com">@gmail.com</option>
									<option value="@daum.net">@daum.net</option></select>
								</div>
						</div></div>


						<hr class="my-4">

						<button class="w-30 btn btn-primary btn-lg" type="submit">수정하기</button>
						<button class="w-30 btn btn-outline-secondary btn-lg" type="button" onclick="location.href='index.jsp'">취소</button>
						<button class="w-30 btn btn-outline-secondary btn-lg" type="button" onclick="location.href='/Project1/withDraw.jsp'">회원탈퇴</button>
					</form>
				</div>
			</div>
		</main>

		<%-- <%@include file="../CommonSV/Footer.jsp"%> --%>
	</div>

</body>
</html>

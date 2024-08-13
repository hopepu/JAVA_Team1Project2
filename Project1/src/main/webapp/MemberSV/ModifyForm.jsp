<%@page import="DAO.MemberDAO"%>
<%@page import="DTO.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
MemberDTO memberDTO = new MemberDTO();
MemberDAO DAO = new MemberDAO(application);
memberDTO = DAO.searchMember(session.getAttribute("UserId").toString(), session.getAttribute("UserName").toString());
DAO.close();
%>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>

<body bgcolor="yellow"; >
	<form method="post" action="MemberSV/ModifyProcess.jsp" onsubmit="return validateForm(this);">
		<table class="container" style="background-color: pink; width: 80vw;">


			<tr>
				<td colspan="1"><label for="memberId">닉&nbsp;네&nbsp;임 </label>
				</td>
				<td colspan="1"><input type="text" class="form-control"
					name="memberNickName" value="<%=memberDTO.getNickName()%>">
					 </input>
					 </td>

			</tr>
			<tr>
				<td><label for="memberPw">비밀번호</label></td>
				<td><input type="password" class="form-control" name="memberPw"
					placeholder="비밀번호를 입력해주세요" value="<%=memberDTO.getPw()%>"  > <span
					class="pwchk1"></span></td>
				<td><label for="memberPwCheck">비밀번호 확인</label></td>
				<td><input type="password" class="form-control" name="memberPw"
					placeholder="비밀번호를 입력해주세요" value="<%=memberDTO.getPw()%>"  > <span
					class="pwchk"></span></td>
			</tr>
			<tr>
				<td><label for="memberName">이 &nbsp;&nbsp;름</label></td>
				<td><input type="text" class="form-control" name="memberName"
					placeholder="" value="<%=memberDTO.getName()%>"  >
					<span class="memberNamechk"></span></td>
			</tr>
			<tr>
				<td><label for="phoneNumber">휴대폰 번호</label></td>
				<td><input type="text" class="form-control" name="memberPno"
					placeholder="" value="<%=memberDTO.getpNo()%>"  ></input> <span
					class="phchk"></span></td>
			</tr>
			<tr>
				<td><label for="email">이메일</label></td>
				<td><input type="text" class="form-control" name="memberMail"
					placeholder="" value="<%=memberDTO.getMail()%>"  ></input> <span
					class="emailchk"></span></td>
			</tr>

				
		</table>
		<button class="btn btn-primary btn-lg btn-block" type="submit">수정 완료</button>
				<button class="btn btn-primary btn-lg btn-block" onclick="location.href='/Project1/index.jsp'">뒤로가기</button>
	</form>



</body>
</html>
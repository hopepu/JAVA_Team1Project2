<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>LoginForm.jsp</title>
<script>
    function validateForm(form) {  /* validateFrom을 호출하면 form(29~34) 정보를 받는다. */
        if (!form.user_id.value) {  /* form안에 user_id안에 값이 !(없으면) */
            alert("아이디를 입력하세요.");   /* 경고창 */
            return false; 			/* false를 리턴 */
        }
        if (form.user_pw.value == "") {		/* form안에 user_pw안에 값이 없으면 */
            alert("패스워드를 입력하세요.");	/* 경고창 */
            return false;		// false를 리턴
        }
    }
    </script>
</head>
<body align="center" style=" border:1px solid black;">
 	
    
    <span style="color: red; font-size: 1.2em;"> 
        <%= request.getAttribute("LoginErrMsg") == null ?  "" : request.getAttribute("LoginErrMsg") %>
        <!-- 조건 : request 영역에 "LoginErrMsg"가 있니? true : false  -->
    </span>  <!-- span 태그는 1줄 글자 출력용  -->
    <%
    if (session.getAttribute("UserId") == null) {  // 로그인 상태 확인 ~34행까지 true
        // 로그아웃 상태
    %><!-- 로그인 스트립트 실행 -->
    
    <div class="container" style="background-color:pink; width:40vw; margin-top:-100px;"  >
    <form action="MemberSV/LoginProcess.jsp" method="post" name="loginFrm"
        onsubmit="return validateForm(this);">    <!-- onsubmit에 return이 true가 나와야 action으로 간다. -->
        <h2 style="margin-top:130px;">로그인 페이지</h2>
        아이디 &nbsp;&nbsp;&nbsp;: <input type="text" name="user_id" /><br />
        패스워드 : <input type="password" name="user_pw" /><br />
        <input type="submit" value="로그인하기" />   	<!-- submit을 눌리면 1순위: onsubmit -> 2순위 :action -->
        <button type="button" onclick="location.href='/Project1/register.jsp'">회원가입</button>
    </form>
    </div>
    <%
    } else { // 로그인된 상태
    %>
        <%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br />
        <a href="Logout.jsp">[로그아웃]</a>		<!-- 세션을 제거 -->
    <%
    }
    %>
</body>
</html>
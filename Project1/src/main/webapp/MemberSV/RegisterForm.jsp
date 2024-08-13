<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body bgcolor="yellow"; >
   <div class="container" style="background-color:pink; width:40vw; position:absolute; left:30%; top:25%;margin:0 auto;"  >
      <div class="input-form-backgroud row" >
         <div class="input-form col-md-12 mx-auto">
            <h2 class="mb-3" align="center">회원가입</h2>
            <%
            if(session.getAttribute("userId") != null){
               // 로그인 상태
          %>      
          <script>
                                 
            function validateForm(form){ /* validateForm을 호출하여 입력값을 받는다 */
               if(!form.memberId.value){
                  alert("Id를 입력하세요.");
                  form.memberId.focus();
                  return false;
               }   
               if(form.idDuplication.value != "idCheck"){
                  alert("아이디 중복체크를 해주세요");
                  form.memberId.focus();
               }   
               if(!form.memberPw.value){
                  alert("PW를 입력하세요.");
                  form.memberPw.focus();
                  return false;
               }
               if(!form.memberPw2.value){
                  alert("PW를 한 번 더 입력하세요.");
                  form.memberPw2.focus();
                  return false;
               }
               if(!form.memberName.value){
                  alert("이름을 입력하세요.");
                  form.memberName.focus();
                  return false;
               }
               if(!form.memberNickName.value){
                  alert("닉네임을 입력하세요.");
                  form.memberNickName.focus();
                  return false;
               }
               if(!form.memberBirth.value){
                  alert("생년월일을 입력하세요.");
                  form.memberBirth.focus();
                  return false;
               }
               if(!form.memberSex.value){
                  alert("성별을 선택해주세요.");
                  form.memberSex.focus();
                  return false;
               }
               if(!form.memberPhoneNumber.value){
                  alert("전화번호를 입력하세요.");
                  form.memberPhoneNumber.focus();
                  return false;
               }
               if(!form.memberEmail.value){
                  alert("이메일을 입력하세요.");
                  form.memberEmail.focus();
                  return false;
               }
               
            } 
            
          </script>
          
           <%}%>
            
            <form method="post" action="/Project1/MemberSV/RegisterProcess.jsp" name="userInfo"
               onsubmit="return validateForm(this)">
               <div class="row">
                  <div class="col-md-6 mb-3" >
                     <label for="memberId" >아이디 &nbsp;&nbsp;</label>
                     <input type="text"
                         class="form-control" id="memberId" name="memberId" position= "absolute"  right="10%"
                        maxlength="50" placeholder="아이디를 입력해주세요" required onkeyup="checkId()" />
                     <button type="button"  onclick="window.open(Project1/MemberSV/Check.jsp?field=id,data=form.memberId.value)" class="checkId">
                     
                        중복 확인
                     </button>      
                     <!-- 아이디 중복 체크 여부 -->      
                     <input type="hidden" name="idDuplication" value="idUncheck" />
                  </div>
                  
               </div>
               <div class="row">
                  <div class="col-md-6 mb-3">
                     <label for="memberPw">비밀번호</label> 
                     <input type="password"
                        class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호를 입력해주세요"
                        value="" required /> 
                  </div>

                  <div class="col-md-6 mb-3">
                     <label for="memberPwCheck">비밀번호 확인</label> 
                        <input type="password"
                        class="form-control" id="memberPwCheck" name="memberPw2"
                        placeholder="비밀번호를 다시 한번 입력해주세요" value="" required>
                  </div>

               </div>   
               <div>
                  <div class="col-md-6 mb-3">
                     <label for="memberName">이 &nbsp;&nbsp;름</label> <input type="text"
                        class="form-control" id="memberName"  name="memberName"  placeholder="이름을 입력해주세요"
                        value="" required> 
                  </div>
                  <div class="col-md-6 mb-3">
                     <label for="memberName">닉네임 </label> <input type="text"
                        class="form-control" id="memberNickName" name="memberNickName" placeholder="닉네임을 입력해주세요"
                        value="" required> 
                  </div>
               </div>
               <div class="mb-3">
                  <label for="birth">생년월일</label> <input type="text"
                     class="form-control" id="birth" name="memberBirth" placeholder="2000/00/00"
                     required="required"> <span class="birthchk"></span>
               </div>
               <div class="mb-3">
                  <label for="sex">성별
                     <input type="radio" class="form-control" name="memberSex"  value="남" id="man" 
                     required="required">남자
                     <input type="radio" class="form-control" name="memberSex"  value="여" id="woman" 
                     required="required" >여자
                  </label> 
               </div>
               <div class="mb-3">
                  <label for="phoneNumber">휴대폰 번호</label> <input type="text"
                     class="form-control" id="phoneNumber" name="memberPhoneNumber" placeholder="' - ' 포함한 번호를 입력"> 
               </div>
               <div class="mb-3">
                  <label for="email">이메일</label> <input type="text"
                     class="form-control" id="email" name="memberEmail" placeholder="MBC@example.com"
                     required="required"> 
               </div>
               
               

               <button class="btn btn-primary btn-lg btn-block" type="submit" >가입 완료</button>
            </form>
            <br>
            <button class="btn btn-primary btn-lg btn-block"
               onclick="location.href='/Project1/index.jsp'">뒤로가기</button>
            <script type="text/javascript">
   


         </div>
      </div>
      
   </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<div class="container">
		<main>
			<div class="py-5 text-center">
				<h2>아이디 찾기</h2>
				<p class="lead">회원정보를 입력해 주세요.</p>
			</div>
			<c:if test='<%=request.getAttribute("errorMSG") != null%>'
				var="error">
				<div class="alert alert-warning" role="alert">${errorMSG}</div>
			</c:if>

			<div class="row">
			<form class="needs-validation" action="MemberSV/FindProcess.jsp"
					method="post">			

				<div class="col-12">
					<h4 class="mb-3">회원정보 입력</h4>
				</div>

				
					<div class="row gy-3">

						<div class="col-2">
							<label for="name" class="form-label">이름</label>
						</div>
						<div class="col-10">
							<input type="text" class="form-control" name="user_name"
								id="user_name" placeholder="" value="" required>
							<div class="invalid-feedback"></div>
						</div>
					</div>

					<!--생년월일  -->
					<div class="row gy-3">
						<div class="col-2">
							<label for="birth" class="form-label">생년월일</label>
						</div>

						<div class="col-10">
							<input type="date" class="form-control" name="birth"
								id="birth" required>
							<div class="invalid-feedback">
							</div>
						</div>
					</div>
			
			<!--성별  -->
			<div class="row gy-3">
				<div class="col-2">
					<label for="sex" class="form-label">성별</label>
				</div>
				<div class="col-10">

					<input id="man" name="sex" type="radio" class="form-check-input"
						value="남" checked required> <label
						class="form-check-label" for="man">남</label> <input id="woman"
						name="sex" type="radio" class="form-check-input" value="여"
						required> <label class="form-check-label" for="woman">여</label>
				</div>
			</div>

			<!--전화번호  -->
			<div class="row gy-3">
				<div class="col-4">
					<label for="phone" class="form-label">휴대폰번호</label>
				</div>
			</div>
			<div class="row gy-3">
				<div class="col-4 ">
					<div class="input-group has-validation">
						<select class="form-select" name="pno_front" id="pno_front"
							required>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
						</select> <span class="input-group-text">-</span>
					</div>
					<div class="invalid-feedback"></div>
				</div>
				<div class="col-4 ">
					<div class="input-group has-validation">
						<input type="text" class="form-control" name="pno_mid"
							id="pno_mid" placeholder="" value="" required> <span
							class="input-group-text">-</span>
					</div>
					<div class="invalid-feedback"></div>
				</div>
				<div class="col-4">
					<input type="text" class="form-control" name="pno_end" id="pno_end"
						placeholder="" value="" required>
					<div class="invalid-feedback"></div>
				</div>
			</div>
			<hr class="my-4">

			<button class="w-100 btn btn-primary btn-lg" type="submit">아이디
				찾기</button>
			</form>
	</div>
	</main>

	</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<script src="./resources/js/color-modes.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<script src="./resources/js/checkout.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>FindPW</title>

</script>
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/checkout.css" rel="stylesheet">
<link href="./resources/css/font.css" rel="stylesheet">
</head>
<body>
	<%@ include file="../CommonSV/ColorMode.jsp"%>

	<div class="container">
		<main>
			<div class="py-5 text-center">
				<h2>패스워드 찾기</h2>
				<p class="lead">회원정보를 입력해 주세요.</p>
			</div>
			<c:if test='<%=request.getAttribute("errorMSG") != null%>'
				var="error">
				<div class="alert alert-warning" role="alert">${errorMSG}</div>
			</c:if>

			<div class="row">
			<form class="needs-validation" action="MemberSV/FindPWProcess.jsp"
					method="post">			

				<div class="col-12">
					<h4 class="mb-3">회원정보 입력</h4>
				</div>

				
					<div class="row gy-3">

						<div class="col-2">
							<label for="name" class="form-label">아이디</label>
						</div>
						<div class="col-10">
							<input type="text" class="form-control" name="user_id"
								id="user_id" placeholder="" value="" required>
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

			<button class="w-100 btn btn-primary btn-lg" type="submit">패스워드 찾기</button>
			</form>
	</div>
	</main>

	</div>

</body>
</html>

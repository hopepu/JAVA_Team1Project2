<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../CommonSV/Font.jsp"%>
<script src="../resources/js/color-modes.js"></script>
<script src="../resources/js/bootstrap.bundle.min.js"></script>
<script src="../resources/js/checkout.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>FinalCheck</title>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/sign-in.css" rel="stylesheet">
<link href="../resources/css/font.css" rel="stylesheet">
</head>
<body onload="viewSet();">
	<script type="text/javascript">
		function viewSet() {
			const checkView = document.getElementById('check');
			const thanksView = document.getElementById('thanks');
			checkView.style.display = 'block';
			thanksView.style.display = 'none';
		}

		function thanks() {
			const checkView = document.getElementById('check');
			const thanksView = document.getElementById('thanks');
			checkView.style.display = 'none';
			thanksView.style.display = 'block';
		}
	</script>
	<main class="form-signin w-100 m-auto">
		<form action="/Project1/MemberSV/Withrawal.jsp" method="get">
			<div class="card" style="width: 30rem;" id="check" >
				<img src="resources/image/cat_final.jpg" class="card-img-top">
				<div class="card-body">
					<h5 class="card-title">회원탈퇴 확인</h5>
					<p class="card-text">
						정말 탈퇴하시겠습니까?<br>모든 서비스에 대한 권리를 상실하실 수 있습니다.
					</p>
					<button type="submit" class="w-30 btn btn-outline-secondary">예</button>
					<button type="button" class="w-30 btn btn-primary"
						onclick="thanks()">아니요</button>
				</div>
			</div>

			<div class="card" style="width: 30rem;" id="thanks" >
				<img src="resources/image/cat_thanks.jpg" class="card-img-top">
				<div class="card-body">
					<h5 class="card-title">감사합니다.</h5>
					<p class="card-text">다양한 서비스를 계속 누려보세요.</p>
					<button type="button" class="w-30 btn btn-primary"
						onclick="location.href='/Project1/index.jsp'">홈으로 이동</button>
				</div>
			</div>
		</form>
	</main>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 글</title>
</head>
<script type="text/javascript">
function validateForm(form) { //폼 내용 검증
		if(form.starPoint.value == "") {
    		alert("별점을 입력하세요.");
    		form.starPoint.focus();
    		return false;
    	}
    	
    	if(form.content.value == "") {
    		alert("내용을 입력하세요.");
    		form.content.focus();
    		return false;
    	}
		
	}
</script>

<body>
	<form name="writeFrm" method="post" action="/Project1/BoardSV/ReviewWriteProcess.jsp"
		onsubmit="return validateForm(this);">
		<table border="1" width="90%" align="center">
			<tr>
				<td>별점</td>
				<td><select name="starPoint">
						<option value="5">★★★★★ 5점</option>
						<option value="4">★★★★☆ 4점</option>
						<option value="3">★★★☆☆ 3점</option>
						<option value="2">★★☆☆☆ 2점</option>
						<option value="1">★☆☆☆☆ 1점</option>
				</select></td>
				<td colspan="2" align="center">
					<button type="submit">작성 완료</button>
					<button type="reset">다시 입력</button>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="2"><textarea type="input" name="review" style="width: 90%; height: 100px;"></textarea>
				</td>
			</tr>

				
		</table>
	</form>
</body>
</html>

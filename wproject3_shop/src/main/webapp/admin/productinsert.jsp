<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js" ></script>
</head>
<body>
<%@ include file="admin_top.jsp" %><!-- include로 세션을 체크하여 로그인한 경우에만 상품 추가 가능 -->
<form action="productproc.jsp?flag=insert" method="post" enctype="multipart/form-data"><!-- 파일 전달을 위해 enctype 속성 설정 -->
	<table>
		<tr> 
			<td colspan="2">상품 등록</td>
		</tr>
		<tr>
			<td>상품명</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>가 격</td>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<td>설 명</td>
			<td><textarea rows="5" style="width: 99%;" name="detail"></textarea></td>
		</tr>
		<tr>
			<td>재 고</td>
			<td><input type="text" name="stock"></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><input type="file" name="image"></td>
		</tr>
		<tr>
			<td colspan="2">
				<br>
				<input type="submit" value="상품 등록">
				<input type="reset" value="새로 입력">
			</td>
		</tr>
	</table>
</form>
<%@ include file="admin_bottom.jsp" %>
</body>
</html>
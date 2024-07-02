<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 추가</title>
</head>
<body>
<h3>사용자 추가</h3>
<form action="insert.m2" method="post">
	<input type="text" name="userid" placeholder="아이디"><br>
	<input type="text" name="password" placeholder="비밀번호"><br>
	<input type="text" name="name" placeholder="작성자"><br>
	<input type="text" name="email" placeholder="이메일"><br>
	<input type="submit" value="추가">
	<input type="button" value="목록" onclick="location.href='list.m2'">
</form>
</body>
</html>
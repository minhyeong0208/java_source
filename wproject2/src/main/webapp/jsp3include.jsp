<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 지시어</title>
</head>
<body>
<%@ include file="jsp3top.jsp" %> <!-- 해당 파일 소스 전체가 실행됨. -->
	<h1>include 연습</h1>
	include 지시어 : 여러 jsp 파일에서 공통으로 사용할 부분을 별도 파일로 작성 후, 필요할 때 포함해서 쓴다.
	<pre>
여기는 본문
	</pre>
<%@ include file="jsp3bottom.jsp" %>
</body>
</html>
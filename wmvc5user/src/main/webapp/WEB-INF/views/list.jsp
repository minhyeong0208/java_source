<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="logincheck.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 목록</title>
</head>
<body>
<%-- ListController 파일에서 호출  --%>
<h3>사용자 목록</h3>
<a href="insert.jsp">사용자 추가</a><br><%-- 클라이언트를 통해 호출하므로 redirect 방식, views 디렉토리 안(forward인 요소만 넣는다.)에 선언 X --%>
<table border="1">
	<tr>
		<th>아이디</th><th>이름</th><th>이메일</th>
	</tr>
	<c:forEach var="u" items="${list}"> <%-- ListController 16행 참조 --%>
		<tr>
			<td>${u.userid}</td>
			<td><a href="view.m2?userid=${u.userid}">${u.name}</a></td>
			<td>${u.email}</td>
		</tr>
	</c:forEach>
</table>
<br>
<a href="logout.m2">로그아웃</a>
</body>
</html>
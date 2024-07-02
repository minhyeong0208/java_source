<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 목록</title>
</head>
<body>
<h3>부서 목록</h3>
<table border="1">
	<tr>
		<th>부서번호</th><th>부서명</th>
	</tr>
	<c:forEach var="u" items="${list}">
		<tr>
			<td>${u.buser_no}</td>
			<td><a href="view.page?buser_num=${u.buser_no}">${u.buser_name}</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>부서별 관리고객</h3>
<%--
<table border="1">
	<c:forEach var="gogek" items="${list}">
		<tr>
			<td colspan="3">${gogek.jikwon_name}의 고객</td>
		</tr>
		<tr>			
			<th>고객번호</th><th>고객명</th><th>고객전화</th>
		</tr>
		<tr>
			<td>${gogek.gogek_no}</td>
			<td>${gogek.gogek_name}</td>
			<td>${gogek.gogek_tel}</td>
		</tr>
	</c:forEach>
</table>
--%>
<c:forEach var="list" items="${list}">
	${list.buser_name}
</c:forEach>
</body>
</html>
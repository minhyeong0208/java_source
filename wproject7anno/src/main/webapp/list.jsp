<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="processDao" class="pack.business.ProcessDao" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원 정보 목록(MyBatis)</h2>
<a href="ins.jsp">회원 추가</a>
<table border="1">
	<tr>
		<th>ID</th><th>Name</th><th>PW</th><th>DATE</th>
	</tr>
	<% ArrayList<DataDto> list = (ArrayList<DataDto>)processDao.selectDataAll(); %>
	<c:set var="list" value="<%=list %>" /> 
	
	<%-- list가 비어있는 경우 실행 --%>
	<c:if test="${empty list}">
	<tr>
		<td colspan="4">회원정보 없음</td>
	</tr>
	</c:if>
	
	<%-- forEach문을 사용하여 데이터 출력 --%>
	<c:forEach var="m" items="<%=list %>">
		<tr>
			<td><a href="del.jsp?id=${m.id}">${m.id}</a></td>
			<td><a href="up.jsp?id=${m.id}">${m.name}</a></td>
			<td>${m.passwd}</td>
			<td>${fn:substring(m.reg_date,0,10)}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="4" style="color: red;">ID 클릭은 삭제, name 클릭은 수정</td>
	</tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.expensive {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
<h1>제품 목록</h1>
<c:choose>
	<c:when test="${empty products}">
		<p>제품이 없어요.</p>
	</c:when>
	<c:otherwise>
		<table border="1">
			<thead>
				<tr>
					<th>제품명</th><th>가격</th><th>설명</th><th>출시일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pro" items="${products}">
					<tr>
						<td>${pro.name}</td>
						<td class='<c:if test="${pro.price >= 5000.0}">expensive</c:if>'>
							${pro.price}
						</td>
						<td>${pro.description}</td>
						<td>${pro.releaseDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<h2>제품 통계</h2>
<c:set var="totalProducts" value="${fn:length(products)}" /> <%-- length 메소드 사용하여 products의 길이 --%>
<c:set var="totalPrice" value="0" />
<c:forEach var="p" items="${products}" varStatus="status">
	<c:set var="totalPrice" value="${totalPrice + p.price}" />
</c:forEach>
<p>전체 건수 : ${totalProducts}</p>
<p>가격 평균 : <fmt:formatNumber value="${totalPrice/totalProducts}" type="currency"/> </p>

<h3>제품 설명</h3>
<ul>
	<c:forEach var="p" items="${products}">
		<li><c:out value="${fn:substring(p.description, 0, 5)}..."></c:out></li><%-- substring 사용 --%>
	</c:forEach>
</ul>


</body>
</html>
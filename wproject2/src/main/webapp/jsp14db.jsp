<%@page import="pack.SangpumDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//Connection conn = null; // 옛날 방식
%>

<jsp:useBean id="connClass" class="pack.ConnClass"></jsp:useBean>  <!-- 객체 생성 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>* 상품 정보(jsp Beans 사용) *</h2>
<table border='1'>
	<tr>
		<th>코드</th><th>상품명</th><th>수량</th><th>단가</th>
	</tr>
		<%
		ArrayList<SangpumDto> list = connClass.getDataAll();
		for(SangpumDto s : list) {
		%>
		<tr>
			<td><%=s.getCode() %></td>
			<td><%=s.getSang() %></td>
			<td><%=s.getSu() %></td>
			<td><%=s.getDan() %></td>
		</tr>		
		<%
		}
		%>
	
</table>
</body>
</html>


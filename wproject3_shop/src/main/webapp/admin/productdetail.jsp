<%@page import="pack.product.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>

<%
String no = request.getParameter("no");
ProductDto dto = productMgr.getProduct(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
</head>
<body>
	<b>상품 상세 보기</b><p/>
	<%@ include file="admin_top.jsp"%>
	
	<table>
		<tr>
			<td style="width: 20%">
				<img src="../upload/<%=dto.getImage() %>" width="150" />
			</td>
			<td>
				<table style="vertical-align: top;">
					<tr>
						<td>번호 : </td><td><%=dto.getNo() %></td>
					</tr>
					<tr>
						<td>상품명 : </td><td><%=dto.getName() %></td>
					</tr>
					<tr>
						<td>가격 : </td><td><%=dto.getPrice() %></td>
					</tr>
					<tr>
						<td>등록일 : </td><td><%=dto.getSdate() %></td>
					</tr>
					<tr>
						<td>재고량 : </td><td><%=dto.getStock() %></td>
					</tr>
				</table>
			</td>
			<td style="width: 30%">
				<b>상품 설명</b><br>
				<%=dto.getDetail() %>
			</td>
		</tr>
		<tr>
			<td colspan="1" style="text-align: center;">
				<a href="javascript:productUpdate('<%=dto.getNo()%>')">수정하기</a>
				<a href="javascript:productDelete('<%=dto.getNo()%>')">삭제하기</a>
			</td>
		</tr>
	</table>
	
	<%@ include file="admin_bottom.jsp"%>
</body>
</html>
<%@page import="pack.product.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js" ></script>
</head>
<body>
<h1>1st</h1>
<%@ include file="guest_top.jsp" %><!-- 세션이 살아있기 때문에 다음 페이지가 확인이 된다. -->
<table>
	<tr>
		<th>상품명</th><th>가격</th><th>재고량</th><th>상세보기</th>
	</tr>
	<%
	ArrayList<ProductDto> plist = productMgr.getproductAll();  // 전체 상품 읽기
	
	for(ProductDto p : plist) {
	%>
		<tr style="text-align: center">
			<td>
				<img src="../upload/<%=p.getImage() %>" width="120" />
				<%=p.getName() %>
			</td>
			<td><%=p.getPrice() %></td>
			<td><%=p.getStock() %></td>
			<td><a href="javascript:productDetail_guest('<%=p.getNo() %>')">보기</a></td>
		</tr>
	<%
	}
	%>
</table>
<%@ include file="guest_bottom.jsp" %>

<form action="productdetail_g.jsp" name="detailFrm" method="post">
	<input type="hidden" name="no" />
</form>

</body>
</html>
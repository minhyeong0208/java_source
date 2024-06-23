<%@page import="pack.product.ProductDto"%>
<%@page import="pack.order.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr"></jsp:useBean>

<%
OrderBean order = orderMgr.getOrderDetail(request.getParameter("no"));
ProductDto product = productMgr.getProduct(order.getProduct_no());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리(관리자)</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
</head>
<body>
	<h3>주문내역 상세보기</h3>
	<%@ include file="admin_top.jsp"%>
	
	<form action="orderproc_admin.jsp" name="detailFrm" method="post">
	<table>
		<tr>
			<td>고객 아이디 : <%=order.getId() %></td>
			<td>주문번호 : <%=order.getNo() %></td>
			<td>상품번호 : <%=product.getNo() %></td>
			<td>상품명 : <%=product.getName() %></td>
		</tr>
		<tr>
			<td>상품가격 : <%=product.getPrice() %></td>
			<td>주문수량 : <%=order.getQuantity() %></td>
			<td>재고 : <%=product.getStock() %></td>
			<td>주문날짜 : <%=product.getSdate() %></td>
		</tr>
		<tr>
			<td colspan="4">총 결제 금액 : <%=Integer.parseInt(order.getQuantity()) * Integer.parseInt(product.getPrice())  %></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: center;">주문상태 : 
				<select name="state">
					<option value="1">접수</option>
					<option value="2">입금확인</option>
					<option value="3">배송준비</option>
					<option value="4">배송중</option>
					<option value="5">처리완료</option>
				</select>
				<script type="text/javascript">
					document.detailFrm.state.value = <%=order.getState() %> <%-- 값 유지하는 방법 --%>
				</script>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: center">
				<input type="hidden" name="no" value="<%=order.getNo() %>">
				<input type="hidden" name="flag">
				<input type="button" value="수 정" onclick="orderUpdate(this.form)"/>
				<input type="button" value="삭 제" onclick="orderDelete(this.form)">
			</td>
		</tr>
	</table>
	</form>
	<%@ include file="admin_bottom.jsp"%>
</body>
</html>
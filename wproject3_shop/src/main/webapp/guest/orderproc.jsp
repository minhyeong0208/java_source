<%@page import="java.util.Map"%>
<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session"></jsp:useBean>    <!-- 장바구니 담기는 세션이 살아있는 동안 허용 --> 
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr"></jsp:useBean>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>

<%
//Hashtable hCart = cartMgr.getCartList();
//Enumeration enu = hCart.keys();
Hashtable<String, OrderBean> hCart = (Hashtable<String, OrderBean>)cartMgr.getCartList();

// 카트가 비어있는 경우
if(hCart.isEmpty()) {
%>
	<script>
		alert("주문 내역이 없습니다.");
		location.href="orderlist.jsp";
	</script>
<%
} else {
	/*
	while(enu.hasMoreElements()) {
		OrderBean orderBean = (OrderBean)hCart.get(enu.nextElement());
		orderMgr.insertOrder(orderBean); 	 // 주문 정보가 DB에 저장
		productMgr.reduceProduct(orderBean); // 주문 수량만큼 재고량 차감             26-27 행은 트랜잭션 처리 가능
		cartMgr.deleteCart(orderBean);       // 카트에 담겨 있던 내역을 삭제
	}
	*/
	for(Map.Entry<String, OrderBean> entry: hCart.entrySet()) {
		OrderBean orderBean = entry.getValue();
		orderMgr.insertOrder(orderBean); 	 // 주문 정보가 DB에 저장
		productMgr.reduceProduct(orderBean); // 주문 수량만큼 재고량 차감             26-27 행은 트랜잭션 처리 가능
		cartMgr.deleteCart(orderBean);       // 카트에 담겨 있던 내역을 삭제
	}
%>
	<script>
		alert("주문이 완료되었습니다.");
		location.href="orderlist.jsp";
	</script>

<%
}
%>
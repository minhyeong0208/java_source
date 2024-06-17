<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session" />  <!-- CartMgr을 세션이 살아있는 동안 유지하기 위해 scope값을 변경-->
<jsp:useBean id="order" class="pack.order.OrderBean" />
<jsp:setProperty property="*" name="order" />

<%
String orderFlag = request.getParameter("flag");  // 구매목록 보기, 수정, 삭제 판단용
String id = (String)session.getAttribute("idKey");

out.println(order.getProduct_no() + ", 주문 수량 : " + order.getQuantity());
%>
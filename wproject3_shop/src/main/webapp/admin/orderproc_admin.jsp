<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- name 속성 : no, flag, state를 들고 온다.-->
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr"></jsp:useBean>


<%
String flag = request.getParameter("flag");
String no = request.getParameter("no");
String state = request.getParameter("state");

boolean b = false;

if(flag.equals("update")) {
	b = orderMgr.updateOrder(no, state);  // no를 수정
} else if(flag.equals("delete")) {
	b = orderMgr.deleteOrder(no);
} else {
	response.sendRedirect("ordermanager.jsp");
}

if(b) { 
%>
	<script>
		alert('정상적으로 처리되었습니다.');
		location.href = "ordermanager.jsp";
	</script> 
<%
} else {
	
%>
	<script>
		alert('정상적으로 처리되었습니다.');
		location.href = "ordermanager.jsp";
	</script>
<%
}
%>
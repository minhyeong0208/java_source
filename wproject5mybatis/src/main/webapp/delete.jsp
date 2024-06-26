<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="processDao" class="pack.business.ProcessDao"></jsp:useBean>
<%
String code = request.getParameter("code");

boolean b = processDao.deleteData(Integer.parseInt(code));

if(b) {
	response.sendRedirect("list.jsp");
} else {
%>
	<script>
		alert('삭제 실패');
		location.href="list.jsp";
	</script>
<%
}
%>
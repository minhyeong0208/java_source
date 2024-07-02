<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session = request.getSession(false); // 세션이 있으면 읽고, 없으면 만들지 않음.

if(session == null || session.getAttribute("userid") == null) {
	out.println("<script>");
	out.println("alert('로그인 후 목록보기 가능!')");
	out.println("location.href='login.html'");
	out.println("</script>");
	
	return;
}
%>

<%-- 액션 태그를 사용할 경우, 결과만 전달이 됨. --%>
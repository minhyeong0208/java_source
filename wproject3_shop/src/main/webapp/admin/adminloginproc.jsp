<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.MemberMgr" />

<%
request.setCharacterEncoding("utf-8");
String adminid = request.getParameter("adminid");
String adminpasswd = request.getParameter("adminpasswd");

boolean b = memberMgr.adminLoginCheck(adminid, adminpasswd);
// b가 참이면 관리자 세션을 생성
if(b){
	session.setAttribute("adminOk", adminid);  //관리자 세션 설정
	response.sendRedirect("admin_index.jsp");
}else{
%>
	<script>
	alert("관리자 로그인 입력 오류!!!");
	location.href = "adminlogin.jsp";
	</script>
<%	
}
%>
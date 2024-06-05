<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession ses = request.getSession(false);

if(ses != null && ses.getAttribute("userId") != null) {
	String userid = (String)ses.getAttribute("userId");
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login Success!</title>
</head>
<body>
	<h2>로그인 성공 페이지</h2>
	<p>환영합니다! <%=userid %>를 사용하시는 분!</p>
	인증에 성공한 경우, 처리할 내용을 작업 Authrization(인가)<br><br>
	쇼핑, 게시판, 방명록, 회의 참여... <br><br>
	<a href="jsp9logout.jsp">로그아웃</a>
</body>
</html>
<%
} else {
	// 로그인 없이 jsp9success.jsp 파일이 호출된 경우
	response.sendRedirect("jsp9sessionlogin.html");
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
로그인 성공했을 때<p>
<table border="1">
	<tr>
		<td colspan="2">해당 페이지로 이동</td>
	</tr>
	<%
		String id = (String)session.getAttribute("id");
			
		if(id.equals("admin")) {
	%>
			<tr style="background-color: gold;">
				<td><%=id %></td>
				<td><a href="https://www.daum.net">관리자 화면</a></td>
			</tr>
	<%
		} else if(id.equals("user")) {
	%>
			<tr style="background-color: sliver;">
				<td><%=id %></td>
				<td><a href="https://www.naver.com">사용자 화면</a></td>
			</tr>
	<%
		}
	%>
</table>
</body>
</html>
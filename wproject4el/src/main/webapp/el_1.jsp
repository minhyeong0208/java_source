<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
EL(Expression Language)<br>
	- JSP 스크립트 표현식 대신해 속성 값을 쉽게 출력하기 위한 언어
	- 출력, 반복처리를 태그 기반으로 제공
	- 웹 디자이너와 프로그래머의 업무분리가 가능(* 주목적)
</pre>
<h2>EL 사용하기</h2>
<%
if(request.getParameter("userName") == null) {
%>
	<jsp:forward page="el_1.html" />
<%
}
%>
사용자가 전송한 userName 출력<br>
1-1) jsp scriptlet 사용 :
<% out.println(request.getParameter("userName")); %>
<br>
1-2) JSP 표현식 사용 :
<%= request.getParameter("userName") %>
<hr>
2) EL 내장 객체 사용
${param.userName}
</body>
</html>
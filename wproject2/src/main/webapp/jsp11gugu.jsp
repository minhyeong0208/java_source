<%@page import="pack.Gugudan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단  결과</title>
</head>
<body>
** 지금까지 배운 방법 사용 **<br>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
out.println(dan + "단<br>");

// Gugudan gugudan = new Gugudan();  // 클래스의 포함 관계, 객체가 요청 수만큼 생성됨.

// 싱글톤 패턴
Gugudan gugudan = Gugudan.getInstance();  // 클래스의 포함 관계. 객체가 단 한 번만 생성됨.

int re[] = gugudan.computeGugu(dan);

for(int a = 0; a < 9; a++){
	out.println(dan + " * " + (a + 1) + " = " + re[a] + "&nbsp;&nbsp;");
}
%>
<hr>
** JSP 액션 태그 중 useBean을 사용 ** <br>
<jsp:useBean id="gugu" class="pack.Gugudan" scope="page" /> <%-- Gugudan gugu = new Gugudan();와 비슷한 의미, id값이 객체 변수이다.--%>
<%-- 
scope 값
page : 현재 페이지에서만 유효. 객체는 매번 생성됨
Request : http 요청을 WAS가 받아서 웹 브라우저에게 응답할 때까지 변수가 유지되는 경우 사용. 객체가 매번 생성됨
Session : 웹 브라우저 별로 변수가 관리되는 경우 사용. 객체가 1회만 생성됨.
Application : 웹 어플리케이션이 시작되고 종료될 때까지 변수가 유지되는 경우 사용. 객체가 1회만 생성됨.
--%>
<%
int re2[] = gugu.computeGugu(dan);

for(int a = 0; a < 9; a++){
	out.println(dan + " * " + (a + 1) + " = " + re2[a] + "&nbsp;&nbsp;");
}
%>
</body>
</html>
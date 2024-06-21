<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"  
    %><%-- true인 경우, el 처리 불가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>EL 연산자 / 내장객체 경험</h2>
<b> 연산자 </b> <br>
\${3 + 4} ==> ${3 + 4}<br>
\${5 / 4} ==> ${5 / 4} <%-- ${5 div 4} --%><br> 
\${5 % 4} ==> ${5 % 4}, ${ 5 mod 4 }<br>

<%-- 대소 비교 --%>
\${5 > 4} ==> ${5 > 4}, ${5 gt 4}, ${5 lt 4}<br> 
<%-- 크거나 같음, 작거나 같음 --%>
\${5 >= 4} ==> ${5 >= 4}, ${5 ge 4}, ${5 le 4}<br> 

<%-- 관계 연산자 and, or --%>
\${5 > 4 and 3 > 2} ==> ${5 > 4 and 3 > 2}, ${5 > 4 or 3 > 2}<br> 

<%-- 삼항 연산자 --%>
\${5 > 4 ? 10 : 10 + 5} ==> ${5 > 4 ? 10 : 10 + 5}<hr> 
<b> 내장객체 </b><br>
<%
request.setAttribute("aa", "air");
session.setAttribute("bb", "burger");
application.setAttribute("cc", "cat");
%>
* 생존 범위 관련 내장객체 출력<br>
JSP 사용 시 : <%=request.getAttribute("aa") %>, EL 사용 시 : ${requestScope.aa}, ${aa}<br> <%-- requestScope는 생략 가능 --%>
JSP 사용 시 : <%=session.getAttribute("bb") %>, EL 사용 시 : ${sessionScope.bb}, ${bb}<br>
JSP 사용 시 : <%=application.getAttribute("cc") %>, EL 사용 시 : ${applicationScope.cc}, ${cc}<br><br>

JSP의 header 내장 객체 : <%=request.getHeader("host") %><br>
EL의 header : ${header.host}, ${header["host"]}<br><br>

* 컬렉션 객체 값 출력<br>
<%
ArrayList<String> list = new ArrayList<>();
list.add("치킨 버거");
list.add("새우 버거");
list.add("불고기 버거");
request.setAttribute("list", list);

ArrayList<String> list2 = new ArrayList<>();
list2 = (ArrayList)request.getAttribute("list");
out.println(list2.get(1));
%>
<br>
EL로 출력 : ${list[0]}, ${list[1]}, ${list[2]}

<%-- EL에서는 반복문 사용 불가, JSTL을 사용해야 함. --%>
<br><br>
<b>HTML 문서 자료 받기</b><p/>
<a href="el_2.html">el_2 HTML 호출</a><br>
이름 : ${ param.irum }, ${ param["irum"] }<br>
성격 : ${ paramValues.sung[0] }, ${ paramValues.sung["1"] } <%-- 배열 인덱스는 문자로 작성이 가능 --%>
</body>
</html>
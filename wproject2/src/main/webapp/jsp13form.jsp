<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String name = request.getParameter("name");  // 과거 방식
%>

<jsp:useBean id="bean" class="pack.ExamBean" /> <%-- id는 클래스 이름이다. 주고 싶은대로 주면 된다. --%>
<!--  
<jsp:setProperty property="name" name="bean" /> // 수신값이 적을 때
-->
<jsp:setProperty property="*" name="bean" /> <!-- property가 *인 경우, 수신된 값이 자동으로 ExamBean의 멤버 필드에 저장(setter 메소드 호출 기능?) -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
FormBean에 등록된 자료 출력하기 <br>
이름 : <jsp:getProperty property="name" name="bean"/><br>
국어 : <jsp:getProperty property="kor" name="bean"/>&nbsp;&nbsp;
영어 : <jsp:getProperty property="eng" name="bean"/>&nbsp;&nbsp;
수학 : <jsp:getProperty property="mat" name="bean"/><br>

<jsp:useBean id="process" class="pack.ExamProcess" />
<jsp:setProperty property="bean" name="process" value="<%=bean %>"/>
총점 : <jsp:getProperty property="sum" name="process"/>
<br>
평균 : <jsp:getProperty property="avg" name="process"/>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mbc = request.getParameter("msg");
%>

<jsp:useBean id="my" class="pack.Para1Class"></jsp:useBean> <%-- 객체 생성 완료 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Beans 연습 : 빈즈에 값 전달</title>
</head>
<body>
<b>Beans 연습 : 빈즈에 값 전달</b>

<!-- String msg = request.getParameter("msg"); 내부적으로 자동 처리 -->

<!-- JSP에서는 아래의 방법을 권장! -->
<jsp:setProperty property="msg" name="my"/> <%-- setter를 호출하는 방법 --%>
<br>메세지 출력(action 태그 사용) : 
<jsp:getProperty property="msg" name="my"/> <%-- name은 클래스?(7행의 id값에 해당) , getProperty는 getter --%>
<%-- property의 msg값은 Para1Class.java의 msg 멤버 필드가 아닌 getMsg, setMsg를 가리킨다. --%>
</body>
</html>
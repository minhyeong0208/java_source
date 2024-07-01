<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view1</title>
</head>
<body>
view 1의 결과<br>
기존 방식  : <%=request.getAttribute("result") %><br>
EL 사용 : ${result}
</body>
</html>
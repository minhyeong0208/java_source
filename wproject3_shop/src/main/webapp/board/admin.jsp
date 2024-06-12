<%-- 20240612 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
function check() {
	if(frm.id.value === "" || frm.pwd.value === "") {
		alert("로그인 자료를 입력하시오.");
		return;
	}
	frm.submit();
}
</script>
</head>
<body>
<form action="adminlogin.jsp" name="frm" method="post">
	<table style="width: 100%">
		<tr>
			<td>
				<%
				String sessionValue = (String)session.getAttribute("adminOk");
				if(sessionValue != null) {
					// 로그인 성공 시, 실행
				%>
					이미 로그인 상태입니다. <br><br>
					<a href="adminlogout.jsp">[로그아웃]</a>
					<a href="javascript:window.close()" onclick="check()">[창닫기]</a>
				<%
				} else { // 로그인 되어 있지 않은 경우에 실행됨.
				%>
					<table style="width: 100%">
						<tr>
							<td>ID : <input type="text" name="id"></td>
						</tr>
						<tr>
							<td>PW : <input type="text" name="pwd"></td>
						</tr>
						<tr>
							<td>
								<a href="#" onclick="check()">[로그인]</a>
								<a href="javascript:window.close()">[창닫기]</a>
							</td>
						</tr>
					</table>
				<%	
				}
				%>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
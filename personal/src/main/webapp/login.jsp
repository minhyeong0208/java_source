<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
// 로그인 성공하면 loginok, 실패하면 loginfail로 이동하는 로직 처리
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

if((id.equals("admin") && pwd.equals("111")) || (id.equals("user") && pwd.equals("222"))) {
	// 세션을 생성하는 방법
	session.setAttribute("id", id); // 두 번째 인자의 id값이 클라이언트마다 다른 값을 줄 수 있다.
	response.sendRedirect("loginok.jsp");
	
	// 세션이 아니라 request를 사용한다면 아래와 같이 기술
//	request.setAttribute("id", id);
//	request.getRequestDispatcher("jsp8loginok.jsp").forward(request, response);

	// 세션의 단점 : 클라이언트가 들어올 때마다 세션을 생성해야 하므로 서버에 부담이 된다. 이를 해결하기 위해 jwt를 사용한다.
} else {
	response.sendRedirect("loginfail.html");
}
%>
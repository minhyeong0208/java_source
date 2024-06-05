<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String password = request.getParameter("password");

// 인증(Authentification) : 실제로는 DB 정보를 읽어 확인
String validId = "ok";
String validPassword = "123";

if(id != null && password != null && id.equalsIgnoreCase(validId) && password.equalsIgnoreCase(validPassword)) {
	// 인증이 유효한 경우
	HttpSession ses = request.getSession();
	ses.setAttribute("userId", id); // 세션 생성 후, session id를 클라이언트 컴퓨터 쿠키에 저장.
	// setAttribute() 실행 시, 클라이언트가 서버로부터 로그인 창을 받아 값을 입력 후, 세션을 생성 후, session id값을 서버로부터 받아 쿠키에 저장함. 

	// 성공한 경우, 보여줄 페이지로 이동
	// success.jsp는 서버에 존재.
	// sendRedirect() 호출 시, 클라이언트의 URL에 success.jsp를 호출 -> 해당 파일을 client에 뿌림.
	response.sendRedirect("jsp9success.jsp"); // success.html로도 이동 가능

} else {
	// 인증에 실패한 경우, 처리 작업
	out.println("<html><body>");
	
	out.println("<h3>로그인 실패!</h3>");
	out.println("<a href='jsp9sessionlogin.html'>다시 시도</a>");
	
	out.println("</body></html>");
}
%>
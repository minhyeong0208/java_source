<%@page import="java.util.Base64"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jws"%>
<%@page import="io.jsonwebtoken.security.Keys"%>
<%@page import="java.security.Key"%>
<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// cookie에서 JWT를 가져와 유효성 검사
Cookie[] cookies = request.getCookies();  // 쿠키는 여러 개가 있을 수 있으므로 배열로 선언
String jwt = null;
if(cookies != null) {
	for(Cookie cookie : cookies) {
		if(cookie.getName().equals("jwt")) {
			jwt = cookie.getValue();
			break;
		}
	}
}

if(jwt != null) {
	try {
		// 고정된 비밀 키 사용 (예제용)  최소 256비트 길이의 비밀 키
		//String secretKeyString = "mySuperSecretKey12345678901234567890123456789012";
		//Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
		
		// 위의 작업을 주석 처리하고 아래 내용으로 변경하자.

    	// 서블릿 컨텍스트에서 Base64로 인코딩된 비밀 키 가져오기  java.util.Base64
    	String encodedKey = (String) getServletContext().getAttribute("secretKey");
    	byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    	Key secretKey = Keys.hmacShaKeyFor(decodedKey);
		
		// JWT 유효성 검사 (이미 발급된 JWT를 검증하고 그 내용을 파악)
		Jws<Claims> claims = Jwts.parserBuilder()
								.setSigningKey(secretKey)  // setSigningKey() : 서명키 설정. -> JWT 생성 시, 사용된 비밀키(secretKey)와 일치해야 함.
								.build()                   // build() : parser 객체를 생성
								.parseClaimsJws(jwt);      // parseClaimsJws() :      
		// claims.getBody() : JWT의 페이로드를 반환
		String userid = claims.getBody().getSubject();     // getSubject() : Subject 클레임을 반환
		// 유효한 경우 환영 메세지 출력
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JWT Login Success!</title>
</head>
<body>
	<h2>JWT 로그인 성공 페이지</h2>
	<p>환영합니다! <%=userid %>를 사용하시는 분!</p>
	인증에 성공한 경우, 처리할 내용을 작업 Authrization(인가)<br><br>
	쇼핑, 게시판, 방명록, 회의 참여... <br><br>
	<a href="jsp10logout.jsp">로그아웃</a>
</body>
</html>
<%
	} catch(JwtException e) {
		// JWT가 유효하지 않으면 로그인 페이지로 이동함.
		response.sendRedirect("jsp10jwtlogin.html");
	}
} else {
	// JWT 없으면 로그인 페이지로 이동함.
	response.sendRedirect("jsp10jwtlogin.html");
}
%>
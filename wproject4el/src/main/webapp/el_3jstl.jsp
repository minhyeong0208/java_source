<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="hello" uri="http://java.sun.com/jsp/jstl/core" %> <%-- 보통 prefix 값은 c로 설정 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>JSTL</h2>
JSTL은 JavaServer Pages Standard Tag Library의 약어로, <br>
Java 코드를 바로 사용하지 않고 HTML 태그(<>) 형태로 직관적인 코딩을 지원하는 라이브러리<br>
Java EE 기반의 웹 애플리케이션 개발 플랫폼을 위한 컴포넌트 모음<br>
XML 데이터 처리와 조건문, 반복문, 국제화와 지역화와 같은 일을 처리하기 위한 JSP 태그 라이브러리<br>
자신만의 태그를 추가할 수 있는 기능을 제공.<br><br>

변수, 제어문 사용이 가능. 일반적으로 EL과 함께 사용!
<hr>
<b>변수 처리</b><br>
<hello:set var="irum" value="이기자" scope="page"></hello:set> <%-- page, request, session, application --%>
이름 : <hello:out value="${irum}" /> <%-- 출력의 경우 out 사용 --%>
<br>

<hello:set var="ir" scope="session">
공기밥  
</hello:set> <%-- value 값으로 설정 또는 태그 내부에 설정도 가능 --%>
이름 : <hello:out value="${ir}" />
<br>

<%-- 변수를 지우기 위해 remove 사용 --%>
<hello:remove var="irum"/>
이름 : <hello:out value="${irum}" />
<br>
<hello:remove var="ir"/>
이름 : <hello:out value="${ir}" />
<br>

<hello:set var="abc" value="${header['User-Agent']}" scope="page" />
abc 값은 (현재 사용 중인 브라우저 정보) <hello:out value="${abc}"></hello:out>
<br>
<hello:set var="su1" value="10" />
<hello:set var="su2">20</hello:set> 
두 수의 합은 ${su1 + su2}





<hr>
<b>조건 판단문 if</b><br>
<hello:set var="nice" value="star"/>

<hello:if test="${nice == 'star'}"> <%-- ${nice eq 'star'}  eq : 비교를 위해 사용 --%>
	if 연습 : nice 값은 <hello:out value="${nice}" />
</hello:if>
<p/>
<b>조건 판단문 choose</b>
<hello:choose>
	<hello:when test="${nice == 'moon'}">
		달 <hello:out value="${nice}" />
	</hello:when>
	<hello:when test="${nice == 'star'}">
		별 <hello:out value="${nice}" />
	</hello:when>
	<hello:otherwise>어떠한 조건도 만족하지 않는 경우 실행</hello:otherwise>
</hello:choose>
<br>
<hello:choose>
	<hello:when test="${empty param.myid}"> <%-- 처음 param.name이 없으므로 실행문 실행.  --%>
		<form>
			아이디 : <input type="text" name="myid">
			<input type="submit">
		</form>
	</hello:when>
	<hello:when test="${param.myid == 'admin'}">
		관리자
	</hello:when>
	<hello:otherwise>
		회원 <hello:out value="${param.myid}"></hello:out>
	</hello:otherwise>
</hello:choose>
<hr>



<b>반복문 forEach</b><br>
연습 1 : 
<hello:forEach var="i" begin="1" end="10" step="2">
	${i}&nbsp;&nbsp;
</hello:forEach>
<br><br>

구구단(3단)<br>
<hello:forEach var="i" begin="1" end="9">
	3 * ${i} = ${3 * i}<br>
</hello:forEach>
<br>



<%-- 컬렉션 사용 --%>
<%
HashMap<String, Object> map = new HashMap<>();
map.put("name", "한국인");
map.put("today",new Date());
%>

<hello:set var="m" value="<%=map %>"></hello:set>
<hello:forEach var="i" items="${m}">
	${i.key} - ${i.value}
</hello:forEach>
<br><br>


<b>배열 생성 후 출력</b><br>
<hello:set var="arr" value="<%=new int[]{1,2,3,4,5} %>"></hello:set>
<hello:forEach var="a" begin="2" end="4" step="1">
	${a}&nbsp;
</hello:forEach>
<br><br>


<b>문자열 분할 후 출력</b><br>
<hello:forTokens var="animal" items="horse,dog*cat,lion" delims=",*"> <%-- delims가 여러 개여도 상관 X --%>
	동물 : ${animal}&nbsp;
</hello:forTokens>
<hr>



<b>숫자 및 날짜 서식</b><br>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
숫자 : <fmt:formatNumber value="12345.678" type="number" /><br>  <%-- 세자리씩 구분 --%>
숫자 : <fmt:formatNumber value="12345.678" type="number" pattern="#,##0" /><br> <%-- 소수 첫째짜리에서 반올림 --%>
숫자 : <fmt:formatNumber value="12345.678" type="currency" /><br>
숫자 : <fmt:formatNumber value="0.123" type="percent" /><br>
숫자 : <fmt:formatNumber value="12345.678" pattern="#,##0.0" /><br> <%-- 소수 둘째자리에서 반올림 --%>
숫자 : <fmt:formatNumber value="12345.678" pattern="0,000.0" /><br> 
숫자 : <fmt:formatNumber value="12" pattern="0,000.0" /><br>
<br>
<hello:set var="now" value="<%=new Date() %>"></hello:set>
날짜 : <fmt:formatDate value="${now}" type="date"/><br>
시간 : <fmt:formatDate value="${now}" type="time"/><br>
날짜 및 시간 : <fmt:formatDate value="${now}" type="both"/><br>
날짜 및 시간 : <fmt:formatDate value="${now}" type="both" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/><br>
</body>
</html>
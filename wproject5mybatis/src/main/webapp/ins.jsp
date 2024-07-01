<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="form" class="pack.business.DataForm"></jsp:useBean>
<jsp:setProperty property="*" name="form" /> <%-- 입력 데이터가 DataForm에 들어가 있음 --%>
<jsp:useBean id="processDao" class="pack.business.ProcessDao"></jsp:useBean>

<%
processDao.insertData(form);

// 목록 보여주기
response.sendRedirect("list.jsp");  // 클라이언트의 주소가 변경됨

%>
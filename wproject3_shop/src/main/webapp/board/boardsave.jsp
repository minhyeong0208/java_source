<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr"></jsp:useBean>
<%
bean.setBip(request.getRemoteAddr()); // 클라이언트의 ip 주소가 등록됨.
bean.setBdate();

int newNum = boardMgr.currentMaxNum() + 1;
bean.setNum(newNum);
bean.setGnum(newNum);

boardMgr.saveData(bean);

response.sendRedirect("boardlist.jsp?page=1");  // 최근 데이터를 보이기 위해 1페이지로 설정
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String memid = (String)session.getAttribute("idKey");  // 일반 회원
String log = "";

// 로그인
if(memid == null) 
	log = "<a href='../member/login.jsp'>로그인</a>";
else
	log = "<a href='../member/logout.jsp'>로그아웃</a>";


// 회원 가입
String mem = "";
if(memid == null) 
	mem = "<a href='../member/register.jsp'>회원가입</a>";
else
	mem = "<a href='../member/memberupdate.jsp'>회원수정</a>";
%>
<table>
	<tr style="background-color: #ffbbcc; text-align:center;">
		<td><%=log %></td> <!-- td 태그에 a 태그 삽입 -->
		<td><%=mem %></td>
		<td><a href="productlist.jsp">상품 목록</a></td>
		<td><a href="cartlist.jsp">장바구니</a></td>
		<td><a href="orderlist.jsp">구매 목록</a></td>
		<td><a href="../board/boardlist.jsp">게시판</a></td> 
	</tr>
</table>
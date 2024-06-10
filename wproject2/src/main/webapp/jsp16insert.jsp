<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 입력 자료를 전송 받아 insert 하는 logic -> 서블릿으로 만들어도 상관 X
request.setCharacterEncoding("utf-8");
// String sang = request.getParameter("sang");
%>

<%-- getParameter() 메소드 사용하지 않고 formBean 사용 --%>
<jsp:useBean id="sangpumBean" class="pack.SangpumBean" />
<jsp:setProperty property="*" name="sangpumBean" />
<%
// 수신 데이터 검증 필요.. ex. jsp15insert.jsp로 바로 이동하려는 경우
%>
<jsp:useBean id="connclass3" class="pack.ConnClass3"/>
<% 
connclass3.insertData(sangpumBean);

// 상품 추가 후 상품 목록 보기로 이동
// redirect 방식 
response.sendRedirect("jsp16paging.jsp");

// forward 방식 -> 여기서는 사용하면 안됨!
// 주의 : 추가, 수정, 삭제 후 목록보기 할 때는 forwarding 하지 않는다.
//request.getRequestDispatcher("jsp16paging.jsp").forward(request, response);
// 실행한 후 주소를 확인하면 jsp16insert.jsp로 변경되지 않는다.(정상적인 경우라면 sp16paging.jsp 페이지가 보여야 함) 
// 이 경우, 새로고침하면 값이 계속 추가된다. 클라이언트를 통해서 호출해야 함.
%>

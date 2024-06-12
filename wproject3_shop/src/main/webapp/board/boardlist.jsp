<%-- 20240611 --%>
<%@page import="pack.board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />
<jsp:useBean id="dto" class="pack.board.BoardDto" />

<%
int spage = 1, pageSu = 0;  // pageSu : 페이지 수
int start, end;

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnSearch").onclick = function() {
		if(frm.sword.value === "") {
			frm.sword.focus();
			frm.sword.placeholder = "검색어를 입력하시오.";
			return;
		}
		frm.submit();
	}
}
</script>
</head>
<body>
<table>
	<tr>
		<td>
			<a href="../index.html">메인으로</a>&nbsp;
			<a href="boardlist.jsp?page=1">최근목록</a>&nbsp;
			<a href="boardwrite.jsp">새글작성</a>&nbsp;
			<a href="#" onclick="window.open('admin.jsp','','width=300,height=150,top=200,left=300')">관리자용</a>&nbsp;
			<br><br>
			
			<table style="width: 100%">
				<tr style="background: linear-gradient(to right, #ff105f, #ffad06);">
					<th>번호</th><th>제 목</th><th>작성자</th><th>작성일</th><th>조회수</th>
				</tr>
				<%
				try {
					spage = Integer.parseInt(request.getParameter("page"));
					
					
				} catch(Exception e) {
					spage = 1;                 // spage 값을 주지 않는 경우, 기본값은 1로 설정
				}
				if(spage <= 0) spage = 1;      // spage 값이 음수인 경우, 1페이지로 이동
				
				// 검색일 경우 ----
				String stype = request.getParameter("stype");
				String sword = request.getParameter("sword");
				
									
				// -------------
				
				boardMgr.totalList();          // 전체 레코드 수 계산
				pageSu = boardMgr.getPageSu(); // 전체 페이지 수 얻기
				
				//ArrayList<BoardDto> list = boardMgr.getDataAll(spage);
				ArrayList<BoardDto> list = boardMgr.getDataAll(spage, stype, sword); // 검색도 처리 가능한 메소드 
				
				for(int i = 0; i < list.size(); i++) {
					dto = (BoardDto)list.get(i);	// casting 선택
					
					// 20240612 댓글 들여쓰기 준비 -----
					int nst = dto.getNested();
					String tab = "";
					for(int k = 0; k < nst; k++) {
						tab += "&nbsp;&nbsp;";
					}
					// ----------------------------
				%>
					<tr>
						<td style="border-bottom: 1px solid black"><%=dto.getNum() %></td>
						<td style="border-bottom: 1px solid black">
							<%=tab %><a href="boardcontent.jsp?num=<%=dto.getNum() %>&page=<%=spage %>"><%=dto.getTitle() %></a>
						</td>
						<td style="border-bottom: 1px solid black"><%=dto.getName() %></td>
						<td style="border-bottom: 1px solid black"><%=dto.getBdate() %></td>
						<td style="border-bottom: 1px solid black"><%=dto.getReadcnt() %></td>
					</tr>
				<%	
				}
				%>
			</table>
			<br>
			<table style="width: 100%">
				<tr>
					<td style="text-align: center">
					<%
					for(int i = 1; i <= pageSu; i++) {
						if(i == spage) {  // 현재 페이지인 경우
							out.print("<b style='font-size:12pt;color:red;'>[" + i + "]</b>&nbsp;&nbsp;");
						} else {
							out.print("<a href='boardlist.jsp?page=" + i + "'>[" + i + "]</a>&nbsp;&nbsp;");						
						}
					}
					%>
					
					<br><br>
					<form action="boardlist.jsp" name="frm" method="get">
						<select name="stype">
							<option value="title" selected="selected">글제목</option>
							<option value="name">작성자</option>
						</select>
						<input type="text" name="sword">
						<input type="button" value="검색" id="btnSearch">
					</form>
					</td>
				</tr>
			</table>
			
		</td>
	</tr>
</table>
</body>
</html>
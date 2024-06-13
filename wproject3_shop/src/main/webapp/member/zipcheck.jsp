<%@page import="pack.member.ZipcodeDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.MemberMgr" />

<%
request.setCharacterEncoding("utf-8");
String check = request.getParameter("check");  // y(동이름 입력화면 출력) or n
String dongName = request.getParameter("dongName");  //동 이름 입력 : <input type="text" name="dongName"> 행의 name 값을 인수로 전달함.

ArrayList<ZipcodeDto> zlist = memberMgr.zipcodeRead(dongName);  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnZipFind").onclick = dongCheck;
	document.querySelector("#btnZipClose").onclick = function() {
		window.close();  // window.open()을 사용했으므로 이 명령으로 닫는다.
	};
}

function dongCheck() {
	if(zipForm.dongName.value === "") {
		alert("검색할 동 이름을 입력하시오.");
		zipForm.dongName.focus();
		return;
	}
	zipForm.submit();
}

// 값을 받아 클릭 시, register.jsp에 값을 입력하도록 하기 위한 메소드
function sendFunc(code, a1, a2, a3, a4) {
	//alert(code + a1 + a2 + a3 + a4);
	opener.document.regForm.zipcode.value = code;
	const addr = a1 + " " + a2 + " " + a3 + " " + a4;
	opener.document.regForm.address.value = addr;
	
	window.close();  // 주소 검색창 닫기
}
</script>
</head>
<body>
	<strong>*** 우편번호 찾기 ***</strong>
	<form action="zipcheck.jsp" name="zipForm" method="post">
		<table>
			<tr>
				<td>
					동 이름 입력 : <input type="text" name="dongName" size="10">
					<input type="button" value="검색" id="btnZipFind">
					<input type="button" value="창닫기" id="btnZipClose">
					<input type="hidden" name="check" value="n">
				</td>
			</tr>
		</table>
	</form>
	
	<%
	// 검색을 진행한 경우
	if(check.equals("n")) {
		if(zlist.isEmpty()) {
	%>
			<b>검색 결과가 없습니다!</b>
	<%
		} else {
	%>
			<table>
				<tr>
					<td style="">
						검색 자료를 클릭하면 자동으로 주소가 입력됩니다.
					</td>
				</tr>
				<tr>
					<td>
					<%
					for(int i = 0; i < zlist.size(); i++) {
						ZipcodeDto dto = zlist.get(i);  // dto를 하나씩 가져온다.
						String zipcode = dto.getZipcode();
						String area1 = dto.getArea1();
						String area2 = dto.getArea2();
						String area3 = dto.getArea3();
						String area4 = dto.getArea4();
						if(area4 == null) area4 = "";
					%>
					<a href="javascript:sendFunc('<%=zipcode %>','<%=area1 %>','<%=area2 %>','<%=area3 %>','<%=area4 %>')">
					<%=zipcode %> <%=area1 %> <%=area2 %> <%=area3 %> <%=area4 %> <!-- DB에서 값을 꺼내 출력 -->
					</a>
					<br>
					<% 	
					}
					%>
					</td>
				</tr>
			</table>
	<%
		}
	}
	%>
</body>
</html>
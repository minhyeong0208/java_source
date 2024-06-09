<%@ page import="personal.ChartUtils"%>
<%@ page import="java.io.File"%>
<%
    // 차트 이미지를 저장할 임시 파일 경로 설정
    String fileName = "chart.png";
    String filePath = application.getRealPath("/") + fileName;

    // 차트 생성 및 저장
    ChartUtils.generateBarChart(filePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>XChart Bar Chart</h1>
	<div class="inner_body">
		<button onclick="sidebar.open()">SideBar Open</button>
		<aside class="side_bar js-side_bar">
			<ul>
				<li>
				<li><a href="#">Home</a></li>
				<li><a href="#">Product</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			<button onclick="sidebar.close()">SideBar Close</button>
		</aside>
	</div>
	<span>TEST</span>
	<div>
		<img src="<%= fileName %>" alt="XChart Image" width="400px">
	</div>
</body>
</html>
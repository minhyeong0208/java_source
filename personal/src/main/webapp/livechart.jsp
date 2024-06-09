<%@ page import="personal.ChartUtils" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.io.OutputStream" %>
<%
    // 차트 이미지를 저장할 임시 파일 경로 설정
    String fileName = "chart.png";
    String filePath = application.getRealPath("/") + fileName;

    // 차트 생성 및 저장
    ChartUtils.generateBarChart(filePath);

    // 이미지 파일을 클라이언트로 전송
    response.setContentType("image/png");
    try (OutputStream output = response.getOutputStream()) {
        Files.copy(Paths.get(filePath), output);
    } catch (Exception e) {
        e.printStackTrace(response.getWriter());
    }
%>
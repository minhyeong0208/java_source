<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 동일 출처 정책(Same-Origin Policy), CORS 문제 해결
response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%
String DRIVER = "org.mariadb.jdbc.Driver";
String DBURL = "jdbc:mariadb://localhost:3306/test";
String DBID = "root";
String DBPW = "123";

//2. DB연결
Class.forName(DRIVER);
Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);

String sql = "select jikwon_no, jikwon_name, buser_name, jikwon_jik,jikwon_pay from jikwon inner join buser on jikwon.buser_num=buser.buser_no";
PreparedStatement pstmt = con.prepareStatement(sql);

ResultSet rs = pstmt.executeQuery();


JSONArray arr = new JSONArray();


while (rs.next()) {

	JSONObject obj = new JSONObject();
	obj.put("no", rs.getString("jikwon_no"));
	obj.put("name", rs.getString("jikwon_name"));
	obj.put("buser", rs.getString("buser_name"));
	obj.put("jik", rs.getString("jikwon_jik"));
	obj.put("pay", rs.getString("jikwon_pay"));
	
	//5-3.배열한칸에 객체 하나를 저장
	arr.add(obj);
}
%>

{ 
"jikwon":<%=arr%>
}

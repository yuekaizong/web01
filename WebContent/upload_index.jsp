<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源文件</title>
</head>
<body>
单击下面的链接下载文件<p>
<a href="download.jsp?id=111">tomcat.exe</a> <br>
<%
 Context ctx = new InitialContext();
 DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
 Connection conn = ds.getConnection();
 Statement stmt = conn.createStatement();
 ResultSet rs = stmt.executeQuery("select id, filename from uploadfile");
 while(rs.next()){
      %>
      <a href="download.jsp?id=<%=rs.getInt(1) %>"><%=rs.getString(2) %></a><br>
     <%
 }
%>

</body>
</html>
<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB2312"%>
<%
    String admin = (String) session.getAttribute("admin");
    if (admin == null || !admin.equals("true"))
    {
        out.println("你无权访问这个页面！！！");
        return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>网上书店留言板</title>
</head>
<body>

	<a href="say.html">我要留言</a>

	<%
	    Context ctx = new InitialContext();
	    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
	    Connection conn = ds.getConnection();

	    Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
	            ResultSet.CONCUR_READ_ONLY);
	    /* 	    Statement stmt = conn.createStatement(); */
	    ResultSet rs = stmt.executeQuery("select * from guestbook order by gst_time desc");

	    /* rs.last(); */

	    while (rs.next()) {
	        out.println("<hr color=\"blue\" size=\"2\"><br>");
	        out.println("用户名：" + rs.getString("gst_user"));
	        out.println("&nbsp; &nbsp;");

	        Timestamp ts = rs.getTimestamp("gst_time");
	        long lms = ts.getTime();
	        Date date = new Date(lms);
	        Time time = new Time(lms);

	        out.println("留言时间：" + date + " " + time);
	        out.println("&nbsp; &nbsp;");
	        out.println("用户IP:" + rs.getString("gst_ip") + "<br>");
	        out.println("主题:" + rs.getString("gst_title") + "<br>");
	        out.println("内容:" + rs.getString("gst_content"));
	        out.println("&nbsp; &nbsp;&nbsp; &nbsp;");
	        out.println("<a href=admin_del.jsp?gst_id=" + rs.getInt(1) + ">删除</a>");
	    }

	    rs.close();
	    stmt.close();
	    conn.close();
	%>

</body>
</html>
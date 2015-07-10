<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%
    String admin = (String) session.getAttribute("admin");
    if (admin == null || !admin.equals("true")) {
        out.println("你无权访问这个页面！！！");
        return;
    }
%>

<%
    String strID = request.getParameter("gst_id");
    int id = Integer.parseInt(strID);
    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
    Connection conn = ds.getConnection();

    PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where gst_id=?");
    pstmt.setInt(1, id);
    pstmt.executeUpdate();
    response.sendRedirect("admin_index.jsp");
    pstmt.close();
    conn.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
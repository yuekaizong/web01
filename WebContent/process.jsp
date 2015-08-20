<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%-- <%@ include file="util.jsp"%> --%>
<%
    request.setCharacterEncoding("gb2312");

    String name = request.getParameter("name");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    if (null == name || null == title || null == content) {
        response.sendRedirect("index.jsp");
        return;
    }

    //name = toHtml(name.trim());
    //title = toHtml(title.trim());
    if (name.equals("") || title.equals("")) {
        response.sendRedirect("say.html");
        return;
    }

    //content = toHtml(content.trim());
    String fromIP = request.getRemoteAddr();

    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
    Connection conn = ds.getConnection();

    PreparedStatement pstmt = conn
            .prepareStatement("insert into guestbook(gst_user, gst_title, gst_content, gst_ip) values (?,?,?,?)");
    pstmt.setString(1, name);
    pstmt.setString(2, title);
    pstmt.setString(3, content);
    pstmt.setString(4, fromIP);

    pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    response.sendRedirect("index.jsp");
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
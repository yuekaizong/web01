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
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>����������԰�</title>
</head>
<body>

	<a href="say.html">��Ҫ����</a>

	<%
	    Context ctx = new InitialContext();
	    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
	    Connection conn = ds.getConnection();

	    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery("select * from gueskbook order by gst_time desc");

	    rs.last();

	    int rowCount = rs.getRow();
	    if (rowCount == 0) {
	        out.println("��ǰû���κ����ԣ�");
	        return;
	    }

	    String strCurPage = request.getParameter("page");

	    int curPage;

	    if (strCurPage == null)
	        curPage = 1;
	    else
	        curPage = Integer.parseInt(strCurPage);

	    int countPerPage = 5;

	    int pageCount = (rowCount + countPerPage - 1) / countPerPage;

	    rs.absolute((curPage - 1) * countPerPage + 1);

	    if (curPage == 1) {
	%>
	��һҳ &nbsp; &nbsp; &nbsp; &nbsp; ��һҳ &nbsp; &nbsp; &nbsp; &nbsp;
	<%
	    }
	    else
	    {
	%>
	<a href="index.jsp?page=page=" <%=1%>>��һҳ</a> &nbsp; &nbsp; &nbsp;
	&nbsp;
	<a href="index.jsp?page=page=" <%=curPage - 1%>>��һҳ</a> &nbsp; &nbsp;
	&nbsp; &nbsp;

	<%
	    }
	    if (curPage == pageCount) {
	%>
	��һҳ &nbsp; &nbsp; &nbsp; &nbsp; ���ҳ &nbsp; &nbsp; &nbsp; &nbsp;
	<%
	    } else {
	%>
	<a href="index.jsp?page=<%=curPage + 1%>">��һҳ</a>
	<a href="index.jsp?page=<%=pageCount%>">���ҳ</a> &nbsp; &nbsp; &nbsp;
	&nbsp;
	<%
	    }

	    int i = 0;

	    while (i < countPerPage && !rs.isAfterLast()) {
	        out.println("<hr color=\"blue\" size=\"2\"><br>");
	        out.println("�û�����" + rs.getString("gst_user"));
	        out.println("&nbsp; &nbsp;");

	        Timestamp ts = rs.getTimestamp("get_time");
	        long lms = ts.getTime();
	        Date date = new Date(lms);
	        Time time = new Time(lms);

	        out.println("����ʱ�䣺" + date + " " + time);
	        out.println("&nbsp; &nbsp;");
	        out.println("�û�IP:" + rs.getString("gst_ip") + "<br>");
	        out.println("����:" + rs.getString("gst_title") + "<br>");
	        out.println("����:" + rs.getString("gst_content"));
	        i++;
	        rs.next();
	    }

	    rs.close();
	    stmt.close();
	    conn.close();
	%>

</body>
</html>















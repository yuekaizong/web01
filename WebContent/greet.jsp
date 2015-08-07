<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ taglib uri="/mytag" prefix="my"%>
<%!int size = 3;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>欢迎页面</title>
</head>
<body>
	<%!String greeting = "欢迎你！";%>
	<%
	    String user = request.getParameter("user");
	    StringBuffer sb = new StringBuffer();
	    sb.append(user).append(", ").append(greeting);
	%>
	<center>
		<h1>程序员之家留言板</h1>
		<%=sb.toString()%>
	</center>

	<my:greet>
		<font size=<%=size++%> color=blue> 欢迎访问Kaizone的个人网站 </font>
		<p>
	</my:greet>
</body>
</html>
<%
    if (size > 5)
        size = 3;
%>
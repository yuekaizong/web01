<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
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

</body>
</html>
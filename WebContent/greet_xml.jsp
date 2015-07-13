<?xml version="1.0" encoding="UTF-8"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html;charset=GB2312" />
	<jsp:output omit-xml-declaration="no" doctype-root-element="number"
		doctype-system="number.dtd"></jsp:output>
	<html>
<head>
<title>欢迎页面</title>
</head>
	</html>
	<body>
		<jsp:declaration>String greeting = "欢迎你！";</jsp:declaration>
		<jsp:scriptlet>String user = request.getParameter("user");
			StringBuffer sb = new StringBuffer();
			sb.append(user).append(", ").append(greeting);</jsp:scriptlet>
		<center>
			<h1><jsp:text>程序员之家留言板</jsp:text></h1>
			<jsp:expression>sb.toString()</jsp:expression>
		</center>
		<number> <jsp:expression>2+3</jsp:expression> </number>
		<jsp:scriptlet>String url="stock.html";</jsp:scriptlet>
		<jsp:include page="%=url%" />
		<a href="%=url%">stock</a>
	</body>
</jsp:root>
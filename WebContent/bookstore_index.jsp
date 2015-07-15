<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>欢迎光临网上书店</title>
</head>
<body>
	<center>
		<h1>欢迎光临网上书店</h1>
		搜索图书<br>
		<form action="search.jsp" method="get">
			请输入关键字：<input type="text" name="keyword" /> <input type="submit"
				value="搜索" />
		</form>
		<br>
		<a href="catalog.jsp">查看所有图书</a>
	</center>

</body>
</html>
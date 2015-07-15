<%@page import="kaizong.jee.web01.b.BookBean"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ include file="bookstore_common.jsp"%>
<jsp:useBean id="cart" scope="session"
	class="kaizong.jee.web01.b.CartBean" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>欢迎光临网上书店</title>
</head>
<body>
	<%
	    String strBookId = request.getParameter("id");
	    if (null == strBookId || "".equals(strBookId))
	    {
	        response.sendRedirect("catalog.jsp");
	        return;
	    }
	    else
	    {
	        int bookId = Integer.parseInt(strBookId);
	        BookBean book = bookdb.getBook(bookId);
	%>
	<table border="1">
		<tr>
			<th>书名</th>
			<th>作者</th>
			<th>出版社</th>
			<th>出版日期</th>
			<th>价格</th>
		</tr>
		<tr>
			<th>《<%=book.getTitle()%>》
			</th>
			<th><%=book.getAuthor()%></th>
			<th><%=book.getBookconcern()%></th>
			<th><%=book.getPublish_date()%></th>
			<th><%=book.getPrice()%></th>
		</tr>
	</table>
	<%
	    if (cart.isExit(new Integer(bookId))) {
	            out.println("该图书已在购物车中<br>");
	        } else {
	%>
	<a href="bookinfo.jsp?add=" <%=bookId%>&id=<%=bookId%>">加入购物车</a>
	<br>
	<%
	    }
	%>
	购物车中现有<%=cart.getNumOfItems()%>种图书 &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="catalog.jsp">查看所有图书</a>
	<%
	    }
	%>
</body>
</html>
<%@page import="kaizong.jee.web01.b.BookBean"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ include file="bookstore_common.jsp"%>
<jsp:useBean id="cart" scope="session"
	class="kaizong.jee.web01.b.CartBean" />
<%
    String strBookId = request.getParameter("add");
    if (strBookId != null && !"".equals(strBookId)) {
        int bookId = Integer.parseInt(strBookId);
        BookBean book = bookdb.getBook(bookId);
        cart.addItem(new Integer(bookId), book);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
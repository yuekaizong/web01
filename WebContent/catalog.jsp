<%@page import="kaizong.jee.web01.b.BookBean"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ include file="bookstore_common.jsp"%>

<jsp:useBean id="cart" scope="session"
	class="kaizong.jee.web01.b.CartBean"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>��ӭ�����������</title>
</head>
<body>
	<jsp:include page="additem.jsp" flush="false" />
	<h1>����վ���۵�ͼ���У�</h1>
	<p>
		<%
		    Collection cl = bookdb.getBooks();
		    Iterator it = cl.iterator();
		%>
	
	<table>
		<tr>
			<th>����</th>
			<th>�۸�</th>
			<th>����</th>
		</tr>
		<%
		    while (it.hasNext()) {
		        BookBean book = (BookBean) it.next();
		        String title = book.getTitle();
		        int bookId = book.getId();
		        float price = book.getPrice();
		%>
		<tr>
			<td><a href="bookinfo.jsp?id=<%=bookId%>">��<%=title%>��</a></td>
			<td><%=price %></td>
			<td><a href="catalog.jsp?add=<%=bookId %>">���빺�ﳵ</a></td>
		</tr>
		<%
		    }
		%>
	</table><p>
	���ﳵ����<%=cart.getNumOfItems() %>��ͼ��
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="showcart.jsp">�鿴���ﳵ</a>

</body>
</html>
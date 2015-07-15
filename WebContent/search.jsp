<%@page import="kaizong.jee.web01.b.BookBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ include file="bookstore_common.jsp"%>
<jsp:useBean id="cart" scope="session"
	class="kaizong.jee.web01.b.CartBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>��ӭ�����������</title>
</head>
<body>
	<jsp:include page="additem.jsp" flush="false" />
	<%
	    String strKeyword = request.getParameter("keyword");
	    if (null == strKeyword || "".equals(strKeyword)) {
	        response.sendRedirect("catalog.jsp");
	        return;
	    }
	    Collection cl = bookdb.searchBook(strKeyword);
	    if (cl.size() <= 0) {
	        out.println("�Բ���û���ҵ�����������ͼ�顣");
	        out.println("<a href=\"bookstore_index.jsp\">����</a>");
	        return;
	    }
	%>
	<table>
		<tr>
			<th>����</th>
			<th>�۸�</th>
			<th>�鿴</th>
			<th>����</th>
		</tr>
		<%
		    Iterator it = cl.iterator();
		    while (it.hasNext()) {
		        BookBean book = (BookBean) it.next();
		        String title = book.getTitle();
		        int bookId = book.getId();
		        float price = book.getPrice();
		%>
		<tr>
			<td><a href="bookinfo.jsp?id=<%=bookId%>>">��<%=title%>��
			</a></td>
			<td><%=price%></td>
			<td><a href="bookinfo.jsp?id=<%=bookId%>">��ϸ��Ϣ</a></td>
			<td><a href="search.jsp?keyword=<%=strKeyword%>&add=<%=bookId%>">���빺�ﳵ</a></td>
		</tr>
		<%
		    }
		%>
	</table><p>
	���ﳵ������<%=cart.getNumOfItems() %>��ͼ��
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="showcart.jsp">�鿴���ﳵ</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="bookstore_index.jsp">�ص���ҳ</a>
</body>
</html>





















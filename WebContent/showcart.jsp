<%@page import="kaizong.jee.web01.b.CartItemBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="kaizong.jee.web01.b.BookBean"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ include file="bookstore_common.jsp"%>
<<jsp:useBean id="cart" scope="session"
	class="kaizong.jee.web01.b.CartBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>��ӭ�����������</title>
</head>
<body>
	<%
	    request.setCharacterEncoding("GB2312");
	    String action = request.getParameter("action");
	    if (action != null && action.equals("�����޸�")) {
	        String strItemNum = request.getParameter("itemnum");
	        if (null == strItemNum || "".equals(strItemNum)) {
	            throw new ServletException("�Ƿ��Ĳ���");
	        }

	        int itemNum = Integer.parseInt(strItemNum);
	        for (int i = 0; i < itemNum; i++) {
	            String strNum = request.getParameter("num_" + i);
	            String strBookId = request.getParameter("book_" + i);

	            int quantity = Integer.parseInt(strNum);
	            int bookId = Integer.parseInt(strBookId);

	            boolean bEnough = bookdb.isAmountEnough(bookId, quantity);
	            if (bEnough) {
	                cart.setItemNum(new Integer(bookId), quantity);
	            } else {
	                BookBean book = bookdb.getBook(bookId);
	                out.println("<font color=\"red\" size=\"4\">");
	                out.println("��" + book.getTitle() + "��");
	                out.println("�Ŀ������ֻ��" + book.getAmount() + "�������������������<p>");
	                out.println("</font>");
	            }
	        }
	    }
	%>

	<%
	    Collection cl = cart.getItems();
	    if (cl.size() <= 0) {
	        out.println("���ﳵ��û��ͼ��<p>");
	%>
	<a href="bookstore_index.jsp">��������</a>
	<%
	    return;
	    }

	    Iterator it = cl.iterator();
	%>

	<form name="theform" action="showcart.jsp" method="post">
		<table border="1">
			<tr>
				<th>����</th>
				<th>�۸�</th>
				<th>����</th>
				<th>С��</th>
				<th>ȡ��</th>
			</tr>
			<%
			    int i = 0;
			    while (it.hasNext()) {
			        CartItemBean cartItem = (CartItemBean) it.next();
			        BookBean book = cartItem.getBook();
			        int bookId = book.getId();
			        String fieldNum = "num_" + i;
			        String fieldBook = "book_" + i;
			%>
			<tr>
				<td><%=book.getTitle()%></td>
				<td><%=book.getPrice()%></td>
				<td><input type="text" name="<%=fieldNum%>"
					value="<%=cartItem.getQuantity()%>" size="2" /> <input
					type="hidden" name="<%=fieldBook%>" value="<%=bookId%>" /></td>
				<td><%=cartItem.getItemPrice()%></td>
				<td><a href="delitem.jsp?id=<%=bookId%>">ɾ��</a></td>
			</tr>
			<%
			    i++;
			    }
			%>
			<tr>
			   <td>�ϼ�</td>
			   <td colspan="4"><%=cart.getTotalPrice() %></td>
			</tr>
		</table>
		<p>
		<input type="hidden" name="itemnum" value="<%=i%>"/>
		<input type="submit" name="action" value="�����޸�"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		�����������
	</form>
</body>
</html>
















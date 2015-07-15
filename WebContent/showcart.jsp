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
<title>欢迎光临网上书店</title>
</head>
<body>
	<%
	    request.setCharacterEncoding("GB2312");
	    String action = request.getParameter("action");
	    if (action != null && action.equals("保存修改")) {
	        String strItemNum = request.getParameter("itemnum");
	        if (null == strItemNum || "".equals(strItemNum)) {
	            throw new ServletException("非法的参数");
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
	                out.println("《" + book.getTitle() + "》");
	                out.println("的库存数量只有" + book.getAmount() + "本，请调整购买数量！<p>");
	                out.println("</font>");
	            }
	        }
	    }
	%>

	<%
	    Collection cl = cart.getItems();
	    if (cl.size() <= 0) {
	        out.println("购物车中没有图书<p>");
	%>
	<a href="bookstore_index.jsp">继续购物</a>
	<%
	    return;
	    }

	    Iterator it = cl.iterator();
	%>

	<form name="theform" action="showcart.jsp" method="post">
		<table border="1">
			<tr>
				<th>书名</th>
				<th>价格</th>
				<th>数量</th>
				<th>小计</th>
				<th>取消</th>
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
				<td><a href="delitem.jsp?id=<%=bookId%>">删除</a></td>
			</tr>
			<%
			    i++;
			    }
			%>
			<tr>
			   <td>合计</td>
			   <td colspan="4"><%=cart.getTotalPrice() %></td>
			</tr>
		</table>
		<p>
		<input type="hidden" name="itemnum" value="<%=i%>"/>
		<input type="submit" name="action" value="保存修改"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		进入结算中心
	</form>
</body>
</html>
















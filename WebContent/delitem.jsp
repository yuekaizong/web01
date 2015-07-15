<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<jsp:useBean id="cart" scope="session" class="kaizong.jee.web01.b.CartBean"/>	
<%
  String strBookId = request.getParameter("id");
  if(null == strBookId || "".equals(strBookId)){
      response.sendRedirect("bookstore_index.jsp");
      return;
  }else{
      cart.deleteItem(Integer.valueOf(strBookId));
      response.sendRedirect("showcart.jsp");
  }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<%@page pageEncoding="GBK"%>
<%
    String content = request.getParameter("content");
 out.println("这是JSP页面测试<p>");
 out.println("内容是："+content);
%>
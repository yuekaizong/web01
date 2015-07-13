<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%
    request.setCharacterEncoding("GB2312");
%>
<jsp:useBean id="user" scope="session"
	class="kaizong.jee.web01.b.UserBean">
	<jsp:setProperty name="user" property="*"/>
	<jsp:setProperty name="user" property="email" param="mail"/>
</jsp:useBean>
×¢²á³É¹¦£¡
<%@page import="kaizong.jee.web01.b.UserCheckBean"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%
    request.setCharacterEncoding("GB2312");
%>
<jsp:useBean id="user" scope="session"
	class="kaizong.jee.web01.b.UserBean" />
<jsp:setProperty property="*" name="user" />

<%
    UserCheckBean uc = new UserCheckBean(user);
    if (uc.validate()) {
%>
<<jsp:forward page="welcome.jsp" />
<%
    }
    else{
        out.println("用户名或者密码错误，请<a href=\"login_mode1.html\">重新登录</a>");
    }
%>
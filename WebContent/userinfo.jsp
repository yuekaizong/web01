<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<jsp:useBean id="user" scope="session"
	class="kaizong.jee.web01.b.UserBean" />
你的名字：<jsp:getProperty name="user" property="name" /><br>
你的性别：<%
    int sex = user.getSex();
    if (1 == sex)
        out.println("男");
    else if (0 == sex)
        out.println("女");
%><br>
你的学历：<jsp:getProperty name="user" property="education" /><br>
你的E-mail:<jsp:getProperty name="user" property="email" /><br>

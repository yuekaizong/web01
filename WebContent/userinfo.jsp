<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<jsp:useBean id="user" scope="session"
	class="kaizong.jee.web01.b.UserBean" />
������֣�<jsp:getProperty name="user" property="name" /><br>
����Ա�<%
    int sex = user.getSex();
    if (1 == sex)
        out.println("��");
    else if (0 == sex)
        out.println("Ů");
%><br>
���ѧ����<jsp:getProperty name="user" property="education" /><br>
���E-mail:<jsp:getProperty name="user" property="email" /><br>

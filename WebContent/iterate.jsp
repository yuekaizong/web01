<%@page import="kaizong.jee.web01.b.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ taglib uri="/mytag" prefix="my"%>

<%
    ArrayList al = new ArrayList();
    UserBean user1 = new UserBean("����", 24, "��", "zhangsan@sina.com");
    UserBean user2 = new UserBean("����", 26, "˶ʿ", "lisi@163.com");
    UserBean user3 = new UserBean("����", 30, "��ʿ", "wangwu@netease.com");

    al.add(user1);
    al.add(user2);
    al.add(user3);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<caption>�û���Ϣ</caption>
		<tr>
			<th>����</th>
			<th>����</th>
			<th>ѧ��</th>
			<th>����</th>
		</tr>
		<my:iterate items="<%=al%>" var="user">
 			<tr>
<%-- 				<td><jsp:getProperty property="name" name="user" /></td> --%>
				<td>${user["age"]}</td>
				<td>${user.education}</td>
				<td><jsp:getProperty property="email" name="user" /></td>
			</tr> 
		</my:iterate>
	</table>
</body>
</html>
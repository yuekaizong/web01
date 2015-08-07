<%@page import="kaizong.jee.web01.b.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ taglib uri="/mytag" prefix="my"%>

<%
    ArrayList al = new ArrayList();
    UserBean user1 = new UserBean("张三", 24, "大本", "zhangsan@sina.com");
    UserBean user2 = new UserBean("李四", 26, "硕士", "lisi@163.com");
    UserBean user3 = new UserBean("王五", 30, "博士", "wangwu@netease.com");

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
		<caption>用户信息</caption>
		<tr>
			<th>姓名</th>
			<th>年龄</th>
			<th>学历</th>
			<th>邮箱</th>
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
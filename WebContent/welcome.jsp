<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h6>Hello Web01</h6>
	<P>The time on the server is ${serverTime}.</P>
	<jsp:useBean id="user" scope="session"
		class="kaizong.jee.web01.b.UserBean" />
	»¶Ó­Äã
	<jsp:getProperty property="name" name="user" />
</body>
</html>
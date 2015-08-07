<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ taglib uri="/myfuncs" prefix="myfn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
</head>
<body>
	欢迎你， ${myfn:toGBK(param.username, "ISO-8859-1") }!
	<br>
	<c:out value="${customer.address.city} }" default="unknown"></c:out>
	<c:catch var="exception">
		<%
		    int i = 5;
		        int j = 0;
		        int k = i / j;
		%>
	</c:catch>
	<c:out value="${exception} }" />
	<p>

		<c:out value="${exception.message} }"></c:out>
		C&{exception.message}相当于调用exception.getMessage(); <br>

		<c:import url="/hello.jsp" context="/Web01" />

		<br>
		<c:import url="login.html"></c:import>
		<br>
		<c:import url="/max.jsp" context="/Web01">
			<c:param name="num1" value="23"></c:param>
			<c:param name="num2" value="24"></c:param>
		</c:import>

		<br>

		<c:forEach var="i" begin="100" end="110">
		   ${i}
		</c:forEach>
</body>
</html>
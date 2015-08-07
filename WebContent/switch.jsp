<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ taglib uri="/mytag" prefix="my"%>
<%
    String name = request.getParameter("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
</head>
<body>
    <my:Hello/><br>

	<my:switch>
		<my:case cond="<%= \"zhansan\".equals(name)%>">
			<%
			    out.println(name + " is manager!");
			%>
		</my:case>
		<my:case cond="<%= name.equals(\"lisi\")%>">
			<%
			    out.println(name + " is salesman!");
			%>
		</my:case>
		<my:default>
			<%
			    out.println(name + " is employee!");
			%>
		</my:default>
	</my:switch>
</body>
</html>
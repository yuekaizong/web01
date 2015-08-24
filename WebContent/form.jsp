<%@page import="java.util.ResourceBundle"%>
<%
    ResourceBundle bundle ==(ResourceBundle)session.getAttribute("resource");
%>
<html>
<head>
<title><%=bundle.getString("title")%></title>
</head>

<body>
	<form action="" method="post">
		<%=bundle.getString("name")%>
		<input type="text" name="name"> <br>
		<%=bundle.getString("email")%><input type="text" name="email">
		<p>
			<input type="reset" value="<%=bundle.getString("reset")%>"> <input
				type="submit" value="<%=bundle.getString("submit")%>">
	</form>
</body>
</html>
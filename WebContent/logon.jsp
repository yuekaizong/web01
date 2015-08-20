<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filter</title>
</head>
<body>
	<form action="logon.jsp?action=logon" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="origin_uri"
					value="${requestScope.origin_uri }"></td>
			</tr>
			<tr>
				<td><input type="reset" value="重填"></td>
				<td><input type="submit" value="提交"></td>
			</tr>

		</table>
	</form>
</body>
</html>
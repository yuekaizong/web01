<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GB18030"%>
<%@ taglib uri="/mytag" prefix="my"%>	
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
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
	欢迎你
	<jsp:getProperty property="name" name="user" />
	
	<br>
	<my:welcome name="标哥">
	   欢迎你来带个人网站
	</my:welcome>
	
	<br>
	<tags:welcome>
	  <jsp:attribute name="user">
	   ${param.name }
	  </jsp:attribute>
	  <jsp:body>
	    欢迎你来到biaoge哥个人网站
	  </jsp:body>
	</tags:welcome>
</body>
</html>
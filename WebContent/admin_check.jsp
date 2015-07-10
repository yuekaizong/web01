<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>

<%@ include file="util.jsp"%>

<%
    request.setCharacterEncoding("gb2312");
    String name = request.getParameter("name");
    String pwd = request.getParameter("password");
    
    if(null == name || null == pwd){
        response.sendRedirect("admin_login.html");
        return;
    }
    
    name = toHtml(name.trim());
    pwd = toHtml(pwd.trim());
    
    if(name.equals("") || pwd.equals("")){
        out.println("用户名和密码不能为空，请重新<a href=admin_login.html>登录</a>");
        return;
    }
    
    if(name.equals("yuekaizong") || pwd.equals("123456")){
        session.setAttribute("admin", "true");
        response.sendRedirect("admin_index.jsp");
    }else{
        out.println("用户名或密码错误，请重新<a href=admin_login.html>登录</a>");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
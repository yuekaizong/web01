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
        out.println("�û��������벻��Ϊ�գ�������<a href=admin_login.html>��¼</a>");
        return;
    }
    
    if(name.equals("yuekaizong") || pwd.equals("123456")){
        session.setAttribute("admin", "true");
        response.sendRedirect("admin_index.jsp");
    }else{
        out.println("�û������������������<a href=admin_login.html>��¼</a>");
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
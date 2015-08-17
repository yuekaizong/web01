<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>XML标签库</title>
</head>
<body>
	<h1>核心操作</h1>
	<h5>x:parse</h5>
	<c:import url="student.xml" var="doc" />
	<x:parse doc="${doc }" var="stuXml" />
	<x:out select="$stuXml/students/student/name" />
	<br>
	<h5>x:out</h5>
	<x:out select="$stuXml//age" />
	<h5>x:set</h5>
	<x:set select="$stuXml/students/student" var="student" />
	<x:out select="$student/name" />
	<h1>控制流程</h1>
	<h5>x:if</h5>
	<x:if select="$stuXml//student[@id='1']">
	   姓名为：<x:out select="$stuXml/students/student/name" />
	</x:if>

	<%-- 	<x:if select="$stuXml//student[@id='2']" var="boolId" />
	<c:if test="${boolId }">
	   编号2的学生存在
	</c:if> --%>
	<h5>x:forEach</h5>
	<x:forEach var="student" select="$stuXml//student" varStatus="status">
	   ${status.index } : <x:out select="$student/name" />  :<x:out
			select="$student/age" />
	</x:forEach>

	<h1>转换操作</h1>
	<h5>x:transform</h5>
	<%-- 	<c:import url="employess.xml" var="doc" />
	<c:import url="employess.xsl" var="xslt"/>
	<x:transform doc="${doc }" xslt="${xslt }" var="empResult"/>
	<x:out select="$empResult/table/tr/td"/> --%>

	<h1>Functions标签库</h1>
	<h5>fn:contains</h5>
	${fn:contains("zhangsan", "san") } ${fn:contains("LISI", "li") }
	<h5>fn:containsIgnoreCase</h5>
	${fn:containsIgnoreCase("zhangsan", "san")}
	${fn:containsIgnoreCase("LISI", "li")}
	<h5>fn:startsWith</h5>
	${fn:startsWith("zhangsan", "zh") } ${fn:startsWith("zhangsan", "san") }
	<h5>fn:endsWith</h5>
	${fn:endsWith("zhangsan","n") } ${fn:endsWith("zhangsan","a") }
	<h5>fn:indexOf</h5>
	${fn:indexOf("zhangsan", "san") } ${fn:indexOf("zhangsan", "") }
	<h5>fn:replace</h5>
	${fn:replace("hello, zhangsan", "zhangsan", "lisi") }
	${fn:replace("hello, zhangsan", ", zhangsan", "" )}
	<h5>fn:substring</h5>
	${fn:substring("hello, lisi", 0, 5) }
	${fn:substring("hello, lisi", 7, -1) }

	<h5>fn:substringBefore</h5>
	${fn:substringBefore("hello, lisi", ",lisi" }
	<h5>fn:substringAfter</h5>
	${fn:substringAfter("hello,lisi", "hello,")}
	<h5>fn:split</h5>
<%-- 	<c:set value='${fn:split("Welcome you to China", " ")}' var="welcome" />
	<c:forEach items="${welcome }" var="token">
	  ${token }
	</c:forEach> --%>

<%-- 	<c:set value='${fn:split("welcome you to China", " ") }' var="welcome" />
	<%=((String[]) pageContext.getAttribute("welcome"))[0]%> --%>
	<h5>fn:join</h5>
	<%
	    String[] welcome2 = new String[] {
	            "Welcome", "you", "to", "China"
	    };
	    pageContext.setAttribute("welcome2", welcome2);
	%>
	${fn:join(welcome2, " ") }
	<br>
	<%
	    String[] welcome3 = new String[] {
	            "Welcome", "you", "to", "china"
	    };
	    pageContext.setAttribute("welcome3", welcome3);
	%>
	${fn:join(welcome3, "-") }

	<h5>fn:toLowerCase</h5>
	${fn:toLowerCase("ZhangSan" )}

	<h5>fn:toUpperCase</h5>
	${fn:toUpperCase("ZhangSan") }

	<h5>fn:trim</h5>
	${fn:trim(" zhangsan ") }

	<h5>fn:escapeXml</h5>
	${fn:escapeXml("<br>") }
	
	<h5>fn:length</h5>
	${fn:length("zhangsan") }
	<%
	ArrayList arrayList = new ArrayList();
	arrayList.add("zhangsan");
	arrayList.add("lisi");
	arrayList.add("wangwu");
	pageContext.setAttribute("employees", arrayList);
	%>
	
	${fn:length(employess) }
</body>
</html>






















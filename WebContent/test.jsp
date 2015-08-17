<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"%>
<%@ taglib uri="/myfuncs" prefix="myfn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>标签库</title>
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

		<c:import url="/hello.jsp" context="/Web01" charEncoding="GB2312" />

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

		<br>
		<c:forTokens items="zhangsan,lisi,wangwu" delims="," var="name">
	${name }
		</c:forTokens>

		<br>

		<fmt:bundle basename="MyResource.properties" prefix="form.table">
			<fmt:message key="name" />
			<fmt:message key="email" />
		</fmt:bundle>

		<br>

		<fmt:formatNumber value="12.3" pattern=".000"></fmt:formatNumber>

		<br>
		<fmt:formatNumber value="123456.7891" pattern="#,#00.0#" />

		<br>

		<fmt:parseNumber value="456.92" type="number" integerOnly="true" />

		<br>

		<fmt:parseNumber value="123,456.92" type="number" />

		<br>

		<fmt:parseNumber value="72%" type="percent" />

		<br>

		<fmt:formatDate value="<%=new java.util.Date()%>" type="date" />

		<br>

		<fmt:formatDate value="<%=new java.util.Date()%>" type="time" />

		<br>
		<fmt:formatDate value="<%=new java.util.Date()%>" type="both" />

		<br>

		<fmt:parseDate value="2005-5-27 9:36:25" type="both"
			dateStyle="default" timeStyle="default" />
		<br>
	<h1>SQL</h1>
	<br>
	<sql:setDataSource url="jdbc:sqlite:book.db" driver="org.sqlite.JDBC" />




	<%-- 	<sql:update>
	  insert into bookinfo (id, title, author, bookconcern, publish_date, price, amount) values
	  (?,?,?,?,?,?,?)
	  <sql:param>8</sql:param>
		<sql:param>Java实例入门</sql:param>
		<sql:param>小明</sql:param>
		<sql:param>小明出版社</sql:param>
		<sql:param>2005-05-23</sql:param>
		<sql:param>55.00</sql:param>
		<sql:param>20</sql:param>
	</sql:update> --%>

	<sql:query sql="select * from bookinfo" var="books" />
	<table>
		<c:forEach var="book" items="${books.rows }">
			<tr>
				<td><c:out value="${ book.title }"></c:out></td>
				<td><c:out value="${ book.author }"></c:out></td>
				<td><c:out value="${ book.price }"></c:out></td>
			</tr>
		</c:forEach>
	</table>

	<table border="1">
		<tr>
			<th>${books.columnNames[1] }</th>
			<th>${books.columnNames[2] }</th>
			<th>${books.columnNames[5] }</th>
		</tr>

		<c:forEach var="book" items="${books.rowsByIndex }">
			<tr>
				<td>${book[1] }</td>
				<td>${book[2] }</td>
				<td>${book[5] }</td>
			</tr>
		</c:forEach>
	</table>

	<h6>sql:query sql:param</h6>

	<sql:query var="books">
	   select * from bookinfo where id = ?
	   <sql:param value="1" />
	</sql:query>
	<table border="1">
		<tr>
			<th>${books.columnNames[0] }</th>
			<th>${books.columnNames[1] }</th>
			<th>${books.columnNames[2] }</th>
			<th>${books.columnNames[3] }</th>
			<th>${books.columnNames[4] }</th>
			<th>${books.columnNames[5] }</th>
			<th>${books.columnNames[6] }</th>
		</tr>
		<c:forEach var="book" items="${books.rowsByIndex }">
			<tr>
				<td>${book[0] }</td>
				<td>${book[1] }</td>
				<td>${book[2] }</td>
				<td>${book[3] }</td>
				<td>${book[4] }</td>
				<td>${book[5] }</td>
				<td>${book[6] }</td>
			</tr>
		</c:forEach>
	</table>

	<br>

	<sql:setDataSource dataSource="jdbc/bookstore" var="ds" />
	<sql:update sql="update bookinfo set amount = amount + 1 where id = 1"
		dataSource="${ds }" />

	<br>
	<h5>sql:dateParam</h5>
	<fmt:parseDate value="2004-06-01" type="date" dateStyle="default"
		var="publish_date" />
	<sql:query var="books">
	select * from bookinfo where publish_date > ?
	<sql:dateParam value="${publish_date}" type="date" />
	</sql:query>

	<table border="1">
		<c:forEach var="book" items="${books.rowsByIndex }">
			<tr>
				<th>${book[1] }</th>
				<th>${book[2] }</th>
				<th>${book[3] }</th>
				<th>${book[4] }</th>
				<th>${book[5] }</th>
				<th>${book[6] }</th>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h5>sql:transcation</h5>

	<sql:transaction dataSource="${ds }" isolation="serializable">
		<sql:update sql="update bookinfo set amount = ? where id = 3">
			<sql:param>7</sql:param>
		</sql:update>

		<sql:update sql="update account set  balance = ? where userid = ?">
			<sql:param>226.00</sql:param>
			<sql:param>甲</sql:param>
		</sql:update>
	</sql:transaction>

	<sql:query var="accounts" sql="select * from account" />
	<table border="2">
	     <tr>
	        <th>${books.columnNames[0] }</th>
	        <th>${books.columnNames[1] }</th>
	     </tr>
	  <c:forEach var="account" items="${accounts.rowsByIndex }">
	      <tr>
	         <td>${account[0] }</td>
	         <td>${account[1] }</td>
	      </tr>
	  </c:forEach>
	</table>

</body>

</html>
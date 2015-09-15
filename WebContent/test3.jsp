<%@page import="kaizone.songmaya.jsyl.util.TokenProcessor"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>在客户端避免表单重复提交</title>
<script type="text/javascript">

  var checkSubmitFlg = true;
  function checkSubmit(){
	  if(true == checkSubmitFlg){
		  document.theForm.submit();
		  checkSubmitFlg = false;
	  }else{
		  alert("你已经提交了表单，请不要重复提交了！！！");
	  }
  }

</script>
</head>
<body>
<%
  TokenProcessor processor = TokenProcessor.getInstance();
  String token = processor.getToken(request);
  
%>
	<form action="handler" method="post" name="theForm">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="user"></td>
			</tr>
			<tr>
				<td>邮件地址：</td>
				<td><input type="text" name="email">
				  <input type="hidden" name="kaizone.songmaya.token" value="<%=token%>">
				</td>
			</tr>
			<tr>
				<td><input type="reset" value="重填"></td>
				<td><input type="button" name="btnSubmit" value="提交" onclick="checkSubmit();"></td>
			</tr>
		</table>
	</form>
	<br>
	token = <%=token %>

</body>
</html>
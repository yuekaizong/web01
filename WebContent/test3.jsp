<%@page import="kaizone.songmaya.jsyl.util.TokenProcessor"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ڿͻ��˱�����ظ��ύ</title>
<script type="text/javascript">

  var checkSubmitFlg = true;
  function checkSubmit(){
	  if(true == checkSubmitFlg){
		  document.theForm.submit();
		  checkSubmitFlg = false;
	  }else{
		  alert("���Ѿ��ύ�˱����벻Ҫ�ظ��ύ�ˣ�����");
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
				<td>�û�����</td>
				<td><input type="text" name="user"></td>
			</tr>
			<tr>
				<td>�ʼ���ַ��</td>
				<td><input type="text" name="email">
				  <input type="hidden" name="kaizone.songmaya.token" value="<%=token%>">
				</td>
			</tr>
			<tr>
				<td><input type="reset" value="����"></td>
				<td><input type="button" name="btnSubmit" value="�ύ" onclick="checkSubmit();"></td>
			</tr>
		</table>
	</form>
	<br>
	token = <%=token %>

</body>
</html>
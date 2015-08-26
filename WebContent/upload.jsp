<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>upload</title>
</head>
<body>
	<%
	    DiskFileUpload dfu = new DiskFileUpload();
	    //设置上传数据的最大数据为10M
	    dfu.setSizeMax(0xA00000);
	    //设置内存缓冲区的阀值为512k
	    dfu.setSizeThreshold(0x80000);

	    //设置临时存储文件为目录为：E：\fileupload
	    File repositroy = new File("E:\\fileupload");
	    if(!repositroy.exists()){
	        repositroy.mkdir();
	    }
	    dfu.setRepositoryPath(repositroy.getAbsolutePath());

	    //得到FileItem对象的列表
	    List fileitems = dfu.parseRequest(request);
	    Iterator it = fileitems.iterator();
	%>
	<table cellpadding="3" border="1">
		<%
		    while (it.hasNext()) {
		        FileItem item = (FileItem) it.next();
		        if (!item.isFormField()) {
		            String name = item.getName();
		            long size = item.getSize();
		            if ((name == null || name.equals("")) && size == 0)
		                continue;
		%>
		<tr>
			<td><%=item.getName()%></td>
			<td><%=item.getSize()%></td>
		</tr>
		<%
		    //保存上传的文件指定到目录
		            File dir = new File("E:\\UploadFile");
		            if (!dir.exists()) {
		                dir.mkdir();
		            }
		            //如果浏览器创送的文件名是全路径，则取出来文件名
		            int index = name.lastIndexOf(File.separator);
		            if (index > 0)
		                name = name.substring(index + 1, name.length());
		            File file = new File(dir, name);
		            item.write(file);
		        }
		        else {
		%>
		<tr>
			<td><%=item.getFieldName()%></td>
			<td><%=item.getString()%></td>
		</tr>
		<%
		    }
		    }
		%>
	</table>

</body>
</html>
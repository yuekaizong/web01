<%@page import="java.io.File"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Upload2</title>
</head>
<body>
	<%
	    DiskFileUpload dfu = new DiskFileUpload();
	    //设置上传数据的最大数据为10M
	    dfu.setSizeMax(0XA00000);

	    //设置内存缓存区的阀值为512k
	    dfu.setSizeThreshold(0x80000);

	    //设置临时存储文件为目录为：E：\fileupload
	    File repositroy = new File("E:\\fileupload");
	    if(!repositroy.exists()){
	        repositroy.mkdir();
	    }
	    dfu.setRepositoryPath(repositroy.getAbsolutePath());

	    //得到FileItem对象的列表
	    List fileItems = dfu.parseRequest(request);
	    Iterator it = fileItems.iterator();

	    Context ctx = new InitialContext();
	    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
	    Connection conn = ds.getConnection();
	    PreparedStatement pstmt = conn
	            .prepareStatement("insert into uploadfile (filename,filesize,data) values (?,?,?)");

	    while (it.hasNext()) {
	        FileItem item = (FileItem) it.next();
	        if (!item.isFormField()) {
	            String name = item.getName();
	            int index = name.lastIndexOf(File.separator);
	            if (index > 0)
	                name = name.substring(index + 1, name.length());

	            long size = item.getSize();
	            if ((name == null || name.equals("")) && size == 0)
	                continue;
	            pstmt.setString(1, name);
	            pstmt.setInt(2, (int) size);
	            // pstmt.setBinaryStream(3, item.getInputStream(), (int)size);
	            // pstmt.setBlob(3, item.getInputStream(), size);
	            File dir = new File("E:\\UploadFile");
	            if (!dir.exists()) {
	                dir.mkdir();
	            }

	            File file = new File(dir, name);
	            item.write(file);
	            pstmt.setString(3, file.getAbsolutePath());
	            
	            pstmt.executeUpdate();
	        }
	    }
	    if (pstmt != null) {
	        pstmt.close();
	        pstmt = null;
	    }
	    if (conn != null) {
	        conn.close();
	        conn = null;
	    }
	    out.println("上传成功！");
	%>

</body>
</html>
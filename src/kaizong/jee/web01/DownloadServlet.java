
package kaizong.jee.web01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DownloadServlet extends HttpServlet {

    private DataSource ds = null;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String strID = request.getParameter("id");
        if (null == strID || strID.equals(""))
            return;

        File file = null;
        InputStream is = null;
        String fileName = null;
        int fileSize = 0;

        if (strID.equals("111")) {
            file = new File(
                    "D:/develop/tools/extools/eclipse/spring-tool-suite/workspace/Web01/WebContent/01.html");
            is = new FileInputStream(file);
            fileName = file.getName();
            fileSize = (int) file.length();
        }
        else
        {
            int id = Integer.parseInt(strID);
            try {
                conn = ds.getConnection();
                pstmt = conn.prepareStatement("select * from uploadfile where id = ?");
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    fileName = rs.getString("filename");
                    fileSize = rs.getInt("filesize");
                    String path = rs.getString("data");
                    File datafile = new File(path);
                    is = new FileInputStream(datafile);
                }
                else
                {
                    response.setContentType("text/html;charset=gb2312");
                    PrintWriter out = response.getWriter();
                    out.println("没有找到下载的文件，请联系");
                    out.println("<a href=\"hello.jsp\">管理员</a>");
                    out.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 设置下载文件使用的报头域
        response.setContentType("application/x-msdownload");
        String str = "attachment; filename=" + fileName;
        response.setHeader("content-Disposition", str);
        response.setContentLength(fileSize);

        // 得到响应对象的输出流，用于向客户端输出二进制数据
        ServletOutputStream sos = response.getOutputStream();
        byte[] data = new byte[2048];
        int len = 0;
        while ((len = is.read(data)) > 0) {
            sos.write(data, 0, len);
        }
        is.close();
        sos.close();
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

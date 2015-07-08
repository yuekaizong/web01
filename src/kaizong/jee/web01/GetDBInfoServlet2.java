
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GetDBInfoServlet2 extends HttpServlet {

    private String url;
    private String user;
    private String password;

    @Override
    public void init() throws ServletException {
        super.init();
        //
        // ServletContext sc = getServletContext();
        // String driverClass = sc.getInitParameter("driverClass");
        // url = sc.getInitParameter("url");
        // user = sc.getInitParameter("user");
        // password = sc.getInitParameter("password");
        //
        // try {
        // Class.forName(driverClass);
        // } catch (ClassNotFoundException e) {
        // e.printStackTrace();
        // }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
            conn = ds.getConnection();

            // conn = DriverManager.getConnection(url);
            resp.setContentType("text/html;charset=gb2312");
            PrintWriter out = resp.getWriter();
            out.println("<html><head>");
            out.println("<title>数据库表的信息</title>");
            out.println("</head><body>");

            String tableName = req.getParameter("tableName");
            if (null == tableName || tableName.equals("")) {
                DatabaseMetaData dbMeta = conn.getMetaData();
                rs = dbMeta.getTables(null, null, null, new String[] {
                        "TABLE"
                });
                out.println("<form action=\"getdbinfo\" method=\"get\">");
                out.println("<select size=1 name=tableName>");
                while (rs.next()) {
                    out.println("<option value =" + rs.getString("TABLE_NAME") + ">");
                    out.println(rs.getString("TABLE_NAME"));
                    out.println("</option>");
                }
                out.println("</select><p>");
                out.println("<input type=\"submit\" value=\"提交\">");
                out.println("</form>");
            }
            else
            {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("select * from " + tableName);
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                out.println("<table border=1>");
                out.println("<caption>表的结构</caption>");
                out.println("<tr><th>字段名</th><th>字段类型</th><th>最大字符宽度</th><tr>");

                ArrayList<String> al = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    out.println("<tr>");
                    String columnName = rsMeta.getColumnName(i);
                    out.println("<td>" + columnCount + "</td>");
                    al.add(columnName);
                    out.println("<td>" + rsMeta.getColumnTypeName(i) + "</td>");
                    out.println("<td>" + rsMeta.getColumnDisplaySize(i) + "</td>");
                }
                out.println("</table><p>");

                out.println("<table border=1>");
                out.println("<caption>表中的数据</caption>");
                out.println("<tr>");

                for (int i = 0; i < columnCount; i++) {
                    out.println("<th>" + al.get(i) + "</th>");
                }

                while (rs.next()) {
                    out.println("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        out.println("<td>" + rs.getString(i) + "</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            out.println("</body><html>");
            out.close();
        } catch (NamingException ne) {
            ne.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
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

    }

}

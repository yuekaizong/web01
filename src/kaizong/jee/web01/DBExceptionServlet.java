
package kaizong.jee.web01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.PropertyConfigurator;

public class DBExceptionServlet extends HttpServlet {

    static Logger logger = Logger.getRootLogger();
    static Logger bookLogger = Logger.getLogger("bookstoreLogger");

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
        }

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new UnavailableException("加载数据库驱动失败！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:book.db");
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from jobs where job_id=13");
        } catch (SQLException se) {
            NDC.push(req.getRemoteHost());
//            MDC.put("RemoteAddress", req.getRemoteHost());
            
            logger.warn("数据库操作失败！" + se);
            logger.error("数据库操作失败！" + se);

            bookLogger.error("数据库操作失败！" + se);
            bookLogger.warn("数据库操作失败！" + se);
//            MDC.get("RemoteAddress");
//            MDC.remove("RemoteAddress");
            
            NDC.pop();
            NDC.remove();

            getServletContext().log("ServletContext.log():数据库操作失败！" + se.toString());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "数据库操作出现问题，请联系管理员");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    log("关闭Statement失败!", se);
                    bookLogger.error("关闭Statement失败！", se);
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    log("关闭conn失败!", se);
                    bookLogger.error("关闭数据库连接失败！", se);
                }
                conn = null;
            }
        }
    }
}


package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TradeServlet extends HttpServlet {

    private String url;
    private String user;
    private String password;

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        String driverClass = sc.getInitParameter("driverClass");
        url = sc.getInitParameter("url");
        user = sc.getInitParameter("user");
        password = sc.getInitParameter("password");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new UnavailableException("加载数据库驱动失败！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        resp.setContentType("text/html;charset=gb2312");
        PrintWriter out = resp.getWriter();

        req.setCharacterEncoding("gb2312");

        String userid = req.getParameter("userid");
        String quantity = req.getParameter("quantity");

        if ((null == userid || "".equals(userid)) ||
                (null == quantity || "".equals(quantity))) {

            out.println("错误的请求参数");
            out.close();
        }
        else {
            try {
                conn = DriverManager.getConnection(url);

                conn.setAutoCommit(false);

                stmt = conn.createStatement();
                rs = stmt.executeQuery("select price,amount from bookinfo where id=3");
                rs.next();
                float price = rs.getFloat(1);
                int amount = rs.getInt(2);

                int num = Integer.parseInt(quantity);
                if (amount >= num) {
                    pstmt = conn.prepareStatement("update bookinfo set amount = ? where id = 3");
                    pstmt.setInt(1, amount - num);
                    pstmt.executeUpdate();
                } else {
                    out.println("你所购买的图书库存数量不足");
                    out.close();
                    return;
                }

                pstmt = conn.prepareStatement("select balance from account where userid = ?");
                pstmt.setString(1, userid);
                rs = pstmt.executeQuery();

                rs.next();
                float balance = rs.getFloat(1);
                float totalPrice = price * num;

                if (balance >= totalPrice) {
                    pstmt = conn.prepareStatement("update account set balance=? where userid = ?");
                    pstmt.setFloat(1, balance - totalPrice);
                    pstmt.setString(2, userid);
                    pstmt.executeUpdate();

                } else {
                    conn.rollback();
                    out.println("你的余额不足");
                    out.close();
                    return;
                }

                conn.commit();
                out.println("交易成功！！！");
                out.close();

            } catch (SQLException e) {

                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
                e.printStackTrace();
            } finally
            {
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
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

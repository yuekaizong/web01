
package kaizong.jee.web01.b;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDBBean implements Serializable {

    private DataSource ds = null;

    public BookDBBean() throws NamingException {
        Context ctx = new InitialContext();
        ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void closePreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Collection getBooks() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<BookBean> bookList = new ArrayList<BookBean>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from bookinfo");
            while (rs.next()) {
                BookBean book = new BookBean(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getFloat(6),
                        rs.getInt(7), rs.getString(8));
                bookList.add(book);
            }
            return bookList;
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }
    }

    public BookBean getBook(int bookId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BookBean> bookList = new ArrayList<BookBean>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from bookinfo where id = ?");
            pstmt.setInt(1, bookId);
            rs = pstmt.executeQuery();
            BookBean book = null;
            while (rs.next()) {
                book = new BookBean(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getFloat(6),
                        rs.getInt(7), rs.getString(8));
            }
            return book;
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
            closeConnection(conn);
        }
    }

    public Collection searchBook(String keyword) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<BookBean> bookList = new ArrayList<BookBean>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from bookinfo where title like '%" + keyword + "%'");
            while (rs.next()) {
                BookBean book = new BookBean(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getFloat(6),
                        rs.getInt(7), rs.getString(8));
                bookList.add(book);
            }
            return bookList;
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }
    }

    public boolean isAmountEnough(int bookId, int quantity) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean bEnough = false;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select amount from bookinfo where id=" + bookId);
            while (rs.next()) {
                int amount = rs.getInt(1);
                if (amount >= quantity)
                    bEnough = true;
            }
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }
        return bEnough;
    }

    public synchronized void buyBooks(CartBean cart) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Iterator it = cart.getItems().iterator();
        try {
            conn = getConnection();
            String sql = "update bookinfo set amount = amount - ? where id = ?";
            pstmt = conn.prepareStatement(sql);

            while (it.hasNext()) {
                CartItemBean item = (CartItemBean) it.next();
                BookBean book = item.getBook();
                int bookId = book.getId();
                int quantity = item.getQuantity();
                pstmt.setInt(1, quantity);
                pstmt.setInt(2, bookId);

                pstmt.addBatch();
            }

            pstmt.executeBatch();
        } finally {
            closePreparedStatement(pstmt);
            closeConnection(conn);
        }
    }
}

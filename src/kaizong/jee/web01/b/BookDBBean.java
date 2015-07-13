
package kaizong.jee.web01.b;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

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
    
    public Collection getBooks(int bookId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BookBean> bookList = new ArrayList<BookBean>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from bookinfo where id = ?");
            pstmt.setInt(1, bookId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookBean book = new BookBean(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getFloat(6),
                        rs.getInt(7), rs.getString(8));
                bookList.add(book);
            }
            return bookList;
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
            closeConnection(conn);
        }
    }

}

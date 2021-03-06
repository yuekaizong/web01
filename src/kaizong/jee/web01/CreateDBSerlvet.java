
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDBSerlvet extends HttpServlet {

    private String url;

    @Override
    public void init() throws ServletException {
        String driverClass = getInitParameter("driverClass");
        url = getInitParameter("url");
        System.out.println(url);
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new UnavailableException("数据库驱动加载失败！");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            PrintWriter out = resp.getWriter();

            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            // stmt.executeUpdate("create database bookstore");
            // stmt.executeUpdate("use bookstore");
            // Statement stat = conn.createStatement();
            stmt.executeUpdate("drop table if exists bookinfo;");
            stmt.executeUpdate("create table bookinfo (id INT not null primary key, title VARCHAR(50) not null, author VARCHAR(50) not null, bookconcern "
                    + " VARCHAR(100) not null, publish_date DATE not null, price FLOAT(4, 2) not null, amount SMALLINT, remark VARCHAR(200))");
            stmt.addBatch("insert into bookinfo values (1, 'java从入门到精通', '张三', '张三出版社', '2004-6-1', 34.00, 35, null)");
            stmt.addBatch("insert into bookinfo values (2, 'jsp深入编程', '李四', '李四出版社', '2004-10-1', 56.00, 20, null)");
            stmt.addBatch("insert into bookinfo values (3, 'j2EE高级编程', '王五', '王五出版社', '2005-3-1', 78.00, 10, null)");
            stmt.addBatch("insert into bookinfo values (4, 'xushiwen教你如何玩lol', '许shi文', '王五出版社', '2014-3-1', 89.00, 12, null)");
            stmt.addBatch("insert into bookinfo values (5, 'lol教程', '许世文', '王五出版社', '2014-10-1',120.00, 12, 'rockll, rock')");
            stmt.executeBatch();
            out.println("create bookinfo success!");

            stmt.executeUpdate("drop table if exists guestbook");
            stmt.executeUpdate("create table guestbook("
                    + "gst_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " gst_user VARCHAR(10) not null,"
                    + " gst_title VARCHAR(100) not null, gst_content TEXT,"
                    + " gst_time TIMESTAMP not null default (datetime('now', 'localtime')),"
                    + " gst_ip VARCHAR(15) not null);");
            stmt.executeBatch();
            out.println("create guestbook success!<br>");

            stmt.executeUpdate("drop table if exists logs");
            stmt.executeUpdate("create table logs(log_date DATATIME not null, log_logger VARCHAR(20) not null, log_level VARCHAR(5) not null, log_msg VARCHAR(200) not null);");
            stmt.executeBatch();
            out.println("create logs success!<br>");

            stmt.executeUpdate("drop table if exists uploadfile");
            stmt.executeUpdate("create table uploadfile(id INTEGER PRIMARY KEY AUTOINCREMENT, filename VARCHAR(255) not null, filesize INT not null, data BLOB not null);"); //MEDIUMBLOB
            stmt.executeBatch();
            out.println("create uploadfile success!<br>");
            
           stmt.executeUpdate("drop table if exists users");
           stmt.executeUpdate("create table users(username varchar(15) not null primary key,password varchar(15) not null)");
           stmt.executeBatch();
           out.println("create users success! <br>");
           
           stmt.executeUpdate("drop table if exists roles");
           stmt.executeUpdate("create table roles(username varchar(15) not null, rolename varchar(15) not null, primary key (username, rolename))");
           
           stmt.addBatch("insert into users values('wangwu', '1234')");
           stmt.addBatch("insert into users values('zhaoliu', '1234')"); 
           stmt.addBatch("insert into roles values('wangwu', 'sales')");
           stmt.addBatch("insert into roles values('zhaoliu', 'market')");
           out.println("create roles success!<br>");
            
           
            out.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

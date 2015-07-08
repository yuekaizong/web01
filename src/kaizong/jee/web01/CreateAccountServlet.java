package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAccountServlet extends HttpServlet {

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
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			System.out.println("hihi");
			stmt.executeUpdate("drop table if exists account;");
			stmt.executeUpdate("create table account (userid varchar(10) not null primary key, balance float(6, 2))");

			pstmt = conn.prepareStatement("insert into account values (?, ?)");

			pstmt.setString(1, "甲");
			pstmt.setFloat(2, 500.00f);
			pstmt.executeUpdate();

			pstmt.setString(1, "乙");
			pstmt.setFloat(2, 200.00f);
			pstmt.executeUpdate();

			PrintWriter out = resp.getWriter();
			out.println("success!");
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
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

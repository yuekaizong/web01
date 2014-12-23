package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {

	private String url;

	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		String driverClass = sc.getInitParameter("driverClass");
		url = sc.getInitParameter("url");
		System.out.println("driverClass = "+driverClass);
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
		ResultSet rs = null;

		req.setCharacterEncoding("utf-8");
		String condition = req.getParameter("cond");
		System.out.println("cond ="+condition);
		if (null == condition || "".equals(condition)) {
			System.out.println("ListServlet  doGet..condition is not");
			resp.sendRedirect("search.html");
			return;
		}

		resp.setContentType("text/html;charset=gb2312");
		PrintWriter out = resp.getWriter();

		try {
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			if (condition.equals("all")) {
				rs = stmt.executeQuery("select * from bookinfo");
				printBookInfo(out, rs);
				out.close();
			} else if (condition.equals("precision")) {
				String title = req.getParameter("title");
				String author = req.getParameter("author");
				String bookconcern = req.getParameter("bookconcern");

				if ((null == title || "".equals(title)
						&& (null == author || "".equals(author))
						&& (null == bookconcern || "".equals(bookconcern)))) {
					resp.sendRedirect("search.html");
					return;
				}

				StringBuffer sb = new StringBuffer(
						"select * from bookinfo where ");
				boolean bFlag = false;

				if (!title.equals("")) {
					sb.append("title = " + "'" + title + "'");
					bFlag = true;
				}

				if (!author.equals("")) {
					if (bFlag)
						sb.append("and author = " + "'" + author + "'");
					else {
						sb.append(" author = " + "'" + author + "'");
						bFlag = true;
					}
				}

				if (!bookconcern.equals("")) {
					if (bFlag)
						sb.append("and bookconcern = " + "'" + bookconcern
								+ "'");
					else
						sb.append(" bookconcern = " + "'" + bookconcern + "'");

				}

				rs = stmt.executeQuery(sb.toString());
				printBookInfo(out, rs);
				out.close();
			}

			else if (condition.equals("keyword")) {
				String keyword = req.getParameter("keyword");
				if (null == keyword || keyword.equals("")) {
					resp.sendRedirect("search.html");
					return;
				}

				String strSQL = "select * from bookinfo where title like '%"
						+ keyword + "%'";
				rs = stmt.executeQuery(strSQL);
				printBookInfo(out, rs);
				out.close();
			}

			else {
				resp.sendRedirect("search.html");
				return;
			}

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	private void printBookInfo(PrintWriter out, ResultSet rs)
			throws SQLException {

		out.println("<html><head>");
		out.println("<title>图书</title>");
		out.println("</head><body>");
		out.println("<table border=1><caption>图书信息</caption>");
		out.println("<tr><th>书名</th><th>作者</th><th>出版社</th><th>价格</th><th>发行日期</th></tr>");

		while (rs.next()) {
			System.out.println( rs.getString("title"));
			out.println("<tr>");
			out.println("<td>" + rs.getString("title") + "</td>");
			out.println("<td>" + rs.getString("author") + "</td>");
			out.println("<td>" + rs.getString("bookconcern") + "</td>");
			out.println("<td>" + rs.getString("price") + "</td>");
			out.println("<td>" + rs.getString("publish_date") + "</td>");
			out.println("</tr>");
		}
	}

}

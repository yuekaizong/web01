package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelComeYou extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("gb2312");

		String user = req.getParameter("user");
		String welcomeMsg = getInitParameter("msg");

		resp.setContentType("text/html; charset=gb2312");

		PrintWriter out = resp.getWriter();

		out.println("<html><head><title>");
		out.println("Welcome Page");
		out.println("</title><body>");
		out.println(welcomeMsg + ", " + user);
		out.println("</body></head></html>");
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

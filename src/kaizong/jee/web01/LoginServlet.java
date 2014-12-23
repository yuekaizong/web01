package kaizong.jee.web01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html; charset=gbk");

		String name = req.getParameter("user");
		String pwd = req.getParameter("password");

		if (name != null && pwd != null && name.equals("yuekaizong")
				&& pwd.equals("1005")) {

			resp.sendRedirect("success.html");

		} else {

			resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
					"服务器忙，请稍后再登陆！");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}

}

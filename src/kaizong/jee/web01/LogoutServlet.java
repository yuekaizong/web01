
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kaizong.jee.web01.online.User;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        resp.setContentType("text/html;charset=gb2312");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        session.invalidate();

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>退出登录</title></head>");
        out.println("<body>");
        out.println(user.getName() + ", 你已退出登录<br>");
        out.println("<a href=" + resp.encodeURL("login.html") + ">重新登录</a>");
        out.println("</body></html>");
        out.close();
    }

}

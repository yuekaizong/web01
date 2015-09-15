
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        String randomCode = (String) session.getAttribute("randomCode");
        if (randomCode == null) {
            resp.sendRedirect("random_login.html");
            return;
        }

        String reqRandom = req.getParameter("random");

        req.setCharacterEncoding("GBK");
        resp.setContentType("text/html;charset=GBK");
        PrintWriter out = resp.getWriter();

        if (randomCode.equals(reqRandom)) {
            out.println("验证码匹配!");
        } else {
            out.println("验证码校验失败，请返回重新输入！");
        }
        out.close();
    }

}

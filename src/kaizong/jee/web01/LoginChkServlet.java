
package kaizong.jee.web01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginChkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.setCharacterEncoding("gb2312");
        String name = req.getParameter("user");
        String pwd = req.getParameter("password");

        if (null == name || null == pwd || "".equals(name) || "".equals(pwd)) {
            resp.sendRedirect(resp.encodeRedirectURL("login"));
            return;
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);
            resp.sendRedirect(resp.encodeRedirectURL("greet"));
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}

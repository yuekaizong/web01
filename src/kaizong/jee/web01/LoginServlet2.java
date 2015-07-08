
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("chk".equals(action)) {
            String name = req.getParameter("user");
            String pwd = req.getParameter("password");
            if ((name != null) && (pwd != null)) {
                if (name.equals("zhangsan") && pwd.equals("1234")) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("username=");
                    sb.append(name);
                    sb.append("&password=");
                    sb.append(pwd);

                    Cookie cookie = new Cookie("userinfo", sb.toString());
                    cookie.setMaxAge(1800);
                    resp.addCookie(cookie);
                    resp.sendRedirect("greet2");

                    return;
                }
                else {
                    resp.setContentType("text/html;charset=gb2312");
                    PrintWriter out = resp.getWriter();
                    out.println("用户或者密码错误， 请<a href=login2>重新登录</a>");
                    return;
                }
            }
        }
        else {

            resp.setContentType("text/html; charset=gb2312");

            PrintWriter out = resp.getWriter();

            HttpSession session = req.getSession();
            String user = (String) session.getAttribute("user");

            out.println("<html>");
            out.println("<mate http-equiv=\"Pragma\" content=\"no-cache\">");
            out.println("<head><title>登录页面</title></head>");
            out.println("<body>");

            OutputSessionInfo.printSessionInfo(out, session);

            out.println("<form method=post action=login2?action=chk>");
            out.println("<table>");

            out.println("<tr>");
            out.println("<td>请输入用户名</td>");
            if (null == user) {
                out.println("<td><input type=text name=user></td>");
            } else {
                out.println("<td><input type=text name=user value=" + user + "></td>");
            }
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>请输入密码</td>");
            out.println("<td><input type=password name=password></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><input type=reset value=重置></td>");
            out.println("<td><input type=submit value=登陆></td>");
            out.println("</tr>");

            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}

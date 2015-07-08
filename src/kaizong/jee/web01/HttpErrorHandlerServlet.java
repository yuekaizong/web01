
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpErrorHandlerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=GB2312");
        PrintWriter out = resp.getWriter();

        Integer status_code = (Integer) req.getAttribute("javax.servlet.error.status_code");
        out.println("<html><head><title>错误页面</title></head>");
        out.println("<body>");

        switch (status_code) {
            case 401:

                break;
            case 404:
                out.println("<h2>HTTP状态代码：" + status_code + "</h2>");
                out.println("你所访问的页面并不存在，或者已经被移动到其他的位置。");
                out.println("如有其他问题，请<a href=\"mailto:596760835@qq.com\">联系管理员</a>");
                break;

            default:
                break;
        }
        out.println("</body></html>");
        out.close();

    }
}


package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandlerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=gb2312");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>错误页面</title><head>");
        out.println("<body>");

        out.println("应用程序运行出错。ExceptionHandlerServlet");
        out.println("<p>错误原因：服务器端文件可能被删除。请<a href=\"mailto:596760835@qq.com\">联系管理员</a>");
        out.println("</body></html>");
        out.close();

    }

}

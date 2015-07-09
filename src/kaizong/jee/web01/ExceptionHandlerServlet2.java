
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class ExceptionHandlerServlet2 extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException,
            IOException {

        resp.setContentType("text/html;charset=GB2312");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>错误页面</title></head>");
        out.println("<body>");

        Object excep = req.getAttribute("javax.servlet.error.exception");
        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");

        out.println(uri + "运行错误。");
        out.println("<p>错误原因：" + excep);

        out.println("</body></html>");
        out.close();

    }

}

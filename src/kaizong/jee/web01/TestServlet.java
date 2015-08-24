
package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String content = req.getParameter("content");
        PrintWriter out = resp.getWriter();
        out.println("这是Servlet测试<p>");
        out.println("内容是："+content);
        out.close();
    }

}

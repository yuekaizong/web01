
package kaizong.jee.web01;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        try {
            int a = 5;
            int b = 0;
            int c = a / b;
        } catch (ArithmeticException ae) {
            req.setAttribute("javax.servlet.error.exception", ae);
            req.setAttribute("javax.servlet.error.request_uri", req.getRequestURI());

            RequestDispatcher rd = req.getRequestDispatcher("ExcepHandler2");
            rd.forward(req, resp);
        }
    }

}

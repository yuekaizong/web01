
package kaizong.jee.web01;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kaizong.jee.web01.b.UserBean;
import kaizong.jee.web01.b.UserCheckBean;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        response.setContentType("text/html;charset=GBK");

        String action = request.getParameter("action");

        if (isValidated(request) && !("login".equals(action))) {
            gotoPage("login_mode2.jsp", request, response);
            return;
        }

        if ("login".equals(action)) {
            UserBean user = new UserBean();
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));

            UserCheckBean uc = new UserCheckBean(user);
            
            if(uc.validate()){
               HttpSession session = request.getSession();
               
               session.setAttribute("user", user);
               gotoPage("welcome.jsp", request, response);
            }else{
                gotoPage("loginerr.jsp", request, response);
            }

        }

    }

    private boolean isValidated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
            return true;
        else
            return false;
    }

    private void gotoPage(String targetURL, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(targetURL);
        rd.forward(request, response);

    }

}

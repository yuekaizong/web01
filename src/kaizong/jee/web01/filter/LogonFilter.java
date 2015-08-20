
package kaizong.jee.web01.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogonFilter implements Filter {

    private static final String LOGON_URI = "logon_uri";
    private static final String HOME_URI = "home_uri";

    private String logon_page;
    private String home_page;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logon_page = filterConfig.getInitParameter(LOGON_URI);
        home_page = filterConfig.getInitParameter(HOME_URI);
        if (null == logon_page || null == home_page)
            throw new ServletException("没有指定的登录界面或者主页!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();

        String request_uri = httpReq.getRequestURI();

        String ctxPath = httpReq.getContextPath();

        String uri = request_uri.substring(ctxPath.length());

        if (logon_page.equals(uri)) {
            String strLogon = httpReq.getParameter("action");

            if ("logon".equals(strLogon)) {
                // 如果是提交登录信息，则对用户进行验证
                String name = httpReq.getParameter("name");
                String password = httpReq.getParameter("password");

                if ("zhangsan".equals(name) && "1234".equals(password)) {
                    session.setAttribute("isLogon", "true");

                    session.setAttribute("user", name);

                    String origin_uri = httpReq.getParameter("origin_uri");

                    if (null != origin_uri && !"".equals(origin_uri)) {
                        httpResp.sendRedirect(origin_uri);
                    } else {
                        httpResp.sendRedirect(ctxPath + home_page);
                    }
                    return;
                }
                else
                {
                    String origin_uri = httpReq.getParameter("origin_uri");
                    if (null != origin_uri && !"".equals(origin_uri)) {
                        httpReq.setAttribute("origin_uri", origin_uri);
                    }

                    httpResp.setContentType("text/html;charset=GB2312");
                    PrintWriter out = httpResp.getWriter();
                    out.println("<h2>用户名或密码错误，请重新输入。</h2>");
                    RequestDispatcher rd = httpReq.getRequestDispatcher(logon_page);
                    rd.include(httpReq, httpResp);
                    return;
                }
            }
            else {
                // 如果用户不是提交登录信息，则调用chain,doFilter() 调用登录页面
                chain.doFilter(request, response);
                return;
            }
        } else {
            // 如果访问的不是登录页面，则判断用户是否已经登录
            String isLogon = (String) session.getAttribute("isLogon");
            if ("true".equals(isLogon)) {
                chain.doFilter(request, response);
                return;
            } else {
                // 如果用户没有登录，则将用户的请求URI作为origin_uri熟悉的值 保存到请求对象中
                String strQuery = httpReq.getQueryString();
                if (null != strQuery) {
                    request_uri = request_uri + "?" + strQuery;
                }
                httpReq.setAttribute("origin_uri", request_uri);

                // 将用户请求转发给登录界面
                RequestDispatcher rd = httpReq.getRequestDispatcher(logon_page);
                rd.forward(httpReq, httpResp);
                return;
            }
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}


package kaizong.jee.web01.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SimpleFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=GB2312");
        PrintWriter out = response.getWriter();
        out.println("before doFilter()");
        chain.doFilter(request, response);
        out.println("after doFilter()");
        out.close();
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

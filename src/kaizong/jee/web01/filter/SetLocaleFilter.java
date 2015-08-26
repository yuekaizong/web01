
package kaizong.jee.web01.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLocaleFilter implements Filter {

    private String resourceName = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        resourceName = filterConfig.getInitParameter("resourceName");
        if (null == resourceName) {
            throw new UnavailableException("no define resource");
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        String clientLang = httpReq.getHeader("Accept-Language");
        Locale locale = null;
        ResourceBundle bundle = null;
        if (clientLang.contains("zh-cn")) {
            locale = new Locale("zh", "CN");
            bundle = ResourceBundle.getBundle(resourceName, locale);
            response.setContentType("text/html;charset=GBK");
        }
        else {
            locale = new Locale("en");
            bundle = ResourceBundle.getBundle(resourceName, locale);
            response.setContentType("text/html;charset=ISO-8859-1");
        }
        
        HttpSession session = httpReq.getSession();
        
        session.setAttribute("resource", bundle);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        resourceName = null;
    }

}

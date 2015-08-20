
package kaizong.jee.web01.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaizong.jee.web01.wrapper.MyRequestWrapper;
import kaizong.jee.web01.wrapper.MyResponseWrapper;

public class GuestbookFilter implements Filter {

    private static final String WORD_FILE = "word_file";
    HashMap<String, String> hm = new HashMap<String, String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String configPath = filterConfig.getInitParameter(WORD_FILE);

        ServletContext sc = filterConfig.getServletContext();
        String filePath = sc.getRealPath(configPath);
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while (null != (line = br.readLine())) {
                String[] strTemp = line.split("=");
                hm.put(strTemp[0], strTemp[1]);
            }
        } catch (IOException ie) {
            throw new ServletException("读取过滤文件信息出错！");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        MyRequestWrapper reqWrapper = new MyRequestWrapper(httpReq);
        MyResponseWrapper respWrapper = new MyResponseWrapper(httpResp);

        chain.doFilter(reqWrapper, respWrapper);

        String content = new String(respWrapper.toByteArray());
        String result = replaceText(content);

        httpResp.setContentType("text/html;charset=GB2312");
        PrintWriter out = httpResp.getWriter();
        out.println(result);
        out.close();
    }

    public String replaceText(String content) throws IOException {
        StringBuffer sb = new StringBuffer();
        Set keys = hm.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            int index = sb.indexOf(key);
            if (-1 != index) {
                sb.replace(index, index + key.length(), (String) hm.get(key));
            }
        }
        return sb.toString();
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

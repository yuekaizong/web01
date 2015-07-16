package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OutputInfo extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println(" init()");
		super.init();
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println(" service(ServletRequest arg0, ServletResponse arg1)");
		super.service(arg0, arg1);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		System.out.println(" service((HttpServletRequest arg0, HttpServletResponse arg1)");
		super.service(arg0, arg1);
	}
	
	@Override
	public void destroy() {
		System.out.println(" destroy()");
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html; charset=gb2312");
		PrintWriter out = resp.getWriter();

		Enumeration<?> headNames = req.getHeaderNames();
		out.println("<html><head>");
		out.println("<title>Info page</title>");
		out.println("</head>");

		out.println("<body><center>");

		out.println("<table border=1 align=center>");
		out.println("<caption>Servlet 接收到的HTTP消息的报头的信息</caption>");
		out.println("<tr bgcolor=#99999>");
		out.println("<th>消息报头的名字</th>");
		out.println("<th>消息报头的值</th>");
		out.println("</tr>");

		while (headNames.hasMoreElements()) {
			String name = (String) headNames.nextElement();
			String value = req.getHeader(name);
			out.println("<tr>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + value + "</td>");
			out.println("</tr>");
		}

		out.println("</table><p>");

		out.println("<table border=1 align=center>");
		out.println("<caption>其他访问信息</caption>");
		
	    out.println("<tr>");
	    out.println("<td>客户端的IP地址</td>");
	    out.println("<td>"+req.getRemoteAddr()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td>客户端的端口号</td>");
	    out.println("<td>"+req.getRemotePort()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td>服务器的IP地址</td>");
	    out.println("<td>"+req.getLocalAddr()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td>服务器的端口号</td>");
	    out.println("<td>"+req.getLocalPort()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td></td>");
	    out.println("<td>"+req.getRemoteAddr()+"</td>");
	    out.println("</tr>");
		
	    out.println("</table>");
	    
		out.println("</center></body><center>");
	}

}

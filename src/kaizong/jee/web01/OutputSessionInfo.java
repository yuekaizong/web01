
package kaizong.jee.web01;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class OutputSessionInfo {

    public static void printSessionInfo(PrintWriter out, HttpSession session) {

        out.println("<table>");

        out.println("<tr>");
        out.println("<td>会话状态：</td>");
        if (session.isNew()) {
            out.println("<td>新的会话</td>");
        } else {
            out.println("<td>旧的会话</td>");
        }

        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>会话ID：</td>");
        out.println("<td>" + session.getId() + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>创建时间：</td>");
        out.println("<td>" + new Date(session.getCreationTime()) + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>上次访问时间：</td>");
        out.println("<td>" + new Date(session.getLastAccessedTime()) + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>最大不活动时间间隔(s)：</td>");
        out.println("<td>" + new Date(session.getMaxInactiveInterval()) + "</td>");
        out.println("</tr>");

        out.println("</table>");
    }

}

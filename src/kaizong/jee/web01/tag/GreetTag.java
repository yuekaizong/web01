
package kaizong.jee.web01.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class GreetTag extends BodyTagSupport {

    private int count = 0;

    /*
     * 只有当doStartTag()方法返回EVAL_BODY_BUFFERED时， JSP页面的实现对象才会创建BodyContent对象
     * 调用setBodyContent()和doInitBody()方法
     */
    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    /*
     * 因为在doAfterBody()方法被调用之前，标签体已经被执行过一次 所以在这里虽然是两次循环，但实际是那个会输出三段标签体的内容
     */
    @Override
    public int doAfterBody() throws JspException {
        if (count < 2) {
            count++;
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = bodyContent.getEnclosingWriter();
        try {
            out.println(bodyContent.toString());
        } catch (IOException e) {
            System.err.println(e);
        }
        return EVAL_PAGE;
    }

}

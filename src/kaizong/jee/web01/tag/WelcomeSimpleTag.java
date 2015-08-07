
package kaizong.jee.web01.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WelcomeSimpleTag extends SimpleTagSupport {

    private JspFragment body;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setJspBody(JspFragment jspBody) {
        this.body = jspBody;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspContext jspCtx = getJspContext();
        JspWriter out = jspCtx.getOut();
        out.print(name);
        out.print(", ");

        body.invoke(null);
    }

}

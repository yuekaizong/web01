
package kaizong.jee.web01.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class HelloTag implements Tag {

    private PageContext pageContext;
    private Tag parent;

    @Override
    public int doEndTag() throws JspException {
        System.out.println("doEndTag()");
        JspWriter out = pageContext.getOut();
        try {
            out.print("欢迎来到我的个人网站");
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return EVAL_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
        System.out.println("doStartTag()");
        return SKIP_BODY;
    }

    @Override
    public Tag getParent() {
        System.out.println("getParent()");
        return parent;
    }

    @Override
    public void release() {
        System.out.println("release()");

    }

    @Override
    public void setPageContext(PageContext pc) {
        System.out.println("setPageContext()");
        this.pageContext = pc;

    }

    @Override
    public void setParent(Tag t) {
        System.out.println("setParent()");
        this.parent = t;

    }
}

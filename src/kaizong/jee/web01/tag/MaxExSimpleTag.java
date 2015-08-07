
package kaizong.jee.web01.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MaxExSimpleTag extends SimpleTagSupport implements DynamicAttributes {

    private ArrayList<String> al = new ArrayList<String>();

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        al.add((String) value);
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspContext jspCtx = getJspContext();
        JspWriter out = jspCtx.getOut();

        int max = Integer.parseInt(al.get(0));

        int size = al.size();
        int num;

        for (int i = 1; i < size; i++) {
            num = Integer.parseInt(al.get(i));
            max = max > num ? max : num;
        }
        
        jspCtx.setAttribute("max", new Integer(max));

    }

}


package kaizong.jee.web01.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DefaultTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        Tag parent = getParent();

        // 判断标签体是否可以执行
        if (!((SwitchTag) parent).getPermission())
            return SKIP_BODY;
        ((SwitchTag) parent).subTagSucessded();
        return EVAL_BODY_INCLUDE;
    }

}


package kaizong.jee.web01.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SwitchTag extends TagSupport {

    // 用于判断自标签是否已经执行
    private boolean subTagExecuted;

    public SwitchTag() {
        subTagExecuted = false;
    }

    @Override
    public int doStartTag() throws JspException {
        // 当遇到<switch>的起始标签，子标签还没有开始之下，subTagExecuted = false
        subTagExecuted = false;
        return EVAL_BODY_INCLUDE;
    }

    /**
     * 用于旁段是否可以执行自身的标签体
     * 
     * @return
     */
    public synchronized boolean getPermission() {
        return (!subTagExecuted);
    }

    /**
     * 如果其中一个子标签满足了条件，则调用这个方法，通知父标签。 这样，其他的子标签将忽略他们的标签体，从而实现switch...case功能
     */
    public synchronized void subTagSucessded() {
        subTagExecuted = true;
    }

    @Override
    public void release() {
        subTagExecuted = false;
    }

}

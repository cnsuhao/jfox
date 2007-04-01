package org.jfox.manager.demo;

import org.jfox.framework.annotation.Service;
import org.jfox.mvc.ActionSupport;
import org.jfox.mvc.InvocationContext;
import org.jfox.mvc.Invocation;
import org.jfox.mvc.PageContext;
import org.jfox.mvc.annotation.ActionMethod;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
@Service(id="checkbox")
public class CheckboxAction extends ActionSupport {

    @ActionMethod(successView = "demo/checkbox.vhtml")
    public void doGetView(InvocationContext invocationContext) throws Exception{
        // do nothing, just show template
    }

    @ActionMethod(successView = "demo/checkbox.vhtml", invocationClass = CheckboxInvocation.class)
    public void doPostSubmit(InvocationContext invocationContext) throws Exception{
        CheckboxInvocation invocation = (CheckboxInvocation)invocationContext.getInvocation();
        String[] fruits = invocation.getFruit();
        PageContext pageContext = invocationContext.getPageContext();
        pageContext.setAttribute("fruits", fruits);
    }

    public static class CheckboxInvocation extends Invocation {
        String[] fruit = new String[0];

        public String[] getFruit() {
            return fruit;
        }

        public void setFruit(String[] fruit) {
            this.fruit = fruit;
        }
    }

    public static void main(String[] args) {

    }
}
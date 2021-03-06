/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package code.google.webactioncontainer;

import code.google.webactioncontainer.annotation.ActionMethod;
import code.google.webactioncontainer.validate.ValidateException;
import code.google.webactioncontainer.velocity.VelocityUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Page Context 存储了用来填充 template的 数据
 *
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class PageContext {

    //页面跳转方式，foward/redirect
    private ActionMethod.ForwardMethod forwardMethod;

    // success or error view
    private String targetView;

    private Map<String,Object> resultMap = new HashMap<String, Object>();

    private Exception businessException = null;

    protected PageContext() {
        
    }

    public void setForwardMethod(ActionMethod.ForwardMethod forwardMethod) {
        this.forwardMethod = forwardMethod;
    }

    public ActionMethod.ForwardMethod getForwardMethod() {
        return forwardMethod;
    }

    public Map<String,Object> getResultMap(){
        // 不能返回 unmodifiedMap，因为 Velocity render过程中需要修改
        return resultMap;
    }

    public void setTargetView(String targetView) {
        this.targetView = targetView;
    }

    public String getTargeView(){
        // 进行模板替换
        return VelocityUtils.evaluate(targetView, getResultMap());
//        return targetView;
    }

    public boolean hasAttribute(String key) {
        return resultMap.containsKey(key);
    }

    public void setAttribute(String key, Object value) {
        resultMap.put(key,value);
    }

    public Object getAttribute(String key) {
        return resultMap.get(key);
    }

    public void setBusinessException(Exception exception){
        this.businessException = exception;
    }

    public boolean hasBusinessException(){
        return businessException != null;
    }

    public Exception getBusinessException(){
        return businessException;
    }

    public String getBusinessExceptionMessage(){
        StringWriter sw = new StringWriter();
        businessException.printStackTrace(new PrintWriter(sw));
        try {
            sw.close();
        }
        catch(Exception e) {
            // ignore
        }
        return sw.getBuffer().toString();
    }

    public static void main(String[] args) {

    }
}

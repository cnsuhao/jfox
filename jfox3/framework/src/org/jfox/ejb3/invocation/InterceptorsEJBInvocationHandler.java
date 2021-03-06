/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.ejb3.invocation;

import org.jfox.ejb3.EJBInvocation;
import org.jfox.ejb3.EJBInvocationHandler;
import org.jfox.ejb3.interceptor.InterceptorMethod;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * 有 AroundInvoke method 和 bean method 构成 interceptor chain
 * 最后一个 interceptor method 即可 bean method
 *
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class InterceptorsEJBInvocationHandler extends EJBInvocationHandler {

    public void invoke(final EJBInvocation invocation) throws Exception {
        final Iterator<InterceptorMethod> interceptorMethods = invocation.getInterceptorMethods().iterator();
        final InvocationContext invocationContext = new InvocationContext() {
            
            /**
             * This allows an interceptor to save information in the context
             * data property of the InvocationContext that can be subsequently retrieved in other interceptors as
             * a means to pass contextual data between interceptors. The contextual data is not sharable across separate
             * business method invocations or lifecycle callback events. If interceptors are invoked as a result of
             * the invocation on a web service endpoint, the map returned by getContextData will be the
             * JAX-WS MessageContext [32]. The lifecycle of the InvocationContext instance is otherwise
             * unspecified.
             *
             * #ejb3-core-spec P308
             */
            public Map<String, Object> getContextData() {
                return Collections.emptyMap();
            }

            public Object getTarget() {
                return invocation.getTarget();
            }

            public Method getMethod() {
                return invocation.getInterfaceMethod();
            }

            public Object[] getParameters() {
                return invocation.getArgs();
            }

            public void setParameters(Object[] objects) {
                invocation.setArgs(objects);
            }

            // 如果有用户定义的Interceptor，会要求最后执行 proceed 方法，以执行下一个Interceptor
            public Object proceed() throws Exception {
                return interceptorMethods.next().invoke(this);
            }
        };
        // 开始执行Invocation Method chain
        Object resultObject = invocationContext.proceed();
        invocation.setResultObject(resultObject);
    }


    public static void main(String[] args) {

    }
}

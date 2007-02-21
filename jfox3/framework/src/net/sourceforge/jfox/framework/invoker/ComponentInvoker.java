package net.sourceforge.jfox.framework.invoker;

import java.lang.reflect.Method;

import net.sourceforge.jfox.framework.BaseException;
import net.sourceforge.jfox.framework.ComponentId;
import net.sourceforge.jfox.framework.component.Component;

/**
 * 动态代理调用类, InvocationHandler会委托该类完成调用过程
 *
 * Framework 中对Component的引用都是弱引用，对Component的调用也都是通过ComponentInvoker来执行
 * 这样可以保证不同Module之间的Component，在无法load implementation 的情况仍然可以引用并调用，
 * 而且实现Module可以改变Component的implementation，而引用模块多该Component引用不必改变
 *
 * @author <a href="mailto:yy.young@gmail.com">Young Yang</a>
 */
public interface ComponentInvoker {

    /**
     * 根据给定的 componentId 执行相应的Component的方法
     *
     * @param theComponent source component, is the implementation instance, not the proxy component
     * @param componentId target componentId
     * @param method method to invoke
     * @param args parameters
     * @return result
     * @throws BaseException any exception
     */
    Object invokeMethod(Component theComponent, ComponentId componentId, Method method, Object... args) throws BaseException;
    
}

/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package jfox.test;

import org.jfox.framework.annotation.ComponentBean;
import org.jfox.framework.annotation.Constant;
import org.jfox.framework.annotation.Inject;
import org.jfox.framework.component.ComponentContext;
import org.jfox.framework.component.ComponentInitialization;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Yang Yong</a>
 */
@ComponentBean(
        id = "User1")
public class User1 implements IUser, ComponentInitialization {

    @Inject(id = "UserManager")
    IUserManager userMgr;

    @Constant("World")
    private String name = "User1";

    public User1() {
    }


    /**
     * Component 实例化之后的回调方法
     * 可以做实例化之后，set Property 之前的准备工作
     *
     * @param componentContext Component context
     */
    public void postContruct(ComponentContext componentContext) {
    }

    /**
     * Component 属性设置完毕之后的回调方法
     * 负责做Properties Set 之后的检查工作，以及做 init 操作
     */
    public void postInject() {
        userMgr.addUser(this);
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {

    }
}

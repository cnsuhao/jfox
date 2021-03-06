/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package jfox.test.kernel.component;

import org.jfox.framework.annotation.Exported;
import org.jfox.framework.component.Component;
import org.jfox.framework.component.ComponentInitialization;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
@Exported
public interface TestComponent extends Component, ComponentInitialization {

    void sayHello();

}

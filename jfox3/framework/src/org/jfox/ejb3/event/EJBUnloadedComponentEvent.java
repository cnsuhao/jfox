package org.jfox.ejb3.event;

import org.jfox.framework.event.ComponentEvent;
import org.jfox.framework.ComponentId;
import org.jfox.ejb3.EJBBucket;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class EJBUnloadedComponentEvent extends ComponentEvent {

    private EJBBucket ejbBucket;

    public EJBUnloadedComponentEvent(ComponentId id, EJBBucket ejbBucket) {
        super(id);
        this.ejbBucket = ejbBucket;
    }

    public EJBBucket getEJBBucket() {
        return ejbBucket;
    }

    public static void main(String[] args) {

    }
}

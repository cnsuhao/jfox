/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.framework;

import org.jfox.framework.event.ComponentEvent;
import org.jfox.framework.event.ComponentListener;
import org.jfox.framework.event.FrameworkEvent;
import org.jfox.framework.event.FrameworkListener;
import org.jfox.framework.event.ModuleEvent;
import org.jfox.framework.event.ModuleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件管理器。注册/分发所有的事件
 * 
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class EventManager {

    private List<FrameworkListener> frameworkListeners = new ArrayList<FrameworkListener>();

    private List<ModuleListener> moduleListeners = new ArrayList<ModuleListener>();

    private List<ComponentListener> componentListeners = new ArrayList<ComponentListener>();

    public EventManager() {

    }

    public synchronized void registerFrameworkListener(FrameworkListener frameworkListener) {
        frameworkListeners.add(frameworkListener); // 
    }

    public synchronized void unregisterFrameworkListener(FrameworkListener frameworkListener) {
        frameworkListeners.remove(frameworkListener);
    }

    public synchronized void registerModuleListener(ModuleListener moduleListener) {
        moduleListeners.add(moduleListener);
    }

    public synchronized void unregisterModuleListener(ModuleListener moduleListener) {
        moduleListeners.remove(moduleListener);
    }

    public synchronized void registerComponentListener(ComponentListener componentListener) {
        componentListeners.add(componentListener);
    }

    public synchronized void unregisterComponentListener(ComponentListener componentListener) {
        componentListeners.remove(componentListener);
    }

    public synchronized void fireFrameworkEvent(FrameworkEvent frameworkEvent) {
        for(FrameworkListener listener : frameworkListeners){
            listener.frameworkEvent(frameworkEvent);
        }
    }

    public synchronized void fireModuleEvent(ModuleEvent moduleEvent) {
        for(ModuleListener listener : moduleListeners) {
            listener.moduleChanged(moduleEvent);
        }
    }

    public synchronized void fireComponentEvent(ComponentEvent componentEvent) {
        for(ComponentListener listener : componentListeners){
            listener.componentChanged(componentEvent);
        }
    }

    public static void main(String[] args) {

    }
}

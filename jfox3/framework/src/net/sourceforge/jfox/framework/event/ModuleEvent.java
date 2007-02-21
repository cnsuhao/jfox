package net.sourceforge.jfox.framework.event;

import java.util.EventObject;

import net.sourceforge.jfox.framework.component.Module;

/**
 * @author <a href="mailto:yy.young@gmail.com">Young Yang</a>
 */
public class ModuleEvent extends EventObject {

    public ModuleEvent(Module module) {
        super(module);
    }
    
    public Module getModule() {
        return (Module)getSource();
    }

    public static void main(String[] args) {

    }
}

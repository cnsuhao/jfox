package jfox.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import net.sourceforge.jfox.framework.annotation.Service;
import net.sourceforge.jfox.framework.component.InstantiatedComponent;
import net.sourceforge.jfox.framework.component.ModuleListener;
import net.sourceforge.jfox.framework.component.ComponentContext;
import net.sourceforge.jfox.framework.event.ModuleEvent;
import net.sourceforge.jfox.framework.event.ComponentEvent;
import net.sourceforge.jfox.framework.component.ComponentListener;

/**
 * @author <a href="mailto:yang_y@sysnet.com.cn">Young Yang</a>
 */
@Service
public class UserManager implements IUserManager, InstantiatedComponent, ModuleListener, ComponentListener {

    private List<IUser> users = new ArrayList<IUser>();


    public void instantiated(ComponentContext componentContext) {
        
    }

    public void postPropertiesSet() {
    }

    public void moduleChanged(ModuleEvent moduleEvent) {
        System.out.println("moduleChanged: " + moduleEvent);
    }

    public void componentChanged(ComponentEvent componentEvent) {
        System.out.println("componentChanged: " + componentEvent);
    }

    public void addUser(IUser user) {
        System.out.println("!!!!!! add user: " + user);
        users.add(user);
    }

    public void removeUser(IUser user) {
        System.out.println("!!!!!! remove user: " + user);
        users.remove(user);
    }

    public String listUsers() {
        List<String> list = new ArrayList<String>();
        for(IUser user : users) {
            list.add(user.getName());
        }
        return Arrays.toString(list.toArray(new String[list.size()]));
    }

    public static void main(String[] args) {

    }
}
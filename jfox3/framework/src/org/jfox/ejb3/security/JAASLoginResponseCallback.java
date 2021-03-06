/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.ejb3.security;

import java.util.List;
import java.util.ArrayList;
import javax.security.auth.callback.Callback;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class JAASLoginResponseCallback implements Callback {

    private Object callbackObject;

    // 一般等于用户名或者 id
    private String principalName;

    private List<String> roles = new ArrayList<String>();

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public Object getCallbackObject() {
        return callbackObject;
    }

    public void setCallbackObject(Object callbackObject) {
        this.callbackObject = callbackObject;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRole(String... roles){
        this.roles.clear();
        for(String roleName : roles){
            addRole(roleName);
        }
    }

    public void addRole(String role){
        roles.add(role);
    }

    public void removeRole(String role){
        roles.remove(role);
    }

    public static void main(String[] args) {

    }
}

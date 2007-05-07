/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.ejb3.invocation;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;
import javax.ejb.EJBAccessException;

import org.jfox.ejb3.EJBInvocation;
import org.jfox.ejb3.EJBInvocationHandler;

/**
 * SecurityEJBInvocationHandler
 *
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
public class SecurityEJBInvocationHandler extends EJBInvocationHandler {

    public Object invoke(final EJBInvocation invocation, final Iterator<EJBInvocationHandler> chain) throws Exception {
        Method method = invocation.getConcreteMethod();

        if(method.isAnnotationPresent(PermitAll.class)){
            return next(invocation, chain);
        }

        if(method.isAnnotationPresent(DenyAll.class)) {
            throw new EJBAccessException("DenyAll Roles to invoke EJB method: "+ invocation);
        }

        if(method.isAnnotationPresent(RolesAllowed.class)){
            String[] allowedRoles = (method.getAnnotation(RolesAllowed.class)).value();
            List<? extends Principal> callerPrincipals = invocation.getCallerRolesList();
            List<String> callerRoles = new ArrayList<String>(callerPrincipals.size());
            for(Principal p : callerPrincipals){
                callerRoles.add(p.getName());
            }
            if(Collections.disjoint(Arrays.asList(allowedRoles), callerRoles)){
                throw new EJBAccessException("Deny roles" + callerRoles + " to invoke : "+ invocation);
            }
            return next(invocation, chain);
        }

        return next(invocation, chain);
    }

    public static void main(String[] args) {

    }
}

package net.sourceforge.jfox.manager.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import javax.naming.Binding;
import javax.naming.NamingEnumeration;

import net.sourceforge.jfox.ejb3.EJBContainer;
import net.sourceforge.jfox.ejb3.EJBBucket;
import net.sourceforge.jfox.framework.Constants;
import net.sourceforge.jfox.framework.Framework;
import net.sourceforge.jfox.framework.annotation.Service;
import net.sourceforge.jfox.framework.component.Module;
import net.sourceforge.jfox.mvc.ActionSupport;
import net.sourceforge.jfox.mvc.InvocationContext;
import net.sourceforge.jfox.mvc.PageContext;
import net.sourceforge.jfox.mvc.WebContextLoader;
import net.sourceforge.jfox.mvc.annotation.ActionMethod;
import net.sourceforge.jfox.util.SystemUtils;

/**
 *
 *
 * @author <a href="mailto:yang_y@sysnet.com.cn">Young Yang</a>
 */
@Service(id = "console")
public class WebConsoleAction extends ActionSupport {

    @ActionMethod(successView = "console/sysinfo.vhtml")
    public void doGetSysinfo(InvocationContext invocationContext) throws Exception {
        PageContext pageContext = invocationContext.getPageContext();
        pageContext.setAttribute("jfoxVersion", Constants.VERSION);
        pageContext.setAttribute("webServerVersion", invocationContext.getServletContext().getServerInfo());
        pageContext.setAttribute("jvmVersion", SystemUtils.JAVA_VERSION);
        pageContext.setAttribute("jvmVendor", SystemUtils.JAVA_VENDOR);
        pageContext.setAttribute("osName", SystemUtils.OS_NAME);
        pageContext.setAttribute("osVersion", SystemUtils.OS_VERSION);
        pageContext.setAttribute("osArch", SystemUtils.OS_ARCH);
    }
    @ActionMethod(successView = "console/jndi.vhtml")
    public void doGetJNDI(InvocationContext invocationContext) throws Exception{
        NamingEnumeration<Binding> enu = getEJBContainer().getNamingContext().listBindings("");
        PageContext pageContext = invocationContext.getPageContext();
        List<Binding> bindings = new ArrayList<Binding>();
        while(enu.hasMoreElements()){
            bindings.add(enu.nextElement());
        }
        
        Collections.sort(bindings, new Comparator<Binding>(){
            public int compare(Binding o1, Binding o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        pageContext.setAttribute("bindings", bindings);
    }

    @ActionMethod(successView = "console/container.vhtml")
    public void doGetContainer(InvocationContext invocationContext) throws Exception{
        PageContext pageContext = invocationContext.getPageContext();
        EJBContainer container = getEJBContainer();
        int defaultTransactionTimeout = container.getTransactionTimeout();
        List<EJBBucket> buckets = new ArrayList<EJBBucket>(container.listBuckets());
        Collections.sort(buckets, new Comparator<EJBBucket>(){
            public int compare(EJBBucket o1, EJBBucket o2) {
                if(o1.getModule().getName().equals(o2.getModule().getName())) {
                    return o1.getEJBName().compareTo(o2.getEJBName());
                }
                else {
                    return o1.getModule().getName().compareTo(o2.getModule().getName());
                }
            }
        });
        pageContext.setAttribute("defaultTransactionTimeout", defaultTransactionTimeout);
        pageContext.setAttribute("buckets", buckets);
    }

    @ActionMethod(successView = "console/persistence.vhtml")
    public void doGetJPAAction(InvocationContext invocationContext) throws Exception{
        //DataSource, NamedNativeQuery, PersistenceUnit
        
    }

    @ActionMethod(successView = "console/modules.vhtml")
    public void doGetModulesAction(InvocationContext invocationContext) throws Exception{
        Framework framework = WebContextLoader.getManagedFramework();
        Module systemModule = framework.getSystemModule();
        List<Module> allModules = framework.getAllModules();

        List<Module> modules = new ArrayList<Module>(allModules.size()+1);
        modules.add(systemModule);
        modules.addAll(allModules);
        PageContext pageContext = invocationContext.getPageContext();
        pageContext.setAttribute("modules", modules);
    }

    private EJBContainer getEJBContainer(){
        Framework framework = WebContextLoader.getManagedFramework();
        Collection<EJBContainer> containers = framework.getSystemModule().findComponentByInterface(EJBContainer.class);
        return containers.iterator().next();
    }

    public static void main(String[] args) {

    }
}

package net.sourceforge.jfox.petstore.action;

import java.util.List;
import javax.ejb.EJB;

import net.sourceforge.jfox.petstore.bo.AccountBO;
import net.sourceforge.jfox.petstore.bo.CategoryBO;
import net.sourceforge.jfox.petstore.bo.ProductBO;
import net.sourceforge.jfox.petstore.entity.Product;
import net.sourceforge.jfox.petstore.entity.Category;
import net.sourceforge.jfox.mvc.util.PagedList;
import net.sourceforge.jfox.framework.annotation.Service;
import net.sourceforge.jfox.mvc.ActionSupport;
import net.sourceforge.jfox.mvc.Invocation;
import net.sourceforge.jfox.mvc.InvocationContext;
import net.sourceforge.jfox.mvc.PageContext;
import net.sourceforge.jfox.mvc.SessionContext;
import net.sourceforge.jfox.mvc.annotation.ActionMethod;

/**
 * @author <a href="mailto:yang_y@sysnet.com.cn">Young Yang</a>
 */
@Service(id = "category")
public class CategoryAction extends ActionSupport {

    @EJB
    AccountBO accountBO;

    @EJB(name = "CategoryBOImpl")
    CategoryBO categoryBO;

    @EJB
    ProductBO productBO;
    
    public void postPropertiesSet() {
        super.postPropertiesSet();
    }

    /**
     * index page
     *
     * @param invocationContext invocationContext
     * @throws Exception exception
     */
    @ActionMethod(successView = "Category.vhtml", invocationClass = CategoryInvocation.class)
    public void doGetView(InvocationContext invocationContext) throws Exception {
        CategoryInvocation invocation = (CategoryInvocation)invocationContext.getInvocation();
        Category category = categoryBO.getCategory(invocation.getCategoryId());

        PageContext pageContext = invocationContext.getPageContext();
        SessionContext sessionContext = invocationContext.getSessionContext();

        PagedList<Product> productPagedList;

        //TODO: cache product list in session
//        if (!sessionContext.containsAttribute("ProductPageList")) {
            List<Product> products = productBO.getProductsByCategory(invocation.getCategoryId());
            productPagedList = new PagedList<Product>(products, 4);
            sessionContext.setAttribute("ProductPageList", productPagedList);
//        }
//        else {
//            productPagedList = (PagedList<Product>)sessionContext.getAttribute("ProductPageList");
//        }
        for(int i=0; i< invocation.getPage(); i++){
            productPagedList.nextPage();
        }

        int nextPage = invocation.getPage();
        if(!productPagedList.isLastPage()){
            nextPage++;
        }

        int previousPage = invocation.getPage();
        if(!productPagedList.isFirstPage()){
            previousPage--;
        }

/*
        if ("next".equals(invocation.getPage())) {
            productPagedList.nextPage();
        }
        else if ("previous".equals(invocation.getPage())) {
            productPagedList.previousPage();
        }
*/

//        Account account = accountBO.getAccount();
//        account.setFirstName("Yang Yong");
//        sessionContext.setAttribute("account", account);

        pageContext.setAttribute("account", sessionContext.getAttribute("account"));
        pageContext.setAttribute("category", category);
        pageContext.setAttribute("categoryId", invocation.getCategoryId());
        pageContext.setAttribute("pageList", productPagedList);
        pageContext.setAttribute("previousPage", previousPage);
        pageContext.setAttribute("nextPage", nextPage);

    }

    public static class CategoryInvocation extends Invocation {

        private String categoryId = "BIRDS";

        private int page = 0;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }


        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

    public static void main(String[] args) {

    }
}

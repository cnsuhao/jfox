/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.petstore.action;

import org.jfox.mvc.ActionContext;
import org.jfox.mvc.ActionSupport;
import org.jfox.mvc.PageContext;
import org.jfox.mvc.ParameterObject;
import org.jfox.mvc.SessionContext;
import org.jfox.mvc.annotation.Action;
import org.jfox.mvc.annotation.ActionMethod;
import org.jfox.mvc.util.PagedList;
import org.jfox.petstore.bo.AccountBO;
import org.jfox.petstore.bo.CategoryBO;
import org.jfox.petstore.bo.ProductBO;
import org.jfox.petstore.entity.Category;
import org.jfox.petstore.entity.Product;

import javax.ejb.EJB;
import java.util.List;

/**
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */
@Action(name = "category")
public class CategoryAction extends ActionSupport {

    @EJB
    AccountBO accountBO;

    @EJB(name = "CategoryBOImpl")
    CategoryBO categoryBO;

    @EJB
    ProductBO productBO;
    
    /**
     * index page
     *
     * @param actionContext invocationContext
     * @throws Exception exception
     */
    @ActionMethod(name="view", successView = "Category.vhtml", parameterClass = CategoryParameterObject.class, httpMethod = ActionMethod.HttpMethod.GET)
    public void doGetView(ActionContext actionContext) throws Exception {
        CategoryParameterObject invocation = (CategoryParameterObject)actionContext.getParameterObject();
        Category category = categoryBO.getCategory(invocation.getCategoryId());

        PageContext pageContext = actionContext.getPageContext();
        SessionContext sessionContext = actionContext.getSessionContext();

        PagedList<Product> productPagedList;

        //product list cached by JPA Cache
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

    public static class CategoryParameterObject extends ParameterObject {

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

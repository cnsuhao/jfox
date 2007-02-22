package net.sourceforge.jfox.petstore.dao;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;

import net.sourceforge.jfox.petstore.entity.Item;
import net.sourceforge.jfox.petstore.entity.Order;
import net.sourceforge.jfox.entity.EntityManagerExt;
import net.sourceforge.jfox.entity.dao.DAOSupport;

/**
 * @author <a href="mailto:yy.young@gmail.com">Young Yang</a>
 */
@NamedNativeQueries(
        {
        @NamedNativeQuery(
                name = ItemDAOImpl.GET_ITEM_LIST_BY_PRODUCT,
                query = "select itemid, listprice, unitcost, supplier, i.productid, name, descn, category, status, attr1, attr2, attr3, attr4, attr5 from item i, product p where p.productid = i.productid and i.productid = $productId",
                resultClass = Item.class
        ),
        @NamedNativeQuery(
                name = ItemDAOImpl.GET_ITEM,
                query = "select i.itemid, listprice, unitcost, supplier, i.productid, name, descn, category, status, attr1, attr2, attr3, attr4, attr5, qty from item i, inventory v, product p where p.productid = i.productid and i.itemid = v.itemid and i.itemid = $id",
                resultClass = Item.class
        ),
        @NamedNativeQuery(
                name = ItemDAOImpl.GET_INVENTORY_QUANTITY,
                query = "select qty from inventory where itemid = $itemId",
                resultClass = Integer.class
        ),
        @NamedNativeQuery(
                name = "updateInventoryQuantity",
                query = "update inventory set qty = qty - increment where itemid = $itemid"
        )
                }
)
@Stateless
public class ItemDAOImpl extends DAOSupport implements ItemDAO {

    public static final String GET_ITEM_LIST_BY_PRODUCT = "getItemListByProduct";
    public static final String GET_ITEM = "getItem";
    public static final String GET_INVENTORY_QUANTITY = "getInventoryQuantity";

    @PersistenceContext(unitName = "JPetstoreMysqlDS")
    EntityManagerExt em;

    protected EntityManagerExt getEntityManager() {
        return em;
    }

    public Item getItem(String itemId) throws SQLException {
        return (Item)createNamedNativeQuery(GET_ITEM).setParameter("id",itemId).getSingleResult();
    }

    public List<Item> getItemListByProduct(String productId) throws SQLException {
        return (List<Item>)createNamedNativeQuery(GET_ITEM_LIST_BY_PRODUCT).setParameter("productId", productId).getResultList();
    }

    public int getInventoryQuantity(String itemId) throws SQLException {
        return (Integer)createNamedNativeQuery(GET_INVENTORY_QUANTITY).setParameter("itemId",itemId).getSingleResult();
    }

    public void updateQuantity(Order order) throws SQLException {
    }
}
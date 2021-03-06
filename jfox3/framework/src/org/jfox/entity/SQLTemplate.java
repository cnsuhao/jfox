/*
 * JFox - The most lightweight Java EE Application Server!
 * more details please visit http://www.huihoo.org/jfox or http://www.jfox.org.cn.
 *
 * JFox is licenced and re-distributable under GNU LGPL.
 */
package org.jfox.entity;

import org.apache.log4j.Logger;
import org.jfox.entity.mapping.ColumnEntry;
import org.jfox.entity.mapping.EntityFactory;
import org.jfox.entity.mapping.MappingColumnEntry;

import java.util.Collection;

/**
 * 通过 createQuery 调用创建的 SQLTemplate，没有名称
 *
 * @author <a href="mailto:jfox.young@gmail.com">Young Yang</a>
 */

public class SQLTemplate {

    protected String templateSQL;

    protected Class<?> resultClass;

    /**
     * 保存一个 Result Class 的 column name=>column entry 的对应关系
     *  result class => {column name=> column entry}
     */
//    protected static Map<Class<?>, Map<String, ColumnEntry>> resultClass2MappedColumnMap = new HashMap<Class<?>, Map<String, ColumnEntry>>();

    protected Logger logger = Logger.getLogger(this.getClass());

//    @SuppressWarnings({"unchecked"})
    public SQLTemplate(String sqlTemplate, Class<?> resultClass) {
        this.templateSQL = sqlTemplate;
        this.resultClass = resultClass;
        if(resultClass.equals(void.class)) {
            // @NamedNativeQuery default value is void.class, we want to use EntityObject.class as default
            this.resultClass = MappedEntity.class;
        }

        // 存储 ResultClass 的 column 及 类型
        introspectResultClass(getResultClass());
    }

    protected void introspectResultClass(Class<?> resultClass) {
        EntityFactory.introspectResultClass(resultClass);
    }

    public Class<?> getColumnClass(String columnName){
        ColumnEntry columnEntry = EntityFactory.getColumnEntry(getResultClass(), columnName);
        if(columnEntry == null) {
            return null;
        }
        else {
            return columnEntry.getField().getType();
        }
    }

    /**
     * 得到所有的 ColumnEntry
     */
    public Collection<MappingColumnEntry> getMappedColumnEntries(){
        return EntityFactory.getMappingColumnEntries(getResultClass());
    }


    public String getTemplateSQL() {
        return templateSQL;
    }

    public Class<?> getResultClass(){
        return this.resultClass;
    }

}

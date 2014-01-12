/**
 * file  :CollectionUtil.java
 * auther:worm
 * date  :2013-4-3
 */
package net.worm.jshelf.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author worm
 *
 */
public final class CollectionUtil
{
    /**
     * @param col
     * @return
     */
    public static boolean checkEmpty(Collection<?> col)
    {
        return null == col || col.isEmpty();
    }
    
    /**
     * @param objs
     * @return
     */
    public static List<?> getList(Object[] objs)
    {
        List<Object> list = new ArrayList<Object>();
        
        if (null != objs)
        {
            for (Object obj : objs)
            {
                list.add(obj);
            }
        }
        
        return list;
    }

}

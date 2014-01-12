/**
 * file  :ObjPrinter.java
 * auther:worm
 * date  :2013-2-18
 */
package net.worm.jshelf.base.util;

import java.lang.reflect.Method;

/**
 * @author worm
 *
 */
public final class ObjPrinter
{
    public static String print(Object obj)
    {
        StringBuilder str = new StringBuilder("{" + obj.getClass().getName() + ": [");
        
        Class<? extends Object> cls = obj.getClass();
        for (Method m : cls.getDeclaredMethods())
        {
            if (m.getName().startsWith("get") && m.getParameterTypes().length == 0)
            {
                try
                {
                    Object v = m.invoke(obj, null);
                    str.append("(").append(m.getName().replaceAll("get", "")).append("=").append(v).append(")");
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                }
            }
        }
        str.append("]}");
        
        return str.toString();
    }
}

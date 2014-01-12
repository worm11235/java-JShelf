/**
 * file  :RmiServiceProxyImpl.java
 * auther:worm
 * date  :2013-2-18
 */


import java.io.Serializable;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

import net.worm.jshelf.base.util.ObjPrinter;

/**
 * @author worm
 *
 */
public final class RmiServiceProxyImpl implements Remote, Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -4186084447590276033L;

    public Map<String, Object> get(Object obj)
    {
        Map<String, Object> ret = new HashMap<String, Object>();
        
        System.out.println(ObjPrinter.print(obj));
        ret.put("source", obj);
        ret.put("method", "get");
        ret.put("code", 0);
        
        return ret;
    }
    
    public Map<String, Object> set(Object obj)
    {
        Map<String, Object> ret = new HashMap<String, Object>();
        
        return ret;
    }
    
    
}

/**
 * file  :InvokeWriper.java
 * auther:worm
 * date  :2013-8-18
 */
package net.worm.jshelf.base.service.handler;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.rmi.Remote;

/**
 * @author worm
 *
 */
public class InvokeWriper implements Remote, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 8121705939018467234L;

    /**
     * 
     */
    public InvokeWriper()
    {
        // TODO Auto-generated constructor stub
    }
    
    public Object invoke(Object obj, Method m, Object[] args)
    {
        return null;
    }

}

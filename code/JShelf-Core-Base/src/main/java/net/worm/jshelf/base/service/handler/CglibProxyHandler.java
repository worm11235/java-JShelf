/**
 * file  :CglibProxyHandler.java
 * auther:worm
 * date  :2013-4-6
 */
package net.worm.jshelf.base.service.handler;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.rmi.Remote;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author worm
 *
 */
public class CglibProxyHandler implements MethodInterceptor, Serializable, Remote
{

    /**
     * 
     */
    private static final long serialVersionUID = -3426410058733161400L;
    
    private Object target;

    /**
     * 
     */
    public CglibProxyHandler()
    {
    }
    
    public Object getInstance(Object obj)
    {
        target = obj;
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
    }

    /* (non-Javadoc)
     * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
     */
    @Override
    public Object intercept(Object obj, Method m, Object[] args,
            MethodProxy arg3) throws Throwable
    {
        return m.invoke(target, args);
    }

}

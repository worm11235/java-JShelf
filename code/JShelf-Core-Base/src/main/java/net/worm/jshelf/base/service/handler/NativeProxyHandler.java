/**
 * file  :NativeProxyHandler.java
 * auther:worm
 * date  :2013-8-7
 */
package net.worm.jshelf.base.service.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author worm
 *
 */
public class NativeProxyHandler implements InvocationHandler
{
    private Object target;
    
    public Object getProxy(Object target, ClassLoader loader)
    {
        this.target = target;
        loader = ClassLoader.getSystemClassLoader();
        return Proxy.newProxyInstance(loader,
                target.getClass().getInterfaces(), this);
    }

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object obj, Method m, Object[] args)
            throws Throwable
    {
        return m.invoke(target, args);
    }

}

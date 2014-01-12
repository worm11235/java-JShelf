/**
 * file  :AppManager.java
 * auther:worm
 * date  :2013-3-27
 */
package net.worm.jshelf.base.service.impl;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import net.worm.jshelf.base.sdk.service.IAppManager;
import net.worm.jshelf.base.sdk.service.IApplication;

/**
 * @author worm
 *
 */
public class AppManager implements IAppManager
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4758496730938244106L;

    /**
     * 
     */
    private Map<String, IApplication> appContext = new HashMap<String, IApplication>();
    
    private static Registry reg;
    
    /**
     * 
     */
    public AppManager()
    {
//        try
//        {
//            if (null == reg)
//            {
//                reg = LocateRegistry.createRegistry(1099);
//            }
//        }
//        catch (RemoteException e)
//        {
//            // TODO Auto-generated catch block
//        }
    }

    /**
     * 
     */
    @Override
    public String registerApp(IApplication obj)
    {
        if (null != obj && !appContext.containsKey(obj.getName()))
        {
            appContext.put(obj.getName(), obj);
            return obj.getName();
        }
        return null;
    }

    /**
     * 
     */
    @Override
    public long unregisterApp(String key)
    {
        if (appContext.containsKey(key))
        {
            appContext.remove(key);
            return 0;
        }
        return -1;
    }

    /**
     * 
     */
    @Override
    public IApplication getApp(String key)
    {
        return appContext.get(key);
    }

    /**
     * 
     */
    @Override
    public Map<String, IApplication> listApp()
    {
        Map<String, Object> appMap = new HashMap<String, Object>();
        try
        {
            reg = LocateRegistry.getRegistry();
            String[] keys = reg.list();
            for (String str : keys)
            {
                try
                {
                    Remote obj = reg.lookup(str);
                    appMap.put(str, obj);
                } catch (NotBoundException e)
                {
                }
            }
            
        } catch (RemoteException e)
        {
        }
        
        return appContext;
    }
}

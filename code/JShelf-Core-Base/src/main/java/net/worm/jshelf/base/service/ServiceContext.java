/**
 * file  :ServiceContext.java
 * auther:worm
 * date  :2013-3-26
 */
package net.worm.jshelf.base.service;

import java.rmi.Naming;
import java.rmi.RemoteException;

import net.worm.jshelf.base.log.AppLogger;
import net.worm.jshelf.base.sdk.model.ApplicationBo;
import net.worm.jshelf.base.sdk.model.ServiceBo;
import net.worm.jshelf.base.sdk.service.IAppManager;
import net.worm.jshelf.base.sdk.service.IApplication;
import net.worm.jshelf.base.sdk.service.IDataManager;
import net.worm.jshelf.base.util.RmiConfig;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author worm
 *
 */
public final class ServiceContext
{
    
    private static ServiceContext ctx;
    
    private static final AppLogger log = AppLogger.getLogger(ServiceContext.class);
    
    private BundleContext bCtx;
    
    private boolean isLocal = false;
    
    private boolean isInitialed = false;
    
    private IAppManager mgr;

    /**
     * 
     */
    private ServiceContext()
    {
    }
    
    /**
     * 
     * @return
     */
    public synchronized static ServiceContext getInstance()
    {
        if (null == ctx)
        {
            ctx = new ServiceContext();
        }
        
        return ctx;
    }
    
    /**
     * 
     * @param context
     */
    public void initial(BundleContext context)
    {
        if (isInitialed)
            return;
        
        if (null != context)
        {
            this.bCtx = context;
            isLocal = true;
            ServiceReference ref = context.getServiceReference(IAppManager.class.getName());
            if (null != ref)
            {
                mgr = (IAppManager) context.getService(ref);
            }
            log.info("ServiceContext initialized as local.");
        }
        else
        {
            isLocal = false;
            log.info("ServiceContext initialized as remote only.");
        }
        isInitialed = true;
    }
    
    public Object getService(String appName, String svrName)
    {
        log.debug("Request to get service, app name: " + appName
                + ", service name: " + svrName);
        
        //先初始化，以防未初始化
        initial(null);
        
        //优先获取本地OSGi服务
        if (isLocal && null != bCtx)
        {
            //应用管理和数据管理服务特殊处理
            if (IAppManager.class.getName().equals(svrName))
            {
                log.debug("Get app manager, return.");
                return mgr;
            }
            if (IDataManager.class.getName().equals(svrName))
            {
                log.debug("Get data manager, return.");
                return bCtx.getService(bCtx.getServiceReference(svrName));
            }
            
            //其他服务，先获取应用，然后从应用的服务列表中获取服务引用，从而获取服务实例。
            if (null != mgr)
            {
                ApplicationBo app = null;
                try
                {
                    app = mgr.getApp(appName);
                } catch (RemoteException e)
                {
                    log.error("Exception while get app info.", e);
                }
                if (null != app)
                {
                    for (ServiceBo sb : app.getServiceList())
                    {
                        if (sb.getName().equals(svrName))
                        {
                            log.debug("Service found : " + sb);
                            return bCtx.getService(sb.getReference());
                        }
                    }
                }
            }
        }
        
        log.info("local service missing, now look for remote service.");
        //本地服务获取失败，则获取RMI服务。
        RmiConfig conf = RmiConfig.getInstance();
        try
        {
            return Naming.lookup("rmi://" + conf.getServerIP() + ":"
                    + conf.getServerPort() + "/" + appName + "/" + svrName);
        } catch (Exception e)
        {
            log.error("Exception while look for rmi service.", e);
        }
        return null;
    }
    
    /**
     * 
     * @return
     */
    public boolean availiable()
    {
        return isInitialed;
    }

}

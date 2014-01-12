/**
 * file  :BaseApplication.java
 * auther:worm
 * date  :2013-8-14
 */
package net.worm.jshelf.base.app;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.worm.jshelf.base.log.AppLogger;
import net.worm.jshelf.base.sdk.model.ApplicationBo;
import net.worm.jshelf.base.sdk.model.ServiceBo;
import net.worm.jshelf.base.sdk.service.IAppManager;
import net.worm.jshelf.base.sdk.service.IApplication;
import net.worm.jshelf.base.sdk.service.IDataManager;
import net.worm.jshelf.base.service.BaseService;
import net.worm.jshelf.base.service.ServiceContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceRegistration;

/**
 * @author worm
 *
 */
public class BaseApplication implements IApplication
{
    private static final String CONFIG_FILE_NAME = "/application.properties";
    
    private static final AppLogger log = AppLogger.getLogger(BaseApplication.class);
    
    private Map<String, ServiceBo> serviceList = new HashMap<String, ServiceBo>();
    
    private BundleContext ctx;
    
    private int status = STATUS_WAIT;
    

    /**
     * 
     */
    public BaseApplication()
    {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public final void start(BundleContext context) throws Exception
    {
        ctx = context;
        
        if (ServiceContext.getInstance().availiable()) //如果应用管理器和数据库管理器已经启动，则立即注册。
        {
            registerService();
            status = STATUS_DATA_NOT_READY;
            registerDB();
            onInitial();
            status = STATUS_READY;
        }
        else //否则注册服务监听，等待两者启动后注册。
        {
            ctx.addServiceListener(new ServiceListener(){

                @Override
                public void serviceChanged(ServiceEvent event)
                {
                    // 只关注服务注册事件
                    if (event.getType() == ServiceEvent.REGISTERED)
                    {
                        // 应用管理服务注册
                        Object srv = ctx.getService(event.getServiceReference());
                        if (srv instanceof IAppManager)
                        {
                            registerService();
                            if (status == STATUS_WAIT)
                            {
                                status = STATUS_DATA_NOT_READY;
                            }
                            else
                            {
                                status = STATUS_READY;
                            }
                        }
                        // 数据库管理服务注册
                        if (srv instanceof IDataManager)
                        {
                            registerDB();
                            if (status == STATUS_WAIT)
                            {
                                status = STATUS_SERVICE_NOT_READY;
                            }
                            else
                            {
                                status = STATUS_READY;
                            }
                        }
                        if (status == STATUS_READY)
                        {
                            onInitial();
                            
                            // 注册完毕后注销监听
                            ctx.removeServiceListener(this);
                        }
                    }
                }
                
            });
        }
    }

    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public final void stop(BundleContext context) throws Exception
    {
        onQuit();
        // 注销服务。如果服务管理相关服务不可用，则尚未有应用注册，无效注销
        if (ServiceContext.getInstance().availiable())
        {
            IAppManager mgr = (IAppManager) ServiceContext.getInstance().getService("BaseApplication", IAppManager.class.getName());
            mgr.unregisterApp(getName());
            
            // 注销数据库数据
            IDataManager dmgr = (IDataManager) ServiceContext.getInstance().getService("BaseApplication", IDataManager.class.getName());
            dmgr.unregisterModel(null);
        }

    }

    /* (non-Javadoc)
     * @see net.worm.jshelf.base.sdk.service.IApplication#getName()
     */
    @Override
    public String getName()
    {
        return "BaseApplication";
    }

    /* (non-Javadoc)
     * @see net.worm.jshelf.base.sdk.service.IApplication#getBundleContext()
     */
    @Override
    public final BundleContext getBundleContext()
    {
        return ctx;
    }

    /* (non-Javadoc)
     * @see net.worm.jshelf.base.sdk.service.IApplication#getService()
     */
    @Override
    public List<ServiceBo> getService()
    {
        List<ServiceBo> list = new ArrayList<ServiceBo>();
        list.addAll(serviceList.values());
        return list;
    }

    /* (non-Javadoc)
     * @see net.worm.jshelf.base.sdk.service.IApplication#onInitial()
     */
    @Override
    public void onInitial()
    {
        // TODO Auto-generated method stub

    }
    
    /* (non-Javadoc)
     * @see net.worm.jshelf.base.sdk.service.IApplication#onQuit()
     */
    @Override
    public void onQuit()
    {
        
    }
    
    private void registerService()
    {
        Bundle me = ctx.getBundle();
        ApplicationBo ab = new ApplicationBo();
        ab.setName(getName());
        
        //加载服务
        URL url = me.getResource(CONFIG_FILE_NAME);
        if (null != url)
        {
            Properties prop = new Properties();
            try
            {
                prop.load(url.openStream());
            }
            catch (IOException e1)
            {
                log.error("Error while load config properties file.", e1);
            }
            String tmp = prop.getProperty("service-export");
            
            if (null != tmp && !tmp.isEmpty())
            {
                for (String str : tmp.split(","))
                {
                    ServiceBo sb = new ServiceBo();
                    sb.setAppName(getName());
                    try
                    {
                        BaseService svr = (BaseService) me.loadClass(str.split(";")[0]).newInstance();
                        ServiceRegistration sr = ctx.registerService("net.worm.jshelf.user.sdk.service.IUserManager", svr, null);
                        sb.setClsName(svr.getClass().getName());
                        sb.setReference(sr.getReference());
                        sb.setDesc("");
                        sb.setName(svr.getName());
                        serviceList.put(svr.getName(), sb);
                    }
                    catch (Exception e)
                    {   
                        log.error("Error while register service.", e);
                    }
                }
                
                ab.setServiceList(getService());
            }
        }
        
        IAppManager mgr = (IAppManager) ServiceContext.getInstance().getService("BaseApplication", IAppManager.class.getName());
        try
        {
            mgr.registerApp(this);
        }
        catch (RemoteException e)
        {
            log.error("Exception while register app.", e);
        }
    }
    
    private void registerDB()
    {
        Bundle me = ctx.getBundle();
        ApplicationBo ab = new ApplicationBo();
        ab.setName(getName());
        
        //加载服务
        URL url = me.getResource(CONFIG_FILE_NAME);
        if (null != url)
        {
            IDataManager mgr = (IDataManager) ServiceContext.getInstance().getService("BaseApplication", IDataManager.class.getName());
            try
            {
                mgr.config(url);
            } catch (RemoteException e)
            {
                log.error("Exception while register database config info.", e);
            }
        }
        
    }

}

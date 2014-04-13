package net.worm.jshelf.base;

import java.rmi.Naming;

import net.worm.jshelf.base.log.AppLogger;
import net.worm.jshelf.base.sdk.service.IAppManager;
import net.worm.jshelf.base.sdk.service.IDataManager;
import net.worm.jshelf.base.service.ServiceContext;
import net.worm.jshelf.base.service.impl.AppManager;
import net.worm.jshelf.base.service.impl.DataManager;
import net.worm.jshelf.base.util.RmiConfig;
import net.worm.jshelf.base.util.RmiExporter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
//import net.worm.jshelf.user.service.impl.BaseUserMgr;

public class Activator implements BundleActivator
{
    private static final AppLogger log = AppLogger.getLogger(Activator.class);

    private static BundleContext context;
    
    private ServiceRegistration appMgr;
    
    private ServiceRegistration dataMgr;
    
    public static BundleContext getContext()
    {
        return context;
    }

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext bundleContext) throws Exception
    {
        log.debug("Base bundle activator start.");
        context = bundleContext;
        
        IAppManager appMgrInst = new AppManager();
        appMgr = context.registerService(IAppManager.class.getName(), appMgrInst, null);
        
        IDataManager dataMgrInst = new DataManager();
        dataMgr = context.registerService(IDataManager.class.getName(), dataMgrInst, null);
        
        ServiceContext.getInstance().initial(context);
        
        bundleContext.addBundleListener(RmiExporter.getInstance(RmiConfig.getInstance().getServerPort()));
        bundleContext.addFrameworkListener(RmiExporter.getInstance(RmiConfig.getInstance().getServerPort()));
        log.debug("Base bundle activator started.");
    }

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext bundleContext) throws Exception
    {
        log.debug("Base bundle activator stop.");
        bundleContext.ungetService(appMgr.getReference());
        bundleContext.ungetService(dataMgr.getReference());
        Naming.unbind("BaseApplication/" + IAppManager.class.getName());
        Naming.unbind("BaseApplication/" + IDataManager.class.getName());
        log.debug("Base bundle stoped.");
    }

}

/**
 * file  :RmiExporter.java
 * auther:worm
 * date  :2013-8-25
 */
package net.worm.jshelf.base.util;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.worm.jshelf.base.config.AppConfig;
import net.worm.jshelf.base.config.ServiceConfig;
import net.worm.jshelf.base.log.AppLogger;
import net.worm.jshelf.base.sdk.model.ServiceBo;
import net.worm.jshelf.base.sdk.service.IAppManager;
import net.worm.jshelf.base.sdk.service.IApplication;
import net.worm.jshelf.base.service.BaseService;
import net.worm.jshelf.base.service.ServiceContext;
import net.worm.jshelf.base.service.handler.CglibProxyHandler;
import net.worm.jshelf.base.util.pojo.RmiServiceInfo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;

/**
 * @author worm
 *
 */
public final class RmiExporter implements BundleListener,FrameworkListener
{
    private static final AppLogger log = AppLogger.getLogger(RmiExporter.class);
    
    private static final String CONFIG_FILE_NAME = "/appconfig.xml";
    
    private static RmiExporter inst;
    
    private Registry reg;
    
    private Map<Long, List<RmiServiceInfo>> serviceMap = new HashMap<Long, List<RmiServiceInfo>>();

    /**
     * 
     */
    private RmiExporter()
    {
        log.debug("RMI exporter initial, using default port number.");
        try
        {
            reg = LocateRegistry.createRegistry(1097);
        } catch (RemoteException e)
        {
            log.error("Initial RMI registry failed.", e);
        }
    }
    
    private RmiExporter(int port)
    {
        log.debug("RMI exporter initial, using port number:" + port);
        try
        {
            reg = LocateRegistry.createRegistry(port);
        } catch (RemoteException e)
        {
            log.error("Initial RMI registry failed.", e);
        }
    }
    
    public static synchronized RmiExporter getInstance(int port)
    {
        if (null == inst)
        {
            inst = new RmiExporter(port);
        }
        return inst;
    }

    /* (non-Javadoc)
     * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
     */
    @Override
    public void bundleChanged(BundleEvent event)
    {
        if (event.getType() == BundleEvent.STARTED)
        {
            Bundle me = event.getBundle();
            export(me);
        }
        else if (event.getType() == BundleEvent.STOPPED)
        {
            Bundle me = event.getBundle();
            unexport(me);
        }
            
    }
    
    public long startService(long id, String svrName)
    {
        log.debug("startService, id:" + id + ", name:" + svrName);
        List<RmiServiceInfo> list = serviceMap.get(id);
        for (RmiServiceInfo rsi : list)
        {
            if (rsi.getName().equals(svrName))
            {
                
                try
                {
                    reg.bind(rsi.getUri(), rsi.getInst());
                } catch (Exception e)
                {
                    log.error("Export rmi service failed, service name :" + svrName, e);
                }
                return 0L;
            }
        }
        return -1L;
    }
    
    public long stopService(long id, String svrName)
    {
        log.debug("stopService, id:" + id + ", name:" + svrName);
        List<RmiServiceInfo> list = serviceMap.get(id);
        for (RmiServiceInfo rsi : list)
        {
            if (rsi.getName().equals(svrName))
            {
                try
                {
                    reg.unbind(rsi.getUri());
                } catch (Exception e)
                {
                    log.error("Failed to unbind rmi service.", e);
                }
                return 0L;
            }
        }
        return -1L;
    }
    
    private void unexport(Bundle me)
    {
        log.debug("Unexport bundle :" + me);
        if (serviceMap.containsKey(me.getBundleId()))
        {
            List<RmiServiceInfo> list = serviceMap.get(me.getBundleId());
            int index = list.size() - 1;
            for (; index >= 0; index --)
            {
                RmiServiceInfo rsi = list.get(index);
                try
                {
                    reg.unbind(rsi.getUri());
                }
                catch (Exception e)
                {
                    log.error("Failed to unbind rmi service.", e);
                }
                list.remove(index);
            }
        }
    }

    private void export(Bundle me)
    {
        log.debug("Export bundle :" + me);
        List<RmiServiceInfo> list = null;
        if (serviceMap.containsKey(me.getBundleId()))
        {
            list = serviceMap.get(me.getBundleId());
        }
        else
        {
            list = new ArrayList<RmiServiceInfo>();
            serviceMap.put(me.getBundleId(), list);
        }
        URL url = me.getResource(CONFIG_FILE_NAME);
        AppConfig ac = null;
        if (null != url)
        {
            ac = readConfig(url);
        }
        if (null != ac)
        {
            for (ServiceConfig sc : ac.getServices())
            {
                try
                {
                    BaseService svr = (BaseService) me.loadClass(sc.getCls()).newInstance();
                    String uri = ac.getName() + "/" + sc.getName();
                    if (null != reg)
                    {
                        CglibProxyHandler cph = new CglibProxyHandler();
                        Remote appStub = UnicastRemoteObject.exportObject((Remote) cph.getInstance(svr), 1099);
                        reg.bind(uri, appStub);
                        RmiServiceInfo rsi = new RmiServiceInfo();
                        rsi.setBundle(me);
                        rsi.setCls(sc.getCls());
                        rsi.setInst(appStub);
                        rsi.setName(svr.getName());
                        rsi.setUri(uri);
                        rsi.setDesc(sc.getDesc());
                        list.add(rsi);
                    }
                }
                catch (Exception e)
                {   
                    log.error("Bind rmi service failed.", e);
                }
            }
            
            IAppManager iam = (IAppManager) ServiceContext.getInstance().getService("BaseApplication", IAppManager.class.getName());
            try
            {
                IApplication ia = iam.getApp(ac.getName());
                for (RmiServiceInfo rsi : list)
                {
                    ServiceBo sb = findServiceBo(ia.getService(), rsi.getName());
                    
                    if (null != sb)
                    {
                        sb.setUri(rsi.getUri());
                        sb.setType(ServiceConfig.MODE_BOTH);
                    }
                    else
                    {
                        sb = new ServiceBo();
                        sb.setAppName(ia.getName());
                        sb.setClsName(rsi.getCls());
                        sb.setName(rsi.getName());
                        sb.setType(ServiceConfig.MODE_REMOTE);
                        sb.setUri(rsi.getUri());
                        sb.setDesc(rsi.getDesc());
                        ia.getService().add(sb);
                    }
                    
                }
            }
            catch (RemoteException e)
            {
                log.error("Invoke appmanager service failed.", e);
            }
        }
    }
    
    private ServiceBo findServiceBo(List<ServiceBo> list, String name)
    {
        for (ServiceBo sb : list)
        {
            if (sb.getName().equals(name))
            {
                return sb;
            }
        }
        
        return null;
    }

    @Override
    public void frameworkEvent(FrameworkEvent event)
    {
        if (event.getType() != FrameworkEvent.STARTED)
        {
            return;
        }
        Bundle me = event.getBundle();
        BundleContext ctx = me.getBundleContext();
        for (Bundle bd : ctx.getBundles())
        {
            if (!serviceMap.containsKey(bd.getBundleId()) && bd.getState() == Bundle.ACTIVE)
            {
                export(bd);
            }
        }
    }
    
    private AppConfig readConfig(URL url)
    {
        SAXReader reader = new SAXReader();
        try
        {
            Document doc = reader.read(url);
            Element root = doc.getRootElement();
            AppConfig ac = new AppConfig(root);
            return ac;
            
        } catch (DocumentException e)
        {
            log.error("Parse application configuration file failed.", e);
        }
        return null;
    }
}

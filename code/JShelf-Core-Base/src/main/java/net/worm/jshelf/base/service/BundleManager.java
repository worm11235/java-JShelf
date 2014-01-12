/**
 * file  :BundleManager.java
 * auther:worm
 * date  :2013-3-21
 */
package net.worm.jshelf.base.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import net.worm.jshelf.base.log.AppLogger;
import net.worm.jshelf.base.sdk.model.BundleBo;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * @author worm
 *
 */
public final class BundleManager implements Remote, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5580064661054985938L;
    
    private static final AppLogger log = AppLogger.getLogger(BundleManager.class);
    
    /**
     * 
     */
    private BundleContext ctx;

    /**
     * 
     */
    public BundleManager()
    {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     * @param context
     */
    public BundleManager(BundleContext context)
    {
        this.ctx = context;
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public List<BundleBo> listBundles(long id)
    {
        log.debug("Request of get Bundle list, id : " + id);
        List<BundleBo> bundleList = new ArrayList<BundleBo>();
        if (null != ctx)
        {
            if (-1 < id)
            {
                Bundle[] bundles = ctx.getBundles();
                if (null != bundles)
                {
                    for (Bundle bundle : bundles)
                        bundleList.add(new BundleBo(bundle));
                }
            }
            else
            {
                Bundle bundle = ctx.getBundle(id);
                if (null != bundle)
                {
                    bundleList.add(new BundleBo(bundle));
                }
            }
        }
        
        return bundleList;
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public long shutdownBundle(long id)
    {
        log.debug("Request of shutdown bundle, id: " + id);
        if (null != ctx)
        {
            Bundle bundle = ctx.getBundle(id);
            if (null != bundle)
            {
                try
                {
                    bundle.stop();
                    return 0L;
                } catch (BundleException e)
                {
                    log.error("Exception while stop bundle.", e);
                }
            }
        }
        log.error("Stop bundle failed.");
        return -1L;
    }
    
    public long startBundle(long id)
    {
        log.debug("Request to start bundle, id: " + id);
        if (null != ctx)
        {
            Bundle bundle = ctx.getBundle(id);
            if (null != bundle)
            {
                try
                {
                    bundle.start();
                    bundle.getRegisteredServices();
                    return 0L;
                } catch (BundleException e)
                {
                    log.error("Exception while start bundle.", e);
                }
            }
        }
        log.error("Start bundle failed.");
        return -1L;
    }
}

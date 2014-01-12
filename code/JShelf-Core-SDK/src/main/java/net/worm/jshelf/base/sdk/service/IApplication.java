/**
 * file  :IApplication.java
 * auther:worm
 * date  :2013-8-10
 */
package net.worm.jshelf.base.sdk.service;

import java.util.List;

import net.worm.jshelf.base.sdk.model.ServiceBo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author worm
 *
 */
public interface IApplication extends BundleActivator
{
    int STATUS_READY = 0;
    
    int STATUS_WAIT = 1;
    
    int STATUS_SERVICE_NOT_READY = 2;
    
    int STATUS_DATA_NOT_READY = 3;
    
    String getName();
    
    BundleContext getBundleContext();
    
    List<ServiceBo> getService();
    
    void onInitial();
    
    void onQuit();
}

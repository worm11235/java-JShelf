/**
 * file  :DataManager.java
 * auther:worm
 * date  :2013-3-28
 */
package net.worm.jshelf.base.service.impl;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.rmi.Remote;

import net.worm.jshelf.base.sdk.service.IDataManager;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

/**
 * @author worm
 *
 */
public class DataManager implements IDataManager, Remote
{
    /**
     * 
     */
    private static final long serialVersionUID = 2933467923494097140L;
    
    /**
     * 
     */
    private Configuration conf;

    /**
     * 
     */
    public DataManager()
    {
    }
    
    public DataManager(File configFile)
    {
        this.config(configFile);
    }

    /* (non-Javadoc)
     * @see net.worm.base.sdk.service.IDataManager#config(java.io.File)
     */
    @Override
    public long config(File configFile)
    {
        // TODO Auto-generated method stub
        System.out.println(new File("").getAbsolutePath());
        if (null != configFile && configFile.exists() && configFile.isFile())
        {
            if (null == conf)
            {
                conf = new Configuration();
            }
            try
            {
                conf.configure(configFile);
            }
            catch (HibernateException e)
            {
//                e.get
                return 1;
            }
            return 0;
            
        }
        
        return -1;
    }

    /* (non-Javadoc)
     * @see net.worm.base.sdk.service.IDataManager#config(java.io.File)
     */
    @Override
    public long config(URL url)
    {
        // TODO Auto-generated method stub
        System.out.println(new File("").getAbsolutePath());
        if (null != url)
        {
            if (null == conf)
            {
                conf = new Configuration();
            }
            try
            {
                conf.configure(url);
            }
            catch (HibernateException e)
            {
//                e.get
                return 1;
            }
            return 0;
            
        }
        
        return -1;
    }

    /* (non-Javadoc)
     * @see net.worm.base.sdk.service.IDataManager#registerModel(java.io.Serializable)
     */
    @Override
    public long registerModel(File file)
    {
        // TODO Auto-generated method stub
        if (null != file && file.exists() && file.isFile())
        {
            try
            {
                conf.addFile(file);
            }
            catch (MappingException e)
            {
//                e.
                return 1;
            }
            return 0;
        }
        return -1;
    }

    /* (non-Javadoc)
     * @see net.worm.base.sdk.service.IDataManager#unregisterModel(java.io.Serializable)
     */
    @Override
    public long unregisterModel(Serializable obj)
    {
//        conf.
        return 0;
    }

    /* (non-Javadoc)
     * @see net.worm.base.sdk.service.IDataManager#getSession()
     */
    @Override
    @SuppressWarnings("deprecation")
    public Object getSession()
    {
        Service svr = new Service() {

            /**
             * 
             */
            private static final long serialVersionUID = 1135145599292693081L;};
        
        
        try
        {
            ServiceRegistry sr = new ServiceRegistry(){

                @Override
                public ServiceRegistry getParentServiceRegistry()
                {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public <R extends Service> R getService(Class<R> serviceRole)
                {
                    // TODO Auto-generated method stub
                    return null;
                }
                
            };
            
            return conf.buildSessionFactory().openSession();
        }
        catch (Exception e)
        {
            //
        }
        return null;
    }

}

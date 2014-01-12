/**
 * file  :BaseService.java
 * auther:worm
 * date  :2013-4-5
 */
package net.worm.jshelf.base.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author worm
 *
 */
public class BaseService implements Serializable, Remote
{

    /**
     * 
     */
    private static final long serialVersionUID = -4225802325719414407L;
    
    private String name;

    /**
     * 
     */
    public BaseService()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the name
     */
    public String getName() throws RemoteException
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) throws RemoteException
    {
        this.name = name;
    }
    
    

}

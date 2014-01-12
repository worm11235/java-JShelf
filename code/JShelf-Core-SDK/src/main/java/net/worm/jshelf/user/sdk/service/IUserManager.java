/**
 * file  :IUserManager.java
 * auther:worm
 * date  :2013-4-4
 */
package net.worm.jshelf.user.sdk.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import net.worm.jshelf.user.sdk.model.User;

/**
 * @author worm
 *
 */
public interface IUserManager extends Remote, Serializable
{
    /**
     * @param user
     * @return
     */
    long createUser(User user) throws RemoteException;
    
    /**
     * @param uuid
     * @return
     */
    long deleteUser(String uuid) throws RemoteException;
    
    /**
     * @param queryParam
     * @return
     */
    List<User> queryUser(Map<String, Object> queryParam) throws RemoteException;
    
    /**
     * @param user
     * @return
     */
    long modifyUser(User user) throws RemoteException;

}

/**
 * file  :IAppManager.java
 * auther:worm
 * date  :2013-3-21
 */
package net.worm.jshelf.base.sdk.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import net.worm.jshelf.base.sdk.model.ApplicationBo;

/**
 * @author worm
 *
 */
public interface IAppManager extends Remote, Serializable
{
    /**
     * 注册应用
     * 
     * @param obj
     * @return
     */
    String registerApp(ApplicationBo app) throws RemoteException;
    
    /**
     * 注销应用
     * 
     * @param key
     * @return
     */
    long unregisterApp(String key) throws RemoteException;
    
    /**
     * 获取应用实例
     * @param key
     * @return
     */
    ApplicationBo getApp(String key) throws RemoteException;
    
    /**
     * 列出应用
     * 
     * @return
     */
    Map<String, ApplicationBo> listApp() throws RemoteException;
}

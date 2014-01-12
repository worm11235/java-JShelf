/**
 * file  :IDataManager.java
 * auther:worm
 * date  :2013-3-21
 */
package net.worm.jshelf.base.sdk.service;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 数据管理接口，主要是针对hibernate处理的抽象，包括初始配置、类型注册注销和获取会话。
 * 
 * @author worm
 *
 */
public interface IDataManager extends Remote, Serializable
{
    /**
     * 配置Hibernate
     * 
     * @param configFile
     * @return
     */
    long config(File configFile) throws RemoteException;
    
    /**
     * 
     * @param url
     * @return
     */
    long config(URL url) throws RemoteException;
    
    /**
     * 注册数据类型
     * 
     * @param obj
     * @return
     */
    long registerModel(File obj) throws RemoteException;
    
    /**
     * 注销数据类型
     * 
     * @param obj
     * @return
     */
    long unregisterModel(Serializable obj) throws RemoteException;
    
    /**
     * 获取会话
     * 
     * @return
     */
    Object getSession() throws RemoteException;
}

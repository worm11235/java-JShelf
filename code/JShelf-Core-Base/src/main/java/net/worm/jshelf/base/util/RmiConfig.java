/**
 * file  :AppConfig.java
 * auther:worm
 * date  :2013-3-26
 */
package net.worm.jshelf.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author worm
 *
 */
public final class RmiConfig
{
    public static final String SERVER_IP = "server_ip";
    
    public static final String SERVER_PORT = "server_port";
    
    private Properties prop;
    
    private static RmiConfig inst;
    
    /**
     * 
     */
    private RmiConfig()
    {
        prop = new Properties();
        try
        {
            prop.load(new FileInputStream(new File("appconfig.properties")));
        }
        catch (Exception e)
        {
            prop.setProperty(SERVER_IP, "127.0.0.1");
            prop.setProperty(SERVER_PORT, "16160");
        }
    }
    
    public synchronized static RmiConfig getInstance()
    {
        if (null == inst)
        {
            inst = new RmiConfig();
        }
        return inst;
    }
    
    public String getServerIP()
    {
        String ip = prop.getProperty(SERVER_IP);
        if (null == ip || ip.isEmpty())
        {
            prop.setProperty(SERVER_IP, "127.0.0.1");
        }
        return prop.getProperty(SERVER_IP);
    }

    public int getServerPort()
    {
        String port = prop.getProperty(SERVER_PORT);
        
        try
        {
            return Integer.parseInt(port);
        }
        catch (Exception e)
        {
            prop.setProperty(SERVER_PORT, "16160");
            return 16160;
        }
    }
}

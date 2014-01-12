/**
 * file  :AppLogger.java
 * auther:worm
 * date  :2013-8-31
 */
package net.worm.jshelf.base.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;

/**
 * @author worm
 *
 */
public final class AppLogger
{
    private static Map<String, Appender> logMap = new HashMap<String, Appender>();
    
    private Logger log;

    /**
     * 
     */
    private AppLogger()
    {
    }
    
    public static AppLogger getLogger(Class<?> cls)
    {
        AppLogger inst = new AppLogger();
        Logger log = Logger.getLogger(cls + "", new BaseLogFactory());
        inst.log = log;
        if (null != cls && null != cls.getClassLoader())
        {
            URL url = cls.getResource("app.conf");
            if (null != url)
            {
                try
                {
                    Properties prop = new Properties();
                    prop.load(url.openStream());
                    String name = prop.getProperty("name");
                    if (null != name && !name.isEmpty())
                    {
                        if (logMap.containsKey(name))
                        {
                            log.removeAllAppenders();
                            log.addAppender(logMap.get(name));
                        }
                        else
                        {
                            Appender apd = new FileAppender(new HTMLLayout(), name);
                            logMap.put(name, apd);
                            log.removeAllAppenders();
                            log.addAppender(apd);
                        }
                        return inst;
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        File file = new File("./app.conf");
        if (file.exists() && file.isFile())
        {
            Properties prop = new Properties();
            try
            {
                prop.load(new FileInputStream(file));
                String name = prop.getProperty("name");
                if (null != name && !name.isEmpty())
                {
                    if (logMap.containsKey(name))
                    {
                        log.removeAllAppenders();
                        log.addAppender(logMap.get(name));
                    }
                    else
                    {
                        Appender apd = new FileAppender(new HTMLLayout(), name);
                        logMap.put(name, apd);
                        log.removeAllAppenders();
                        log.addAppender(apd);
                    }
                    return inst;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        return inst;
    }
    
    public void trace(Object message)
    {
        log.trace(message);
    }
    
    public void trace(Object message, Throwable e)
    {
        log.trace(message, e);
    }
    
    public void debug(Object message)
    {
        log.debug(message);
    }
    
    public void debug(Object message, Throwable e)
    {
        log.debug(message, e);
    }
    
    public void info(Object message)
    {
        log.info(message);
    }
    
    public void info(Object message, Throwable e)
    {
        log.info(message, e);
    }
    
    public void warn(Object message)
    {
        log.warn(message);
    }
    
    public void warn(Object message, Throwable e)
    {
        log.warn(message, e);
    }
    
    public void error(Object message)
    {
        log.error(message);
    }
    
    public void error(Object message, Throwable e)
    {
        log.error(message, e);
    }
    
    public void fatal(Object message)
    {
        log.fatal(message);
    }
    
    public void fatal(Object message, Throwable e)
    {
        log.fatal(message, e);
    }

}

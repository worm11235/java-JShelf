/**
 * file  :BaseLogFactory.java
 * auther:worm
 * date  :2013-8-31
 */
package net.worm.jshelf.base.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * @author worm
 *
 */
public final class BaseLogFactory implements LoggerFactory
{

    /**
     * 
     */
    public BaseLogFactory()
    {
    }

    /* (non-Javadoc)
     * @see org.apache.log4j.spi.LoggerFactory#makeNewLoggerInstance(java.lang.String)
     */
    @Override
    public Logger makeNewLoggerInstance(String arg0)
    {
        return Logger.getLogger(arg0);
    }

}

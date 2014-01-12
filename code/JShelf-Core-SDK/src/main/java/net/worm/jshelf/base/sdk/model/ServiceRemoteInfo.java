/**
 * file  :ServiceRemoteInfo.java
 * auther:worm
 * date  :2013-8-30
 */
package net.worm.jshelf.base.sdk.model;

import java.io.Serializable;

/**
 * @author worm
 *
 */
public final class ServiceRemoteInfo implements Serializable, Cloneable
{

    /**
     * 
     */
    private static final long serialVersionUID = -1454780988770495092L;
    
    private String uri;

    /**
     * 
     */
    public ServiceRemoteInfo()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }

}

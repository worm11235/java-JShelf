/**
 * file  :BundleBo.java
 * auther:worm
 * date  :2013-1-17
 */
package net.worm.jshelf.base.sdk.model;

import java.io.Serializable;

import org.osgi.framework.Bundle;

/**
 * @author worm
 *
 */
public final class BundleBo implements Serializable, Cloneable
{

    /**
     * 
     */
    private static final long serialVersionUID = -3575279066402819961L;
    
    private long id;
    
    private String name;
    
    private int state;
    
    private String location;
    
    private long lastModified;
    
    private String version;
    
    public BundleBo(){}
    
    public BundleBo(Bundle bundle)
    {
        this.id = bundle.getBundleId();
        this.state = bundle.getState();
        this.name = bundle.getSymbolicName();
        this.location = bundle.getLocation();
        this.version = bundle.getVersion().toString();
        this.lastModified = bundle.getLastModified();
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the state
     */
    public int getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state)
    {
        this.state = state;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     * @return the lastModified
     */
    public long getLastModified()
    {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(long lastModified)
    {
        this.lastModified = lastModified;
    }

    /**
     * @return the version
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("BundleBo [id=").append(id).append(", name=")
                .append(name).append(", state=").append(state)
                .append(", location=").append(location)
                .append(", lastModified=").append(lastModified)
                .append(", version=").append(version).append("]");
        return builder.toString();
    }

}

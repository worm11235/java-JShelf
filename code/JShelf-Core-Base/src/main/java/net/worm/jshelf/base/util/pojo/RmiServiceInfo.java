/**
 * file  :RmiServiceInfo.java
 * auther:worm
 * date  :2013-8-25
 */
package net.worm.jshelf.base.util.pojo;

import java.io.Serializable;
import java.rmi.Remote;

import org.osgi.framework.Bundle;

/**
 * @author worm
 *
 */
public final class RmiServiceInfo implements Serializable, Cloneable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5512655169769370980L;
    
    private String name;
    
    private String uri;
    
    private String cls;
    
    private String desc;
    
    private Bundle bundle;
    
    private Remote inst;

    /**
     * 
     */
    public RmiServiceInfo()
    {
        // TODO Auto-generated constructor stub
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

    /**
     * @return the cls
     */
    public String getCls()
    {
        return cls;
    }

    /**
     * @param cls the cls to set
     */
    public void setCls(String cls)
    {
        this.cls = cls;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the bundle
     */
    public Bundle getBundle()
    {
        return bundle;
    }

    /**
     * @param bundle the bundle to set
     */
    public void setBundle(Bundle bundle)
    {
        this.bundle = bundle;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RmiServiceInfo [name=").append(name).append(", uri=")
                .append(uri).append(", cls=").append(cls).append(", desc=")
                .append(desc).append("]");
        return builder.toString();
    }

    /**
     * @return the inst
     */
    public Remote getInst()
    {
        return inst;
    }

    /**
     * @param inst the inst to set
     */
    public void setInst(Remote inst)
    {
        this.inst = inst;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cls == null) ? 0 : cls.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof RmiServiceInfo))
            return false;
        RmiServiceInfo other = (RmiServiceInfo) obj;
        if (cls == null)
        {
            if (other.cls != null)
                return false;
        } else if (!cls.equals(other.cls))
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (uri == null)
        {
            if (other.uri != null)
                return false;
        } else if (!uri.equals(other.uri))
            return false;
        return true;
    }
}

/**
 * file  :ServiceBo.java
 * auther:worm
 * date  :2013-8-16
 */
package net.worm.jshelf.base.sdk.model;

import java.io.Serializable;

import org.osgi.framework.ServiceReference;

/**
 * @author worm
 * 
 */
public final class ServiceBo implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 928825901437699515L;

    private String name;

    private String clsName;

    private String appName;

    private String desc;

    private ServiceReference reference;

    private int type;

    private String uri;

    /**
     * 
     */
    public ServiceBo()
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
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the clsName
     */
    public String getClsName()
    {
        return clsName;
    }

    /**
     * @param clsName
     *            the clsName to set
     */
    public void setClsName(String clsName)
    {
        this.clsName = clsName;
    }

    /**
     * @return the appName
     */
    public String getAppName()
    {
        return appName;
    }

    /**
     * @param appName
     *            the appName to set
     */
    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the reference
     */
    public ServiceReference getReference()
    {
        return reference;
    }

    /**
     * @param reference
     *            the reference to set
     */
    public void setReference(ServiceReference reference)
    {
        this.reference = reference;
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * @return the uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * @param uri
     *            the uri to set
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceBo [name=").append(name).append(", clsName=")
                .append(clsName).append(", appName=").append(appName)
                .append(", desc=").append(desc).append(", type=").append(type)
                .append(", uri=").append(uri).append("]");
        return builder.toString();
    }

}

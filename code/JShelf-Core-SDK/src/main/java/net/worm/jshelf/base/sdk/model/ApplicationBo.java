/**
 * file  :ApplicationBo.java
 * auther:worm
 * date  :2013-8-16
 */
package net.worm.jshelf.base.sdk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author worm
 *
 */
public final class ApplicationBo implements Serializable, Cloneable
{

    /**
     * 
     */
    private static final long serialVersionUID = -128285984907435877L;
    
    private long id;
    
    private String name;
    
    private String desc;
    
    private List<ServiceBo> serviceList = new ArrayList<ServiceBo>();
    
    private List<BundleBo> bundleList = new ArrayList<BundleBo>();

    /**
     * 
     */
    public ApplicationBo()
    {
        // TODO Auto-generated constructor stub
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
     * @return the serviceList
     */
    public List<ServiceBo> getServiceList()
    {
        return serviceList;
    }

    /**
     * @param serviceList the serviceList to set
     */
    public void setServiceList(List<ServiceBo> serviceList)
    {
        this.serviceList = serviceList;
    }

    /**
     * @return the bundleList
     */
    public List<BundleBo> getBundleList()
    {
        return bundleList;
    }

    /**
     * @param bundleList the bundleList to set
     */
    public void setBundleList(List<BundleBo> bundleList)
    {
        this.bundleList = bundleList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ApplicationBo [id=").append(id).append(", name=")
                .append(name).append(", desc=").append(desc)
                .append(", serviceList=").append(serviceList)
                .append(", bundleList=").append(bundleList).append("]");
        return builder.toString();
    }

}

/**
 * file  :AppConfig.java
 * auther:worm
 * date  :2013-8-25
 */
package net.worm.jshelf.base.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * @author worm
 *
 */
public final class AppConfig implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 4685800108823141015L;
    
    private String name;
    
    private String cls;
    
    private List<ServiceConfig> services;

    /**
     * 
     */
    public AppConfig() {}
    
    @SuppressWarnings("unchecked")
    public AppConfig(Element e)
    {
        Element app = e.element("appinfo");
        name = app.elementText("name");
        cls = app.elementText("class");
        
        List<Element> ss = e.element("services").elements("service");
        services = new ArrayList<ServiceConfig>();
        for (Element s : ss)
        {
            services.add(new ServiceConfig(s));
        }
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
     * @return the services
     */
    public List<ServiceConfig> getServices()
    {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<ServiceConfig> services)
    {
        this.services = services;
    }

}

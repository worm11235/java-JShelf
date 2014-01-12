/**
 * file  :ServiceConfig.java
 * auther:worm
 * date  :2013-8-25
 */
package net.worm.jshelf.base.config;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * @author worm
 *
 */
public final class ServiceConfig implements Serializable
{
    
    public static final int MODE_REMOTE = 2;
    
    public static final int MODE_LOCAL = 1;
    
    public static final int MODE_BOTH = 3;

    /**
     * 
     */
    private static final long serialVersionUID = -1939233703890732815L;
    
    /**
     * 是否自动启动
     */
    private boolean autoStart;
    
    /**
     * 模式：1——仅注册本地服务，2——仅注册远程服务，3——注册本地和远程服务
     */
    private int mode;
    
    /**
     * 服务名称
     */
    private String name;
    
    /**
     * 服务实现类
     */
    private String cls;
    
    /**
     * 服务调用接口
     */
    private String intf;
    
    /**
     * 描述
     */
    private String desc;

    /**
     * 
     */
    public ServiceConfig()
    {
    }
    
    public ServiceConfig(Element e)
    {
        autoStart = "true".equals(e.attributeValue("autostart"));
        String md = e.attributeValue("mode");
        if ("local".equals(md))
        {
            mode = MODE_LOCAL;
        }
        else if ("remote".equals(md))
        {
            mode = MODE_REMOTE;
        }
        else
        {
            mode = MODE_BOTH;
        }
        
        name = e.elementText("name");
        cls = e.elementText("class");
        intf = e.elementText("interface");
    }

    /**
     * @return the autoStart
     */
    public boolean isAutoStart()
    {
        return autoStart;
    }

    /**
     * @param autoStart the autoStart to set
     */
    public void setAutoStart(boolean autoStart)
    {
        this.autoStart = autoStart;
    }

    /**
     * @return the mode
     */
    public int getMode()
    {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(int mode)
    {
        this.mode = mode;
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
     * @return the intf
     */
    public String getIntf()
    {
        return intf;
    }

    /**
     * @param intf the intf to set
     */
    public void setIntf(String intf)
    {
        this.intf = intf;
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

}

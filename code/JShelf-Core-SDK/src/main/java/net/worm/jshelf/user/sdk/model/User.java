/**
 * file  :User.java
 * auther:worm
 * date  :2013-4-3
 */
package net.worm.jshelf.user.sdk.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author worm
 *
 */
public class User implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5475492820930021643L;
    
    private String uuid;
    
    private String name;
    
    private int privilege;
    
    private String description;
    
    /**
     * 
     */
    public User()
    {
        uuid = UUID.randomUUID() + "";
    }

    /**
     * @return the uuid
     */
    public String getUuid()
    {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
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
     * @return the privilege
     */
    public int getPrivilege()
    {
        return privilege;
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(int privilege)
    {
        this.privilege = privilege;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

}

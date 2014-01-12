/**
 * file  :BaseUserMgr.java
 * auther:worm
 * date  :2013-4-4
 */
package net.worm.jshelf.user.service.impl;

import java.util.List;
import java.util.Map;

import net.worm.jshelf.base.log.AppLogger;
import net.worm.jshelf.base.service.BaseService;
import net.worm.jshelf.user.sdk.model.User;
import net.worm.jshelf.user.sdk.service.IUserManager;

/**
 * @author worm
 *
 */
public class BaseUserMgr extends BaseService implements IUserManager
{
    private static final AppLogger log = AppLogger.getLogger(BaseUserMgr.class);

    /**
     * 
     */
    private static final long serialVersionUID = 4875554685758956891L;
    
    @Override
    public String getName()
    {
        return "BaseUserMgr";
    }

    @Override
    public long createUser(User user)
    {
        log.debug(user);
        return 0;
    }

    @Override
    public long deleteUser(String uuid)
    {
        log.debug(uuid);
        return 0;
    }

    @Override
    public List<User> queryUser(Map<String, Object> queryParam)
    {
        log.debug(queryParam);
        return null;
    }

    @Override
    public long modifyUser(User user)
    {
        log.debug(user);
        return 0;
    }

}

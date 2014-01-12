/**
 * file  :Caller.java
 * auther:worm
 * date  :2013-2-18
 */


import java.rmi.Naming;
import java.rmi.Remote;

import net.worm.jshelf.base.sdk.service.IAppManager;
import net.worm.jshelf.user.sdk.service.IUserManager;

/**
 * @author worm
 *
 */
public class Caller
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        try
        {
            IUserManager rmi =  (IUserManager) Naming.lookup("rmi://localhost:1099/" + "BaseUserApp/BaseUserMgr");
            System.out.println("sfsd");
            rmi.deleteUser(")createUser(null");
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

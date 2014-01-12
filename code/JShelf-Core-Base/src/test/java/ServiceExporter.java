/**
 * file  :ServiceExporter.java
 * auther:worm
 * date  :2013-1-22
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author worm
 *
 */
public class ServiceExporter
{
    public static void main(String[] args)
    {
        Configuration conf = new Configuration();
        conf.configure();
        
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        session.close();
//        try
//        {
//            
//            LocateRegistry.createRegistry(1099);
//            Naming.bind("rmi://localhost:1099/UserService", new RmiServiceProxyImpl()); 
//        } catch (Exception e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.print("Service start");  
//        try
//        {
//            Thread.sleep(10000000);
//        } catch (InterruptedException e)
//        {
//            // TODO Auto-generated catch block
//        }
    }
    
    public void init()
    {
        Properties prop = new Properties();
        try
        {
            prop.load(new FileInputStream(new File("")));
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
        }
    }
}

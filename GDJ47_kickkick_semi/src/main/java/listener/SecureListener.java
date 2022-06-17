package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import common.AESEncrypt;

/**
 * Application Lifecycle Listener implementation class SecureListener
 *
 */
@WebListener
public class SecureListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SecureListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        new AESEncrypt();//서버가 켜지고 context생성시 secretkey초기화
    }
	
}

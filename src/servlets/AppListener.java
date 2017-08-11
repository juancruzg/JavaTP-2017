package servlets;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener, ServletContextAttributeListener {
    public AppListener() {
    }

    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    }

    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Soy un evento que se lanza al finalizar el server");
    }

    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("Soy un evento que se lanza al iniciar el server");
    }
	
}

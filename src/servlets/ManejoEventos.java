package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;

import entidades.Usuario;

@WebListener
public class ManejoEventos implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Soy un evento que se lanza al finalizar el server");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("Soy un evento que se lanza al iniciar el server");
    }
    
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	// Acá puedo manejar eventos disparados al loguear
    	// Este tal vez sería un buen lugar para manejar permisos y validaciones de permisos
    	if (se.getName().equals("usuario")) {
    		System.out.println("Hola " + ((Usuario)se.getValue()).getNombre());
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	// Y acá al desloguear
    	if (se.getName().equals("usuario")) {
    		System.out.println("Chau " + ((Usuario)se.getValue()).getNombre());
    	}
    }
}

package servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entidades.Usuario;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionBindingListener {
    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    }

    public void valueBound(HttpSessionBindingEvent event)  { 
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	// Acá puedo manejar eventos disparados al loggear
    	// Este tal vez sería un buen lugar para manejar permisos y validaciones de permisos
    	if (se.getName().equals("usuario")) {
    		System.out.println("Hola " + ((Usuario)se.getValue()).getNombre());
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	// Y acá al desloggear
    	if (se.getName().equals("usuario")) {
    		System.out.println("Chau " + ((Usuario)se.getValue()).getNombre());
    	}
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    }

    public void valueUnbound(HttpSessionBindingEvent event)  { 
    }
	
}

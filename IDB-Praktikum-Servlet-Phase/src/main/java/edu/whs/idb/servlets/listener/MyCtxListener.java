
package edu.whs.idb.servlets.listener;

import edu.whs.idb.praktikum.dao.Datenzugriffsobjekt;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Aufgabenblaztt 3 - Arbeitsschritt 2
 * 
 * Im Rahmen des Arbeitsschritts 3 koennen Sie die vorbereitete 
 * Klassenschablone verwenden, um Ihr Datenzugriffsobjekt in den 
 * Application-Scope zu legen.
 * 
 * Was fehlt noch?
 * 
 * 1. Die Klasse muss per Annotation als Context-Listener (Web-Listener) 
 *    bekannt gemacht werden.
 * 2. Ihr Datenzugriffsobjekt muss in den beiden Mehtoden des S
 *    ervletContextListeners verwaltet werden
 *
 */
@WebListener
public class MyCtxListener implements ServletContextListener {

    /**
     * Erstellen einer Instanz des Datenzugriffsobjekts und Ablegen dieses 
     * Objekts in den Application-Scope (Objektablage des Servlet-Contexts)
     * 
     * @param sce 
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(
                    "dao", new Datenzugriffsobjekt("IDB-Praktikum-Web-AppPU"));
        
        // Das Erstellen des Datenzugriffsobjekts dokumentieren
        System.out.println("Datenzugriffsobjekt angelegt");
    }

    /**
     * Das im Applcation-Scope befindliche Datenzugriffsobjekt schließen
     * 
     * @param sce 
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Datenzugriffsobjekt dao = 
            (Datenzugriffsobjekt) sce.getServletContext().getAttribute("dao");
        
        if (dao != null) {
            dao.close();
        }
        
        // Das Schließen des Datenzugriffsobjekts dokumentieren
        System.out.println("Datenzugriffsobjekt geschlossen");
    }
}

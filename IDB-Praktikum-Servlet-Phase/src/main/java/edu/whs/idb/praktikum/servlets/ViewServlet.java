
package edu.whs.idb.praktikum.servlets;

import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Kategorie;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Aufgabenblaetter 4 und 5
 * 
 * Die Klasse ViewServlet wird erst ab dem Aufgabenblatt 4 relevant und kann 
 * im Zuge der Bearbeitung des Aufgabenblatts 3 noch ignoriert werden
 * 
 * Das ViewServlet soll die Rolle der View im Rahmen des MVC-Architekturmusters 
 * uebernehmen. In diesem Sinne wird die Request-Verarbeitung nach Ausfuehrung 
 * der Geschaeftslogik des Controllers an dieses Servlets weitergeleitet.
 * 
 * Die Aufgabe des Anzeige-Servlets ist es, den von der Geschaeftslogik 
 * aufbereiteten Zustand in eine HTML-Antwort zu rendern.
 * 
 */
@WebServlet(name = "ViewServlet", urlPatterns = {"/ViewServlet"})
public class ViewServlet extends HttpServlet {

    /**
     * Aufgabenblatt 4 und 5
     * 
     * Hier soll entlang der Aufgabenblaetter 4 und 5 sukzessive die 
     * Anzeigelogik entstehen.
     * 
     * 
     *   Aufgabenblatt 4: Arbeitsschritt 1:
     * 
     *    - Kopieren Sie den kompletten Source-Code der aus der 
     *      renderResponse-Methode des ControllerServlets hier hinein
     * 
     *   Aufgabenblatt 4: Arbeitsschritt 2:
     * 
     *    - Definieren Sie im Parameter href des Anker-Tags zur Auswahl
     *      der Kategorien einen GET-Request mit dem Kategoriekuerzel als
     *      Parameter
     * 
     *    - Pruefen Sie, ob im Request-Scope ein Attribut mit der 
     *      ausgewaehlten Kategorie liegt:
     * 
     *      1. Ja:
     * 
     *       - Fuegen Sie vor dem angezeigten Namen der ausgwaehlten 
     *         Kategorie ein '>' ein.
     * 
     *       - Stellen Sie die Artikel der ausgewaehlten Kategorie in 
     *         Tabellenform dar.
     * 
     *      2. Nein:
     *       
     *       - Die Kategorieliste ohne Markierung anzeigen
     * 
     *       - Die Artikeltabelle leer oder komplett weg lassen.
     * 
     *   Aufgabenblatt 5: Arbeitsschritt 2:
     *     
     *       - Passen Sie den Code derart an, dass die Markierung der 
     *         ausgewaehlten Kategorien nun auf Basis der Liste erfolgt.
     *
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            List<Kategorie> list = (List<Kategorie>)request.getAttribute("Liste");
            List<Artikel> listart = (List<Artikel>)request.getAttribute("ListeArtikel");
            //String kurzel = (String)request.getAttribute("KategorieKurzel");
            
            HttpSession session = request.getSession();
            Set<String> kurzellist = (Set<String>)session.getAttribute("session");
            
            /**
             * Ab Aufgabenblatt 4 hier die Anzeigelogik platzieren
             */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            
            out.println("<head>");
            out.println("<title>Artikelbrowser</title>");            
            out.println("</head>");
            
            out.println("<style>");
            out.println("html, body {");
            out.println("min-height: 100%;");
            out.println("}");
            
            out.println("table {");
            out.println("margin: 0 auto;");
            out.println("}");
            
            out.println(".div-1 {");
            out.println("background-color: #EBEBEB;");
            out.println("}");
            
            out.println(".div-2 {");
            out.println("float: left;");
            out.println("padding: 5px;");
            out.println("width: 20.0%;");
            out.println("height: 100vh;");
            out.println("box-sizing: border-box;");
            out.println("background-color: grey;");
            out.println("}");
            
            out.println(".div-3 {");
            out.println("float: left;");
            out.println("padding: 5px;");
            out.println("width: 80.0%;");
            out.println("height: 100vh;");
            out.println("box-sizing: border-box;");
            out.println("background-color: lightgrey;");
            out.println("}");            
            out.println("</style>");
            
            out.println("<body>");
            out.println("<div class='div-1'>");
            
            out.println("<div class='div-2'>"); 
            out.println("<h2 style='text-align: left;'>Kategorien</h2>");
//            for(int i = 0; i < list.size() ; i++) {
//                if(list.get(i).getKurzel().equals(kurzel)) {
//                    out.println("<p><a href='ControllerServlet?kurzel=" 
//                        + list.get(i).getKurzel() +"'>" 
//                        + "> " + list.get(i).getName() + "</a></p>");
//                } else {
//                    out.println("<p><a href='ControllerServlet?kurzel=" 
//                        + list.get(i).getKurzel() +"'>" 
//                        + list.get(i).getName() + "</a></p>");
//                }                
//            }
            for(int i = 0; i < list.size() ; i++) {
                if(kurzellist.contains(list.get(i).getKurzel())) {
                    out.println("<p><a href='ControllerServlet?kurzel=" 
                        + list.get(i).getKurzel() +"'>" 
                        + "> " + list.get(i).getName() + "</a></p>");
                } else {
                    out.println("<p><a href='ControllerServlet?kurzel=" 
                        + list.get(i).getKurzel() +"'>" 
                        + list.get(i).getName() + "</a></p>");
                }                
            }
            out.println("</div>");
            
            out.println("<div class='div-3'>");
            out.println("<h1 style='text-align: center; margin-top: 0px;' >Inhalte</h1>");
//            if(kurzel != null) {
//                out.println("<table name='Tabellekategorien' border='1' width='700'>");
//                out.println("<tr>");
//                out.println("<th width='100'>Art-Nr</th>");
//                out.println("<th width='200'>Name</th>");
//                out.println("<th width='300'>Beschreibung</th>");
//                out.println("<th width='100'>Preis (EUR)</th>");
//                out.println("</tr>");            
//                for(int i = 0; i < listart.size() ; i++) {
//                    if(listart.get(i).getKategorie().getKurzel().equals(kurzel)) {
//                        out.println("<tr>");
//                        out.println("<td>" + listart.get(i).getArtNr() + "</td>");
//                        out.println("<td>" + listart.get(i).getName() + "</td>");                    
//                        out.println("<td>" + listart.get(i).getBeschreibung() + "</td>");
//                        out.println("<td>" + listart.get(i).getPreis() + "</td>");
//                        out.println("</tr>");
//                    }                
//                }
//                out.println("</table>");
//            }
            if(!kurzellist.isEmpty()) {
                out.println("<table name='Tabellekategorien' border='1' width='700'>");
                out.println("<tr>");
                out.println("<th width='100'>Art-Nr</th>");
                out.println("<th width='200'>Name</th>");
                out.println("<th width='300'>Beschreibung</th>");
                out.println("<th width='100'>Preis (EUR)</th>");
                out.println("</tr>");            
                for(int i = 0; i < listart.size() ; i++) {
                    if(kurzellist.contains(listart.get(i).getKategorie().getKurzel())) {
                        out.println("<tr>");
                        out.println("<td>" + listart.get(i).getArtNr() + "</td>");
                        out.println("<td>" + listart.get(i).getName() + "</td>");                    
                        out.println("<td>" + listart.get(i).getBeschreibung() + "</td>");
                        out.println("<td>" + listart.get(i).getPreis() + "</td>");
                        out.println("</tr>");
                    }                
                }
                out.println("</table>");
            }          
            out.println("</div>");
            
            out.println("</div>");
            out.println("</body>");
            
            out.println("</html>");            
            
            
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

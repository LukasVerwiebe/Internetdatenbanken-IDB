
package edu.whs.idb.praktikum.dao;

import edu.whs.idb.praktikum.dao.exception.InputException;
import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Bestelldetails;
import edu.whs.idb.praktikum.entities.Bestellung;
import edu.whs.idb.praktikum.entities.Kategorie;
import edu.whs.idb.praktikum.entities.Kunde;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Datenzugriffsobjekt zur Implementierung der JPA-spezifischen Logik.
 * 
 * Die Entitaetsklassen sollen im package edu.whs.idb.praktikum.entities
 * definiert werden.
 */
public class Datenzugriffsobjekt {
    
    /**
     * Hier koennen Sie den Entity-Manager hinterlegen, den das 
     * Datenzugriffsobjekt verwalten soll.
     */
    private EntityManager em;
    private EntityManagerFactory emf;
    
    /**
     * Starten der uebrgebenen Persistence-Unit
     * 
     * @param persistenceUnit Die Persistence-Unit, die gestartet werden soll
     */
    public Datenzugriffsobjekt(String persistenceUnit) {
        /**
         * Entity-Manager auf Basis der uebergebenen Persistence-Unit 
         * instanziieren.
         */
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
        em = emf.createEntityManager();
    }
    
    /** ToDo: Public-Methoden fuer die Datenzugriffslogik auf Basis der 
     *        Jakarta-Persistence-API
     */
    public void neueKategorie(Kategorie kategorie) {
        try {
            em.getTransaction().begin();
            em.persist(kategorie);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void neueArtikel(Artikel artikel, String kurzel) {
        try {            
            // Hinzufügen der Kategorie zum Artikel:
            Kategorie kategorie = em.find(Kategorie.class, kurzel);
            artikel.setKategorie(kategorie);
            // Artikel mit Kategorie anlegen: 
            em.getTransaction().begin();
            em.persist(artikel);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Artikel> findeArtikelName() {
        List<Artikel> list = new ArrayList<Artikel>();
        try {           
            Query query = em.createQuery("SELECT a FROM Artikel a WHERE a.name LIKE 'A%'");
            list = (List<Artikel>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Artikel> findeArtikelPreis() {
        List<Artikel> list = new ArrayList<Artikel>();
        try {           
            Query query = em.createQuery("SELECT a FROM Artikel a WHERE a.preis = (SELECT MAX(a.preis) FROM Artikel a)");
            list = (List<Artikel>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Artikel> findeArtikelKategorie() {
        List<Artikel> list = new ArrayList<Artikel>();
        try {           
            Query query = em.createQuery("SELECT a FROM Kategorie k JOIN k.artikel a");
            list = (List<Artikel>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Kategorie> findeAlleKAtegorien() {
        List<Kategorie> list = new ArrayList<Kategorie>();
        try {           
            Query query = em.createQuery("SELECT k FROM Kategorie k");
            list = (List<Kategorie>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void loescheArtikel() {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Artikel");
            query.executeUpdate();
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void loescheKategorie() {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Kategorie");
            query.executeUpdate();
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void neuerKunde(Kunde kunde) {
        em.getTransaction().begin();
        em.persist(kunde);
        em.getTransaction().commit();        
    }
    
    public void neueBestellung(String email, Long artNr, long id, Date date, int anzahl) {
        try {
            // Bestellung und Bestelldetails anlegen:
            Kunde kunde = em.find(Kunde.class, email);
            Artikel artikel = em.find(Artikel.class, artNr);
            Bestellung bestellung = new Bestellung(id, date, kunde);
            Bestelldetails bestelldetails = new Bestelldetails(artikel, artikel.getPreis() * anzahl, anzahl);
            bestelldetails.setArtNr(artikel.getArtNr());
            bestelldetails.setBestellNr(bestellung.getBestellNr());
            bestellung.getBestelldetails().add(bestelldetails);
            bestelldetails.setBestellung(bestellung);
            // Transaktion:           
            em.getTransaction().begin();
            em.persist(bestellung);
            em.getTransaction().commit();            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Bestellung> sucheBestellungZuArtikel(Long artNr) {
        List<Bestellung> list = new ArrayList<Bestellung>();
        try {
            Query query = em.createQuery("SELECT b FROM Bestellung b JOIN b.bestelldetails bd WHERE bd.artNr = " + artNr);
            list = (List<Bestellung>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Bestellung> sucheBestellungGesamtpreis() {
        List<Bestellung> list = new ArrayList<Bestellung>();
        try {
            Query query = em.createQuery("SELECT b FROM Bestellung b JOIN b.bestelldetails bd WHERE bd.preis = "
                    + "(SELECT MAX(bd.preis) FROM Bestelldetails bd)");
            list = (List<Bestellung>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Bestellung> sucheBestellpositionen() {
        List<Bestellung> list = new ArrayList<Bestellung>();
        try {
            Query query = em.createQuery("SELECT b FROM Bestellung b JOIN b.bestelldetails bd WHERE SUM(bd.bestellNr) = "
                    + "(SELECT MAX(SUM(bd.bestellNr)) FROM Bestelldetails bd)");
            list = (List<Bestellung>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void loescheBestellung(Long bestellNr) {
        try {
            // Bestellung auswählen:
            Bestellung bestellung = em.find(Bestellung.class, bestellNr);
            // Löschvorgang Starten:
            em.getTransaction().begin();
            em.remove(bestellung);
            em.getTransaction().commit();            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Test:
     */
    
    public List<Artikel> jpql1() {
        List<Artikel> list = new ArrayList<Artikel>();
        try {
            Query query = em.createQuery("SELECT a FROM Artikel a WHERE a.beschreibung LIKE 'Ein%' GROUP BY a HAVING COUNT(a.name) > 0 ORDER BY a.name DESC");
            list = (List<Artikel>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void jpql2(Kategorie kategorie) {
        try {
            em.getTransaction().begin();
            em.persist(kategorie);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void jpql3() {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Kategorie WHERE k.name = 'Test'");
            query.executeUpdate();
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void jpql4() {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Kategorie k SET k.name = :name WHERE k.kurzel = :kurzel")
                    .setParameter("name", "Test Neu NEU")
                    .setParameter("kurzel", "TE");
            query.executeUpdate();
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }
        
//        Query query = em.createQuery(
//            "UPDATE Country SET population = population * 11 / 10 " +
//            "WHERE population < :p");
//        int updateCount = query.setParameter(p, 100000).executeUpdate();
    }
    
    public List<Kategorie> jpql5() {
        List<Kategorie> list = new ArrayList<Kategorie>();
        try {
            Query query = em.createQuery("SELECT k FROM Kategorie k WHERE k.kurzel = 'TE'");
            list = (List<Kategorie>)query.getResultList();
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void jpql6() {
        List<Artikel> list = new ArrayList<Artikel>();
        Query query = em.createQuery("SELECT a FROM Artikel a WHERE a.kategorie.kurzel = 'LE'");
        list = (List<Artikel>)query.getResultList();        
        for(int i = 0; i < list.size(); i++) {
            System.out.println("Artikel: "+list.get(i).getArtNr()+" Name:"+list.get(i).getName());
        }        
    }
    
    public void jpql7() {
        List<Object> list = new ArrayList<Object>();
        Query query = em.createQuery("SELECT a.name, a.beschreibung FROM Artikel a WHERE a.kategorie.kurzel = 'LE'");
        list = query.getResultList();  
        
        for(int i = 0; i < list.size(); i++) {
           // String test = (String)list.get(i);
            System.out.println("Attribute Name: " + list.get(i));
        }        
    }    
    
    public void jpql8() {
        List<Artikel> list = new ArrayList<Artikel>();
        Query query = em.createQuery("SELECT a FROM Artikel a WHERE CONCAT(a.name, a.beschreibung) = 'ApfelEin Apfel.'");
        list = (List<Artikel>)query.getResultList();         
        for(int i = 0; i < list.size(); i++) {
            System.out.println("\nCONCAT Name: " + list.get(i).getName());
        }     
        
        List<Artikel> list2 = new ArrayList<Artikel>();
        Query query2 = em.createQuery("SELECT a FROM Artikel a WHERE SUBSTRING(a.name, 1, 1) = 'A'");
        list2 = (List<Artikel>)query2.getResultList();         
        for(int i = 0; i < list2.size(); i++) {
            System.out.println("\nSUBSTRING Name: " + list2.get(i).getName());
        } 
        
        List<Artikel> list3 = new ArrayList<Artikel>();
        Query query3 = em.createQuery("SELECT a FROM Artikel a WHERE LOWER(a.name) = 'apfel'");
        list3 = (List<Artikel>)query3.getResultList();         
        for(int i = 0; i < list3.size(); i++) {
            System.out.println("\nLOWER Name: " + list3.get(i).getName());
        }
        
        List<Artikel> list4 = new ArrayList<Artikel>();
        Query query4 = em.createQuery("SELECT a FROM Artikel a WHERE UPPER(a.name) = 'APFELSAFT'");
        list4 = (List<Artikel>)query4.getResultList();         
        for(int i = 0; i < list4.size(); i++) {
            System.out.println("\nUPPER Name: " + list4.get(i).getName());
        }
        
        List<Artikel> list5 = new ArrayList<Artikel>();
        Query query5 = em.createQuery("SELECT a FROM Artikel a WHERE LENGTH(a.name) = 5");
        list5 = (List<Artikel>)query5.getResultList();         
        for(int i = 0; i < list5.size(); i++) {
            System.out.println("\nLENGTH Name: " + list5.get(i).getName());
        }
    }
    
    public void jpql9() {
        List<Artikel> list5 = new ArrayList<Artikel>();
        Query query5 = em.createQuery("SELECT a.kategorie, COUNT(a) FROM Artikel a GROUP BY a.kategorie");
        list5 = (List<Artikel>)query5.getResultList();         
        for(int i = 0; i < list5.size(); i++) {
            System.out.println("\nCOUNT Name: " + list5.get(i));
        }
        
        List<Artikel> list6 = new ArrayList<Artikel>();
        Query query6 = em.createQuery("SELECT a.kategorie, COUNT(a) FROM Artikel a GROUP BY a.kategorie HAVING COUNT(a) < 10");
        list6 = (List<Artikel>)query6.getResultList();         
        for(int i = 0; i < list6.size(); i++) {
            System.out.println("\nCOUNT2 Name: " + list6.get(i));
        }
    }
    
    public void jpql10() {
        List<Artikel> list5 = new ArrayList<Artikel>();
        Query query5 = em.createQuery("SELECT a FROM Artikel a ORDER BY a.name DESC, a.preis DESC");
        list5 = (List<Artikel>)query5.getResultList();         
        for(int i = 0; i < list5.size(); i++) {
            System.out.println("\nORDER BY Name: " + list5.get(i).getName() + " Preis: " + list5.get(i).getPreis());
        }
    }
    
    public void jpql11() {
        List<Artikel> list5 = new ArrayList<Artikel>();
        Query query5 = em.createQuery("SELECT a FROM Artikel a WHERE (SELECT COUNT(k) FROM Kategorie k WHERE a.kategorie.kurzel = k.kurzel) > 0");
        list5 = (List<Artikel>)query5.getResultList();         
        for(int i = 0; i < list5.size(); i++) {
            System.out.println("\nUnterabfrage Name: " + list5.get(i).getName() + " Preis: " + list5.get(i).getPreis());
        }
    }
    
    public List<Bestellung> jpql12() {
        List<Bestellung> list = new ArrayList<Bestellung>();
        try {
            Query query = em.createQuery("SELECT b FROM Bestellung b JOIN b.bestelldetails bd WHERE bd.preis = "
                    + "(SELECT MAX(bd.preis) FROM Bestelldetails bd)");
            list = (List<Bestellung>)query.getResultList();
            
            for(int i = 0; i < list.size(); i++) {
                System.out.println("\nJOIN Name: " + list.get(i).getBestellNr());
            }
            
        } catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    
    /**
     * Entity-Manager schließen.
     */
    public void close() {
        // ToDo: Schließen des Entity-Managers
        em.close();
        emf.close();
    }
}


package edu.whs.idb.praktikum.jpa;

import edu.whs.idb.praktikum.dao.Datenzugriffsobjekt;
import edu.whs.idb.praktikum.dao.exception.InputException;
import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Bestelldetails;
import edu.whs.idb.praktikum.entities.Bestellung;
import edu.whs.idb.praktikum.entities.Kategorie;
import edu.whs.idb.praktikum.entities.Kunde;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 */
public class Main {

    // Hier statisch das Datenzugriffsobjekt hinterlegen, das in der 
    // main-Methode verwaltet wird, damit es innerhalb der Methoden zur Loesung
    // der einzelnen Artbeitsschritte verwendet werden kann.
    private static Datenzugriffsobjekt dao;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Datenzugriffsobjekt instanziieren und in "dao" hinterlegen
        dao = new Datenzugriffsobjekt("IDB-Praktikum-JPA-PhasePU");

        // Aufgabenblatt 1:
//        legeLeerenKatalogAn();
//        fuellenDesKatalogsMitArtikeln();
//        gezieltesSelektierenVonArtikeln();
//        loeschenDesArtikelkatalogs();

        // Aufgabenblatt 2:
//        anlegenNeuerKunden();
//        bestellungenAufgeben();
//        bestellungenSuchen();
//        loeschenVonBestellungen();

        ausgabeJPQL1();
        //eingabeJPQL1();
        //einagbeJPQL2();
        einagbeJPQL3();
        ausgabeJPQL4();
        ausgabeJPQL5();
        ausgabeJPQL6();
        ausgabeJPQL8();
        ausgabeJPQL9();
        ausgabeJPQL10();
        ausgabeJPQL11();
        ausgabeJPQL12();
        
        // Datenzugriffsobjekt schließen
        dao.close();
    }
    
    public static void ausgabeJPQL1() {
        List<Artikel> result = dao.jpql1();        
        for(int i = 0; i < result.size(); i++) {
            System.out.println("Name: " + result.get(i).getName() + " Beschreibung: " + result.get(i).getBeschreibung());
        }       
    }
    
    public static void eingabeJPQL1() {
        Kategorie k1 = new Kategorie("TE", "Test");
        dao.jpql2(k1);
    }
    
    public static void einagbeJPQL2() {
        dao.jpql3();
    }
    
    public static void einagbeJPQL3() {
        dao.jpql4();
    }
    
    public static void ausgabeJPQL4() {
        List<Kategorie> result = dao.jpql5();        
        for(int i = 0; i < result.size(); i++) {            
            System.out.println("\nName: " + result.get(i).getName() + " Kurzel: " + result.get(i).getKurzel());
        }       
    }

    public static void ausgabeJPQL5() {
        System.out.println("");
        dao.jpql6();
    }
       
    public static void ausgabeJPQL6() {
        System.out.println("");
        dao.jpql7();
    }
    
    public static void ausgabeJPQL8() {
        System.out.println("");
        dao.jpql8();
    }
    
    public static void ausgabeJPQL9() {
        System.out.println("");
        dao.jpql9();
    }
    
    public static void ausgabeJPQL10() {
        System.out.println("");
        dao.jpql10();
    }
    
    public static void ausgabeJPQL11() {
        System.out.println("");
        dao.jpql11();
    }
    
    public static void ausgabeJPQL12() {
        System.out.println("");
        dao.jpql12();
    }
    
    // ---> Beginn: AUFGABENBLATT 1 <---
    
    /**
     * Aufgabenblatt 1
     * 
     * Arbeitsschritt 1 - Anlegen eines leeren Katalogs
     * 
     * Ein leerer Katalog reduziert sich auf seine Kategorien. Artikel sind 
     * noch nicht vorhanden. Um also einen leeren Katalog anzulegen, müssen wir 
     * nur seinen Kategoriebestand definieren. Hier dürfen Sie ruhig kreativ 
     * sein!
     * 
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die 
     * Geschäftslogik liefert, mindestens sechs verschiedene Kategorien in die 
     * Datenbank zu schreiben. Erweitern Sie dabei die Datenzugriffsklasse um 
     * die benötigte Datenzugriffslogik. 
     * 
     * Hinweis: Berücksichtigen Sie dabei die zwingend notwendige 
     *          Transaktionsverwaltung!
     * 
     */
    public static void legeLeerenKatalogAn() {
        List<Kategorie> list = new ArrayList<Kategorie>();
        
        Kategorie k1 = new Kategorie("LE", "Lebensmittel");
        list.add(k1);
        Kategorie k2 = new Kategorie("GE", "Getränk");
        list.add(k2);
        Kategorie k3 = new Kategorie("AL", "Alkohol");
        list.add(k3);
        Kategorie k4 = new Kategorie("HY", "Hygiene");
        list.add(k4);
        Kategorie k5 = new Kategorie("KL", "Kleidung");
        list.add(k5);
        Kategorie k6 = new Kategorie("EL", "Elektronik");
        list.add(k6);
        
        for(int i = 0; i <list.size(); i++) {
            dao.neueKategorie(list.get(i));
        }
    }
    
    /**
     * Aufgabenblatt 1
     * 
     * Arbeitsschritt 2 - Fuellen des Katalogs mit Artikeln
     * 
     * Da der leere Katalog nun erstellt ist, wollen wir ihn mit Artikel füllen. 
     * Dabei sollte jeder Kategorie mindestens zwei Artikel zugeordnet werden. 
     * Um dies tun zu können, muss die betreffende Kategorie zunächst z.B. über 
     * seinen Schlüssel gefunden worden sein. Bei der Artikel-Schaffung können 
     * Sie Ihrer Kreativität wieder freien Lauf lassen.
     * 
     * Schreiben Sie eine weitere Methode in Ihrem ausführbaren Projekt, das 
     * die Geschäftslogik liefert, jeder Kategorie mindestens zwei Artikel 
     * zuzuordnen und in die Datenbank zu schreiben. Erweitern Sie dabei die 
     * Datenzugriffsklasse um die benötigte Datenzugriffslogik. 
     * 
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige 
     *          Transaktionsverwaltung!
     * 
     */
    public static void fuellenDesKatalogsMitArtikeln() {
        List<Artikel> list = new ArrayList<Artikel>();
        String[] kurzel = {"LE", "LE", "GE", "GE", "AL", "AL", "HY", "HY", "KL", "KL", "EL", "EL"};
        
        Artikel a1 = new Artikel(1L, "Apfel", "Ein Apfel.", "apfel.png", 2.99);
        list.add(a1);
        Artikel a2 = new Artikel(2L, "Orange", "Eine Orange.", "orange.png", 3.45);
        list.add(a2);
        Artikel a3 = new Artikel(3L, "Apfelsaft", "Ein Apfelsaft.", "apfelsaft.png", 4.95);
        list.add(a3);
        Artikel a4 = new Artikel(4L, "Orangensaft", "Ein Orangesaft.", "orangensaft.png", 6.34);
        list.add(a4);
        Artikel a5 = new Artikel(5L, "Wein", "Ein Wein.", "wein.png", 7.95);
        list.add(a5);
        Artikel a6 = new Artikel(6L, "Bier", "Ein Bier.", "bier.png", 4.67);
        list.add(a6);
        Artikel a7 = new Artikel(7L, "Zahnpasta", "Eine Tube Zahnpasta.", "zahnpasta.png", 2.90);
        list.add(a7);
        Artikel a8 = new Artikel(8L, "Seife", "Ein Stück Seife.", "seife.png", 1.33);
        list.add(a8);
        Artikel a9 = new Artikel(9L, "Hemd", "Ein Hemd.", "hemd.png", 16.99);
        list.add(a9);
        Artikel a10 = new Artikel(10L, "Shuhe", "Eine Paar Schuhe.", "schuhe.png", 20.99);
        list.add(a10);
        Artikel a11 = new Artikel(11L, "Tastatur", "Eine Tastatur.", "tastatur.png", 29.99);
        list.add(a11);
        Artikel a12 = new Artikel(12L, "Maus", "Eine PC-Maus.", "maus.png", 33.50);
        list.add(a12);
        
        for(int i = 0; i <list.size(); i++) {
            dao.neueArtikel(list.get(i), kurzel[i]);
        }
    }
    
    /**
     * Aufgabenblatt 1
     * 
     * Arbeitsschritt 3 - Gezieltes Selektieren von Artikeln
     * 
     * Nun wollen wir den frisch erstellten Artikelkatalog nutzen, um gezielt 
     * Artikel zu selektieren. Dazu soll im wesentlichen das Konzept der 
     * Jakarta Persistence Query Language (JPQL) zum Einsatz kommen. 
     * 
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die 
     * Geschäftslogik liefert, die nachfolgenden Abfragen auszuführen und die 
     * Ergebnisse in System.out zu schreiben. Erweitern Sie wie gehabt dabei die
     * Datenzugriffsklasse um die benötigte Datenzugriffslogik. 
     * 
     *  1. Finden Sie alle Artikel (artNr, name, preis), die mit „A“ beginnen.
     *  2. Finden Sie den teuersten Artikel (artNr, name, preis).
     *  3. Finden Sie für alle Kategorien (name) die jeweils zugehörigen 
     *     Artikel (artNr, name, preis) und geben Sie das Ergebnis entsprechend 
     *     strukturiert aus.
     * 
     */
    public static void gezieltesSelektierenVonArtikeln() {
        
        System.out.println("Aufgabe 1.3.c.1");
        List<Artikel> resultC1 = dao.findeArtikelName();
        for(int i = 0; i < resultC1.size(); i++) {
            System.out.println("---------------");
            System.out.println(resultC1.get(i).getArtNr());
            System.out.println(resultC1.get(i).getName());
            System.out.println(resultC1.get(i).getPreis());
        }
        
        System.out.println("\n\n");
        System.out.println("Aufgabe 1.3.c.2");
        List<Artikel> resultC2 = dao.findeArtikelPreis();
        for(int i = 0; i < resultC2.size(); i++) {
            System.out.println("---------------");
            System.out.println(resultC2.get(i).getArtNr());
            System.out.println(resultC2.get(i).getName());
            System.out.println(resultC2.get(i).getPreis());
        }
        
        System.out.println("\n\n");
        System.out.println("Aufgabe 1.3.c.3");
        List<Artikel> resultC3 = dao.findeArtikelKategorie();
        for(int i = 0; i < resultC3.size(); i++) {
            System.out.println("---------------");
            System.out.println(resultC3.get(i).getArtNr());
            System.out.println(resultC3.get(i).getName());
            System.out.println(resultC3.get(i).getPreis());
            System.out.println(resultC3.get(i).getKategorie().getName());
        }
    }
    
    /**
     * Aufgabenblatt 1
     * 
     * Arbeitsschritt 4 - Loeschen des Artikelkatalogs
     * 
     * Nun ist es an der Zeit, den Artikelkatalog wieder zu löschen. Der 
     * Katalog lässt sich ja jederzeit über die im Rahmen der Arbeitsschritte 
     * 1 und 2 erarbeiteten Geschäftsmethoden wieder neu anlegen. 
     * Berücksichtigen Sie bei dem Löschvorgang Abhängigkeiten zwischen Artikel 
     * und Kategorien.
     * 
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die 
     * Geschäftslogik liefert, alle Artikel und Kategorien zu löschen. 
     * Erweitern Sie dabei die Datenzugriffsklasse um die benötigte 
     * Datenzugriffslogik. 
     * 
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige 
     *          Transaktionsverwaltung!
     */
    public static void loeschenDesArtikelkatalogs() {
        try {
            dao.loescheArtikel();
            System.out.println("Alle Artikel gelöscht.");
            dao.loescheKategorie();
            System.out.println("Alle Kategorien gelöscht.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    // ---> Ende: AUFGABENBLATT 1 <---
    
    // ---> Beginn: AUFGABENBLATT 2 <---

    /**
     * Aufgabenblatt 2
     * 
     * Arbeitsschritt 1 - Anlegen neuer Kunden
     * 
     * Das Anlegen eines neuen Kunden ist natürlich nichts neues mehr und 
     * erfolgt nach dem selben Prinzip, das bereits für Artikel und Kategorien 
     * angewandt wurde. Neu dazu kommt allerdings, dass wir nun 
     * Validierungsregeln berücksichtigen wollen. Diese sind bereits in der 
     * Entitätsklasse Kunde definiert, müssen aber bei jedem Schreibprozess 
     * geprüft werden. Da die Prüfung selbst implizit vom Entity-Manager 
     * übernommen wird1, besteht Ihre Aufgabe darin, im Rahmen der 
     * Transaktionsverwaltung mögliche Validierungsfehler abzufangen und 
     * richtig zu verarbeiten.
     * 
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die 
     * Geschäftslogik liefert, beliebig viele Kunden mit gültigen und 
     * ungültigen Werten für die Attribute „name“, „vorname“ und „email“ in die 
     * Datenbank zu schreiben. Erweitern Sie dabei die Datenzugriffsklasse um 
     * die benötigte Datenzugriffslogik. Diese muss im Rahmen der bereits 
     * bekannten Transaktionsabwicklung nun zusätzlich prüfen, ob 
     * Validierungsfehler aufgetreten sind. Um der Geschäftslogik 
     * Validierungsfehler mitteilen zu können, möchten wir eine eigens dafür 
     * konzipierte Exception (z.B. InputException, in der Projektvorlage 
     * bereits vordefiniert) einführen, die alle auftretenden 
     * Validierungsfehlermeldungen zusammenfasst und an die aufrufende 
     * Geschäftslogik geworfen wird. Innerhalb der Geschäftslogik muss diese 
     * Exception abfangen und die entsprechenden Fehlermeldungen in 
     * System.out ausgeben werden.
     * 
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige 
     *          Transaktionsverwaltung!

     */
    public static void anlegenNeuerKunden() {
        try {
            List<Kunde> list = new ArrayList<Kunde>();
            //InputException ine = new InputException();
            
            Kunde kunde1 = new Kunde("lukas@test.de", "Verwiebe", "Lukas", "teststrasse 1");
            list.add(kunde1);
            Kunde kunde2 = new Kunde("max@test.de", "Muster", "Max", "teststrasse 2");
            list.add(kunde2);
            Kunde kunde3 = new Kunde("paul@test.de", "Mustermann", "Paul", "teststrasse 3");
            list.add(kunde3);
            Kunde kunde4 = new Kunde("peter@test.de", "Mustermann", "Peter", "teststrasse 4");
            list.add(kunde4);
            Kunde kunde5 = new Kunde("tom@test.de", "Mustermann", "Tom", "teststrasse 5");
            list.add(kunde5);
            Kunde kunde6 = new Kunde("meike@test.de", "Muster", "Meike", "teststrasse 6");
            list.add(kunde6);            
            
            try {
                for(int i = 0; i <list.size(); i++) {
                    dao.neuerKunde(list.get(i));
                }
            } catch(ConstraintViolationException cve) {
                String meldung = "";                
                for(ConstraintViolation cv : cve.getConstraintViolations()) {
                    meldung = "\n --> " + cv.getPropertyPath() + ": " + cv.getMessage();
                }               
                InputException ie = new InputException(meldung);
                throw ie;
            }           
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Aufgabenblatt 2
     * 
     * Arbeitsschritt 2 - Bestellungen aufgeben
     * 
     * Wir haben nun die Voraussetzung geschaffen, endlich die erste Bestellung 
     * aufgeben zu können. Dies klingt trivial, birgt aber eine nicht zu 
     * unterschätzende Komplexität. Denn eine Bestellung im Ganzen verknüpft 
     * mehrere Entitäten über deren Beziehungen miteinander und es muss dringen 
     * die Datenintegrität sichergestellt sein. Diese wird konkret verletzt, 
     * wenn z.B. beim Persistieren einer Bestellung seine Bestelldetails selbst 
     * noch nicht persistent sind. Dies muss also berücksichtigt werden. Eine 
     * geeignete Lösung wäre z.B. die Nutzung von Persist-Kaskaden.
     * 
     * Schreiben Sie eine weitere Methode in Ihrem ausführbaren Projekt, das 
     * die Geschäftslogik liefert, mindestens sechs Bestellungen über beliebige 
     * Artikel abzusetzen. Lassen Sie die Kunden bestellen, die Sie im 
     * vorangegangenen Arbeitsschritt eingepflegt haben. Erweitern Sie wieder 
     * die Datenzugriffsklasse um die benötigte Datenzugriffslogik. 
     * 
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige 
     *          Transaktionsverwaltung!
     */
    public static void bestellungenAufgeben() {
        try {
            Date date = StringToDate("2022-05-23");            
                     
            dao.neueBestellung("lukas@test.de", 1L, 1L, date, 5);
            dao.neueBestellung("max@test.de", 2L, 2L, date, 15);
            dao.neueBestellung("paul@test.de", 3L, 3L, date, 2);
            dao.neueBestellung("peter@test.de", 4L, 4L, date, 25);
            dao.neueBestellung("tom@test.de", 5L, 5L, date, 11);
            dao.neueBestellung("meike@test.de", 6L, 6L, date, 33);            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static Date StringToDate(String s) {
        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.parse(s);
        } catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    /**
     * Aufgabenblatt 2
     * 
     * Arbeitsschritt 3 - Bestellungen suchen
     * 
     * Nun wollen wir die soeben aufgenommenen Bestelldaten als Basis für 
     * Abfragen nutzen, wozu wieder das Konzept der Jakarta Persistence Query 
     * Language (JPQL) zum Einsatz kommen soll. Das komplexere Entitätenmodell 
     * unserer Bestellung mit mehrstufigen Beziehungen eignet sich gut, um 
     * Abfrageformulierungen unter Einbezug von Pfad-Navigation zu üben. 
     * Benutzen Sie Pfad-Navigation soweit es möglich ist.
     * 
     * Hinweis:  Pfad-Navigationen über Kollektionen sind nur über 
     *           JOIN-Verknüpfungen in der FROM-Klausel möglich!
     * 
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die 
     * Geschäftslogik liefert, die nachfolgenden Abfragen auszuführen und die 
     * Ergebnisse in System.out zu schreiben. Erweitern Sie wie gehabt dabei 
     * die Datenzugriffsklasse um die benötigte Datenzugriffslogik. 
     * 
     *   1. Finden Sie alle Bestellungen, über einen konkreten Artikel Ihrer 
     *      Wahl.
     *   2. Finden Sie die Bestellung mit dem höchsten Gesamtpreis.
     *   3. Welche Bestellung oder Bestellungen verfügen über die meisten 
     *      bestellten Artikelpositionen.
     */
    public static void bestellungenSuchen() {
        try {            
            System.out.println("Aufgabe 1: Bestellung zu ArtNr finden");
            List<Bestellung> result1 = dao.sucheBestellungZuArtikel(1L);
            for(int i = 0; i < result1.size(); i++) {
               System.out.println("Bestellung: " + result1.get(i).getBestellNr() +
                    " Bestelldatum: " + result1.get(i).getBestellDatum()); 
            }
            
            System.out.println("\nAufgabe 2: Bestellung mit höchsten Geseamtpreis");
            List<Bestellung> result2 = dao.sucheBestellungGesamtpreis();
            for(int j = 0; j < result2.size(); j++) {
               System.out.println("Bestellung: " + result2.get(j).getBestellNr() +
                    " Bestelldatum: " + result2.get(j).getBestellDatum()); 
            }            
            
//            System.out.println("\nAufgabe 3: Bestellung mit den meisten Bestelldetails");
//            List<Bestellung> result3 = dao.sucheBestellpositionen();
//            for(int k = 0; k < result3.size(); k++) {
//                System.out.println("Bestellung: " + result3.get(k));
//            }
            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Aufgabenblatt 2
     * 
     * Arbeitsschritt 4 - Loeschen von Bestellungen
     * 
     * Zu guter Letzt möchten wir wieder alle Bestellungen aus der Datenbank 
     * löschen. Dabei müssen Sie – wie in Arbeitsschritt 2 auch – die 
     * Datenintegrität berücksichtigen. Eine Bestellung darf demnach erst 
     * gelöscht werden, wenn all seinen Bestelldetails bereits entfernt wurden. 
     * Auch in diesem Fall ist der Einsatz von Lösch-Kaskaden eine geeignete 
     * Lösung.
     * 
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die 
     * Geschäftslogik liefert, alle Bestellungen inklusive der zugehörigen 
     * Bestelldetails zu löschen. Erweitern Sie dabei die Datenzugriffsklasse 
     * um die benötigte Datenzugriffslogik. 
     * 
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige 
     *          Transaktionsverwaltung!
     */
    public static void loeschenVonBestellungen() {
        try {
            dao.loescheBestellung(1L);
            dao.loescheBestellung(2L);
            dao.loescheBestellung(3L);
            dao.loescheBestellung(4L);
            dao.loescheBestellung(5L);
            dao.loescheBestellung(6L);
            System.out.println("Bestellungen wurde entfernt.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    // ---> Ende: AUFGABENBLATT 2 <---
}

# Aufgabenblatt 1

## Aufgabe 1.1 (Einleitung zur Aufgabenstellung für das Praktikum).
Im Laufe des Praktikums sollen Sie die verschiedenen Themen der Vorlesung durch die Entwicklung eines Web-Shops praktisch üben. Im ersten Teil des Praktikums erstellen
Sie die Persistenzschicht der Anwendung.

### Teil 1: Persistenzschicht (Artikelkatalog)

Thema: Erstellung des Entitäten-Schemas für den Artikelkatalog des entstehenden Web-Shops und die objektrelationale Abbildung des Schemas auf eine relationale Datenbank (objectrelational mapping, ORM) sowie eines Datenzugrisobjekts zur Kapselung der ORM-Logik unter Verwendung der Jakarta Persistence API.

Technologien:
- Jakarta Persistence API (JPA) 3.0

Entwicklungsumgebung:
Projektvorlagen (Maven, erstellt mit Netbeans 12.6)
- IDB-Praktikum-JPA-Entities (Persistenzprojekt)

Abhängigkeiten:
- jakarta.jakartaee-api-9.1.0
- IDB-Praktikum-JPA-Phase (Ausführbares Projekt)

Abhängigkeiten:
- derbyclient-10.15.2, derbytools-10.15.2
- org.eclipse.persistence.jpa-3.0.2
- IDB-Praktikum-JPA-Entities

Laufzeitumgebungen:
- JDK 11 (Mindestens als Source-Format)
- Java-DB-Server 10.15.2

Besonderheiten:
Mit JPA-Entitäten werden die ersten Komponenten eingeführt, die über Annotationen (@Annotation) mit Metadaten versehen werden können, die von einem Manager (Entity-
Manager) interpretiert und verarbeitet werden. Weiterhin soll das Entwurfsmuster eines Datenzugrisobjekts (Data-Access-Object, DAO) implementiert werden.

## Aufgabenstellung zu Blatt 1
Wir beginnen mit dem Entwurf des Schemas für einen Artikelkatalog. Dieser soll aus nur zwei Entitätstypen bestehen, einem Artikel und einer Kategorie. Die  Entitätenklassen werden jeweils auf eine entsprechende Tabelle in der relationalen Datenbank abgebildet. Die Verbindung zur Datenbank soll über eine logische Schicht, der Datenzugrisschicht erfolgen. Diese soll unter Anwendung eines einfachen Entwurfsmusters (Data-Access-Object) aus nur einer einzigen Klasse bestehen, die die gesamte datenbankspezische Logik kapselt und die sich darin verborgene Funktionalität im Sinne eines Services über eine entsprechende Schnittstelle der Geschäftslogik verfügbar macht (siehe Abbilgung 1).

Dies alles soll unter Anwendung des Konzepts der Jakarta Persistence API (JPA) realisiert werden. Die Verwendung des Entity-Managers erfolgt demnach ausschlieÿlich innerhalb des Datenzugriffsobjekts; die Geschäftslogik nutzt ausschlieÿlich dessen Schnittstelle. Diese Geschäftslogik soll innerhalb einer startfähigen Hauptklasse programmiert werden, um den erzeugten Datenzugriffs-Code sowie die Entitätsklassen zu testen und den Artikelkatalog
mit Inhalten zu füllen.

Abgrenzung und Vorgehen:
Im Rahmen der Entwicklungsarbeit verbleiben wir noch innerhalb des vertrauten Netbeans-Umfelds einer Java-Standalone-Applikation, die sie im Rahmen von EPR und OPR her kennen gelernt haben; eineWeb-Applikation ist noch nicht Bestandteil der Aufgabe. Da sowohl die Entitätsklassen als auch die Datenzugrislogik im weiteren Verlauf weiterentwickelt und wiederverwendet werden sollen, ist es aber sinnvoll, die Entwicklung dieser Klassen in ein Java-Bibliotheksprojekt1 auszugliedern und von dem nur im Rahmen der ersten beiden Aufgabenblätter zum Einsatz kommenden ausführbaren Hauptklasse zu trennen.

![image](https://user-images.githubusercontent.com/63674539/192640178-2b4d0609-b195-472a-851b-70cc278f68e9.png)

So lassen sich die Persistenzklassen in späteren Phasen leicht in andere Projekte einbinden.

Der Lösung dieser Aufgabe sollen demnach zwei Netbeans-Projekte zugrunde liegen, ein Projekt, das im Sinne einer Bibliothek die Entitätsklassen und das Datenzugrisobjekt der Persistenzschicht enthält (im weiteren Verlauf Persistenzprojekt genannt) und ein zweites Projekt für die Ausführung einer temporären Geschäftslogik (im weiteren Verlauf ausführbares Projekt genannt). Das Persistenzprojekt muss dabei im ausführbaren Projekt als Abhängigkeit deniert werden.

Für beide Projekte stehen fertige Vorlagen als Maven-Projekte2 unter Moodle zur Verfügung (IDB-Praktikum-JPA-Entities (Persistenzprojekt), IDB-Praktikum-JPAPhase (Aus-
führbares Projekt)), in denen bereits alle Abhängigkeiten zu den benötigten Bibliotheken deniert sind und die Sie verwenden können. Die Nutzung dieser Projektvorlagen bietet Ihnen mehrere Vorteile:

1. Alle Abhängigkeiten zu den benötigten Bibliotheken sind bereits deniert und werden automatisch aufgelöst.
2. Die Projektvorlagen liefern an die Aufgabenstellungen angepasste Source-Code-Skelette, in die Sie im einfachsten Fall Ihre Lösungen nur noch einbetten müssen.
3. Die benötigte Persistence-Unit ist bereits vordeniert und muss nur noch entsprechend Ihres Codes und Ihre Datenbank angepasst werden.

Hinweis: Bei der Verwendung der Projektvorlagen ist es ggf. nötig, ein gültiges JDK anzugeben. Die Projekte setzten das JDK 11 voraus, Sie können aber auch eine höher Version verwenden.

Als Datenbanksystem soll das Server-Framework von JavaDB in der Version 10.15.2 zum Einsatz kommen. JavaDB ist in Netbeans bestens integriert. Eine Server-Instanz lässt sich über ein Handle unter dem Netbeans-Reiter "Services" steuern und verwalten. Es reicht, eine leere Datenbank anzulegen; die Erzeugung des Schemas kann automatisch auf Basis der Entitätsklassen erfolgen.

Die Umsetzung des Datenzugriffsobjekts soll sich an das Entwurfsmuster "Data-Access-Object" orientieren, kann aber maximal vereinfacht werden. In diesem Sinne lässt sich das Entwurfsmuster durch eine einzige Klasse realisieren, die keine explizite Schnittstelle implementiert. Die Schnittstelle des Datenzugriffsobjekts ergibt sich implizit über die Summe der mit "public" deklarierten Methoden. In der entsprechenden Projektvorlage liegt bereits die leere Klasse "Datenzugriffsobjekt" vor.

Die Aufgabenstellung ist so konzipiert, dass zunächst ein denierter Startzustand hergestellt wird, der eine lauffähige Gesamtapplikation in der geforderten Applikationsstruktur und einem gültigen Entitäten-Schema liefert. Ab hier soll nun die geforderte Geschäftslogik nach und nach entlang weiterer Arbeitsschritte implementiert werden.

## Aufgabe 1.2 (Arbeitsschritte zur Einrichtung der Entwicklungsumgebung).
(a) Herstellen des Startzustands Richten Sie zunächst die beiden Projektvorlagen ein, prüfen, ob alle Abhängigkeiten aufgelöst werden können, und stellen sicher, dass den Projekten zum Kompilieren mindestens das JDK 11 korrekt konguriert ist (Projekt-Properties → Build→ Compile).

Legen Sie eine leere JavaDB-Datenbank an. Unter "Other Sources" können Sie in der Projektvorlage des ausführbaren Projekts die vordenierte Persistence-Unit "IDB-Praktikum-JPA-PhasePU" in der Datei persistence.xml nden. Passen Sie diese Persistence-Unit an Ihre neu angelegte Datenbank an, indem Sie die value-Einträge für "url", "user" und "password" entsprechend setzen.

(b) Persistenzprojekt Erstellen Sie die Entitätsklassen "Artikel" und "Kategorie" wie im folgenden Diagramm skizziert in im vordeffnierten Package "edu.whs.idb.praktikum.entities" der Projektvorlage (siehe Abbildung 2).

![image](https://user-images.githubusercontent.com/63674539/192640862-7918422e-792e-42db-8f34-e19c175f04dc.png)

Die Assoziation zwischen "Artikel" und "Kategorie" entspricht einer unidirektionalen Beziehung (JPA).

Eine Entität Artikel repräsentiert eine Ware aus dem Katalog eines Online-Shops. Die Attribute name, beschreibung und preis sind selbsterklärend, die verbleibenden
sind wie folgt zu interpretieren:

- artNr: Eine eindeutige Artikelnummmer, die den Artikel identiffziert.
- bild : Der Dateipfad zu einer Bilddatei, die den Artikel illustriert. Bilder sind erst für spätere Aufgabenblätter relevant und können bis dahin ungenutzt bleiben.

Stellen Sie zudem sicher, dass die beiden Entitätsklassen Bestandteil der Persistence-Unit sind.

Bearbeiten Sie nun die Datenzugriffsklasse "Datenzugriffsobjekt", die bereits leer im Package "edu.whs.idb.praktikum.dao" vordefiniert ist und deren Objekte den Zugriff zur Datenbank schaffen sollen. Ein Datenzugriffsobjekt soll bei seiner Instanziierung einen Entity-Manager erzeugen und verwalten, der für die Datenzugriffslogik verantwortlich ist. Eine "close"-Methode soll den Entity-Manager und somit auch das gesamte Datenzugriffsobjekt schlieÿen können. Der in der Projektvorlage vordefinierte Konstruktor ermöglicht die namentliche Übergabe einer zu startenden Persistence-Unit. So entkoppeln wir die Datenzugriffslogik von der konkreten Persistence-Unit und wir erhalten uns die Möglichkeit, die Persistences-Unit austauschen zu können, ohne den Source-Code des Datenzugriffsobjekts anpassen zu müssen.

Objekte der Datenzugriffsklasse sollen nun in der Lage sein, Datenbankverbindungen zu öffnen und zu schlieÿen, die noch fehlenden Zugriffsmethoden ergeben sich aus der
Bearbeitung der nachfolgenden Arbeitsschritte.

(c) Ausführbares Projekt In der Projektvorlage finden Sie im Package "edu.whs.idb.praktikum.jpa" die Klasse "Main". Hier sind bereits ein Klassenattribut zur Hinterlegung des Datenzugriffsobjekt, eine leere main-Methode und für jeden Aufgabenschritt je eine leere Klassenmethode deffiniert. Sinn dieser Klasse soll es sein, im Laufe der weiteren Arbeitsschritte unter Verwendung des Datenzugriffsobjekts einen Artikelbestand anzulegen und im Anschluss darauf Abfragen absetzen zu können. Der Einfachheit halber soll die Dateneingabe und -abfrage nicht durch eine interaktive Benutzerschnittstelle erfolgen. Implementieren Sie die main-Methode initial wie folgt:
- Instanziieren Sie Ihr Datenzugrisobjekt mit der Persistence-Unit "IDB-Praktikum-JPA-PhasePU" und speichern Sie eine Referenz dazu im Klassenattribut.
- Schließen Sie Ihr Datenzugriffsobjekt wieder durch Ausführung der close-Methode.

Versuchen Sie nun, das ausführbare Projekt zu starten. Wenn es sich fehlerfrei starten lässt und sich in der Datenbank die erwarteten Tabelle benden, können wir mir der Bearbeitung der Aufgabenstellung fortfahren.

## Aufgabe 1.3 (Der Artikelkatalog).
Von nun an können wir den Fokus ganz auf die Anwendung der Jakarta Persistence API richten. Bei den nachfolgenden Arbeitsschritten geht es darum, Artikel in den Katalog aufzunehmen und wiederzufinden.

Dies soll auf Seiten der Main-Klasse des ausführbaren Projekts über eigens dafür deklarierte statische Methoden erfolgen, die bei Bedarf passend in die main-Methode eingebunden werden; jeder Arbeitsschritt soll in eine neue Methodenimplementierung führen. Auf Seiten der Datenzugriffsklasse soll so nach und nach die  Datenzugriffsschnittstelle entstehen, so dass sie der Geschäftslogik bestmöglich zuarbeitet.

(a) Anlegen eines leeren Katalogs Ein leerer Katalog reduziert sich auf seine Kategorien. Artikel sind noch nicht vorhanden. Um also einen leeren Katalog anzulegen, müssen wir nur seinen Kategoriebestand definieren. Hier dürfen Sie ruhig kreativ sein! Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik
liefert, mindestens sechs verschiedene Kategorien in die Datenbank zu schreiben. Erweitern Sie dabei die Datenzugriffsklasse um die benötigte Datenzugriffslogik.

Hinweis: Berücksichtigen Sie dabei die zwingend notwendige Transaktionsverwaltung!

(b) Füllen des Katalogs mit Artikeln Da der leere Katalog nun erstellt ist, wollen wir ihn mit Artikel füllen. Dabei sollte jeder Kategorie mindestens zwei Artikel zugeordnet werden. Um dies tun zu können, muss die betreffende Kategorie zunächst z.B. über seinen Schlüssel gefunden worden sein. Bei der Artikel-Schaffung können Sie Ihrer Kreativität wieder freien Lauf lassen.

Schreiben Sie eine weitere Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, jeder Kategorie mindestens zwei Artikel zuzuordnen und in die Datenbank zu schreiben. Erweitern Sie dabei die Datenzugrisklasse um die benötigte Datenzugriffslogik.

Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige Transaktionsverwaltung!

(c) Gezieltes Selektieren von Artikeln Nun wollen wir den frisch erstellten Artikelkatalog nutzen, um gezielt Artikel zu selektieren. Dazu soll im wesentlichen das Konzept der Jakarta Persistence Query Language (JPQL) zum Einsatz kommen.

Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, die nachfolgenden Abfragen auszuführen und die Ergebnisse in System.out zu
schreiben. Erweitern Sie wie gehabt dabei die Datenzugriffsklasse um die benötigte Datenzugriffslogik.

- Finden Sie alle Artikel (artNr, name, preis), die mit "A" beginnen.
- Finden Sie den teuersten Artikel (artNr, name, preis).
- Finden Sie für alle Kategorien (name) die jeweils zugehörigen Artikel (artNr, name, preis) und geben Sie das Ergebnis entsprechend strukturiert aus.

(d) Löschen des Artikelkatalogs Nun ist es an der Zeit, den Artikelkatalog wieder zu löschen. Der Katalog lässt sich ja jederzeit über die im Rahmen der Arbeitsschritte 1 und 2 erarbeiteten Geschäftsmethoden wieder neu anlegen. Berücksichtigen Sie bei dem Löschvorgang Abhängigkeiten zwischen Artikel und Kategorien.

Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, alle Artikel und Kategorien zu löschen. Erweitern Sie dabei die Datenzugriffsklasse um die benötigte Datenzugriffslogik.

Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige Transaktionsverwaltung!

# Aufgabenblatt 3

## Aufgabe 3.1 (Grundlagen von Web-Applikationen Teil 1 (Servlets 1)).
Thema: Einstieg in die Entwicklung von Web-Applikationen. Verwendung von Servlets zur Verarbeitung von Requests.

Technologien:
- Jakarta Persistence API (JPA) 3.0
- Jakarta Bean Validation 3.0
- Jakarta Servlet 5.0

Entwicklungsumgebung:
- Projektvorlagen (Maven, erstellt mit Netbeans 12.6)
- IDB-Praktikum-JPA-Entities (Persistenzprojekt)

Abhängigkeiten:
- jakarta.jakartaee-api-9.1.0
- IDB-Praktikum-Servlet-Phase (Ausführbares Projekt)

Abhängigkeiten:
- jakarta.jakartaee-api-9.1.0
- IDB-Praktikum-JPA-Entities

Laufzeitumgebungen:
- GlassFish 6.2.1. Webprole
- Java-DB-Server 10.15.2

Besonderheiten:
Der Einsatz eines Web-Servers bringt die Einführung einer neuen, deutlich komplexeren Laufzeitumgebung mit sich. Ein Servlet stellt eine weitere, von seiner Laufzeitumgebung gemanagte Komponente dar. Implizite Verwaltung der Gültigkeit von Attributen über Scope-Objekte. Der Zugriff auf die Datenbank erfolgt ab jetzt über ein Datenquelle.

### Aufgabenstellung
Nachdem das Entitäten-Schema der Persistenzschicht im Rahmen der ersten zwei Aufgabenblätter fertiggestellt worden ist, können wir nun die ersten Schritte in die Entwicklung einer Web-Applikation versuchen. In diesem Sinne soll entlang dieses und der beide folgenden Aufgabenblätter ein Web gestützter Artikelbrowser entstehen, der in der Lage sein soll, den Artikelkatalog anzuzeigen. Dieser Artikelbrowser soll ausschlieÿlich durch Servlets betrieben werden, um grundlegende Konzepte der Entwicklung von Java basierten Web-Applikationen kennenzulernen und zu verstehen.

Thema dieses Aufgabenblatts ist es, erste Erfahrungen mit Servlets zu sammeln, und gleichzeitig die ersten Schritte zur Entwicklung des Artikelbrowsers zu machen. Dabei soll die Grundstruktur der Web-Oberfläche entstehen und sukzessive erweitert werden. Das Etappenziel dieser Aufgabe ist es, in einem Navigationsbereich die Liste aller Kategorien anzuzeigen (vgl. Abbildung 1). Die Persistenzschicht kann dabei komplett wiederverwendet werden; sie muss ggf. angepasst werden.

![image](https://user-images.githubusercontent.com/63674539/192653053-5fcbe9df-2ee6-4c3b-a967-53cd24a871ce.png)

### Abgrenzung und Vorgehen:
Im Zuge der Bearbeitung dieses Aufgabenblattes verlassen wir die gewohnte Umgebung einer Java-SE-Applikation zugunsten einer Web-Applikation. Web-Applikationen sind
komplexer strukturiert und stellen höher Anforderungen an die Laufzeitumgebung (Glassfish). Aus diesem Grund stellt Netbeans zur Entwicklung von Web-Applikationen einen entsprechenden Projekttyp, das Web-Application-Projekt, zur Verfügung, mit dem wir von nun an weiterarbeiten möchten.

Das ausführbare Projekt der Aufgabenblätter 1 und 2 wird nicht mehr benötigt, kann aber noch verwendet werden, um z.B. den Artikelkatalog anzulegen. Die Aufgabe des ausführbaren Projekts nimmt von jetzt an ein Web-Application-Projekt (im weiteren Verlauf Servlet-Projekt) ein. Das Persistenzprojekt hingegen soll weiterhin als Bibliothek für das Servlet-Projekt zum Einsatz kommen. Für das Servlet-Projekt steht eine an die Aufgabenblätter 4-6 angepasste Projektvorlage zur Verfügung: IDB-Praktikum-Servlet-Phase.

Die Entwicklung von Web-Applikationen macht als Zielplattform eine passende Web-Server-Laufzeitumgebung erforderlich. In unserem Fall soll GlassFish 6.2.1. Webprofile
zum Einsatz kommen.

Eine Server-Instanz lässt sich innerhalb von Netbeans unter dem Reiter "Service" registrieren. Nur die unter dem Punkt "Server" aufgelisteten Server stehen für Projekte zur Verfügung. Web-Projekte müssen mit einer passenden Laufzeitumgebung verknüpft sein, damit Netbeans bei der Ausführung eines Projekts die Web-Applikation in die gewünschte Server-Umgebung installieren und ausführen kann.

Wie schon im Rahmen der ersten zwei Aufgabenblätter muss unsere Persistenzschicht der zu entwickelnden Web-Applikation, dem Servlet-Projekt, als Abhängigkeit definiert werden.

Im Rahmen der Aufgabenstellung soll ein Servlet entstehen, das imWesentlichen zwei Aufgaben erledigen soll. Zum einen soll es die Geschäftslogik abbilden und Requests im Sinne eines Controllers verarbeiten und zum anderen soll es - einer View gleich - die Antwort in einer HTML-Seite rendern. Es macht Sinn, diese Trennung im Sinne des MVC-Musters von Beginn an zu leben, indem die HTML-Ausgabe in eine eigene Methode ausgelagert wird, sodass nach Abwicklung der Geschäftslogik nur noch die HTML-Ausgabe produziert werden muss. Später soll diese Funktion ein eigenes (View-)Servlet übernehmen. Im Anhang finden Sie ein HTML-Grundgerüst zur Darstellung des Artikelbrowsers wie in Abbildung 1, das Sie verwenden können.

Attribute lassen sich zur Bestimmung ihrer Gültigkeit in verschiedene Scope-Objekte innerhalb einer Web-Applikation ablegen. Diese werden von der Laufzeitumgebung implizit verwaltet. Im Rahmen dieses Aufgabenblattes befassen wir uns mit dem Request-Scope (Request-Objekt) und dem Application-Scope (Web-Context-Objekt). Das Datenzugriffsobjekt soll applikationsweit im Application-Scope hinterlegt sein.

Die Aufgabenstellung ist wieder so konzipiert, dass zunächst ein definierter Startzustand hergestellt wird, der eine lauffähige Gesamtapplikation in der geforderten Struktur einer Web-Applikation liefert. Erst danach soll die geforderte Geschäftslogik nach und nach entlang weiterer Arbeitsschritte implementiert werden.

## Aufgabe 3.2 (Arbeitsschritte zur Einrichtung der Entwicklungsumgebung).

### (a) Herstellen des Startzustands
Bevor wir mit den eigentlichen Arbeitsschritten beginnen, wollen wir wieder einen denierten Startzustand herstellen, der sich über eine lauffähige Web-Applikation ohne weitere Funktion definiert.

### (b) Persistenzprojekt
Das Persistenz-Projekt muss für den Startzustand nicht angepasst werden.

### (c) Servlet Projekt
Richten Sie die Projektvorlage IDB-Praktikum-Servlet-Phase in Netbeans ein und wählen in den Projekteigenschaften (Properties → Run) als Server Ihren Glassfish-Server
(z.B. GlassFish 6.2.1. Webprofile). Ähnlich wie Sie es von den ersten zwei Aufgabenbl ättern her kennen, sind in der Projektvorlage bereits alle nötigen Abhängigkeiten bereits deniert und es gibt ein an alle Arbeits-schritte angepasstes Source-Code-Skelett.

Da die Verwendung von Datenquellen im Umfeld von Web-Applikationen andere Herausforderungen mitbringt, erfolgt die Anbindung an unsere Datenbank nun anders:

- Die Datenbank wird über den Umweg einer Datenquelle angesprochen. Datenquellen (Java: Data-Source) sind vereinfacht betrachtet zwischengeschaltete Verwaltungsinstanzen für Verbindungen zu einer konkreten Datenbank auf Basis eines Verbindungspools.

Eine Data-Source ist bereits über die Annotation "DataSourceDefinition" in der Servletklasse "ControllerServlet" vordefiniert. Sie müssen nur noch die Parameterwerte für "databaseName", "user" und "password" anpassen. 

- Eine darauf angepasste Persistence-Unit finden Sie in der Projektvorlage für das Servlet-Projekt unter "Other Sources". Hier sollten Sie sicherstellen, dass Ihre
Entitätsklassen richtig aufgeführt sind.

Dazu gesellt sich vorkongurierter Deployment-Descriptor ("web.xml" unter "Web Pages → WEB-INF").

Gegenstand dieses Aufgabenblatts ist im Wesentlichen das Servlet "ControllerServlet". Es enthält die Methode "processRequest(. . . )", die automatisch von Netbeans bei der Erstellung eines Servlets generiert wird und für die Verarbeitung aller POST und GET-Requests zuständig ist; die entsprechenden Servlet-Methoden "doGet(...)" und "doPost(. . . )" sind so implementiert, dass direkt nach "processRequest(. . . )" weiter delegiert wird. Wir möchten von vornherein die Request-Verarbeitung von der
HTML-Ausgabe strikt trennen, indem die von Netbeans generierte Methode "processRequest(...)" ausschlieÿlich für die Geschäftslogik verantwortlich sein und eine weitere
Methode "renderResponse(. . . )" nur die HTML-Antwort rendern soll.

![image](https://user-images.githubusercontent.com/63674539/192655189-ad56eb01-7ebb-43c0-8851-1886ab1bcdb6.png)

Die Methode "processRequest(...)" soll im Sinne des MVC-Patterns die Rolle des Controllers und die Methode "renderResponse(...)" die Rolle der View übernehmen.

Im Deployment-Descriptor (web.xml) ist das Servlet als Welcome-File definiert. Dadurch muss die Ausführung nach dem Start des Servlet-Projekts im Webserver nicht durch explizite Eingabe im URL-Feld des Browsers manuell angestoÿen werden.

Starten Sie das Projekt über den Run-Button. Wenn der Startprozess fehlerfrei durchgeführt werden kann, der Browser geönet wird und die Testseite mit dem Hinweis "Wenn Sie diese Zeile lesen können, ist die Web-Applikation startklar" erscheint, können wir mit den nachfolgenden Arbeitsschritten beginnen.

## Aufgabe 3.3 (Der Artikelbrowser).
Nun können wir beginnen, den eigentlichen Artikelbrowser zu programmieren. Dazu soll zunächst die Grundstruktur der Benutzeransicht bestehend aus einem Navigations- und einem Anzeigebereich entstehen. Anschlieÿend soll eine Liste aller Kategorien im Navigationsbereich angezeigt werden.

Die Ergebnisse der einzelnen Arbeitsschritte sollen nun nicht mehr in je eine Methode pro Arbeitsschritt münden, wie Sie es aus den ersten beiden Arbeitsblättern gewohnt waren. Stattdessen soll nun sukzessive die Geschäfts- und Anzeigelogik in den beiden dafür vorgesehenen Methoden des Controller-Servlets entstehen.

### (a) Aufbau des Shop-Grundgerüsts
Als erstes wollen wir die Grundstruktur des Artikelbrowsers herstellen, wie sie in Abbildung 1 illustriert ist, allerdings noch ohne Anzeige der Kategorien des Artikelkatalogs im Navigationsbereich. Sie können zu diesem Zweck die Vorlage aus dem Anhang verwenden.

Erweitern Sie in Ihrem Servlet-Projekt die View-Logik der "renderResponse(...)"-Methode so, dass der Output-Stream ein HTML-Dokument liefert, das der Grundstruktur des
Artikelbrowsers entspricht.

### (b) Anbindung der Persistenzschicht
Nachdem das Grundgerüst des Artikelbrowsers steht, möchten wir nun die Grundlage schaffen, Inhalte aus dem Artikelkatalog dynamisch anzeigen zu können. Dazu muss zunächst die Persistenzschicht eingerichtet werden, die uns den Zugriff auf die Datenbank ermöglicht. Da das dafür zuständige Datenzugriffsobjekt applikationsweit gültig sein soll und der Lebenszyklus unserer Web-Applikation ausschlieÿlich von der Laufzeitumgebung bestimmt wird, soll das Datenzugriffsobjekt in den Application-Scope der Web-Applikation abgelegt werden.

Schreiben Sie im Servlet-Projekt einen Context-Listener der beim Start der Web-Applikation das Datenzugriffsobjekt als Attribut in die Objektablage desWeb-Context-
Objekt ablegt und beim Beenden der Applikation das Datenzugriffsobjekt schlieÿt.

Sie können in der Projektvorlage die bereits vordefinierte Klasse "MyCtxListener" im Package "edu.whs.idb.servlets.listener" als Grundlage verwenden.

Hinweis: Verwenden Sie bei der Instanziierung des Datenzugriffsobjekts nun die neue Persistence-Unit "IDB-Praktikum-Web-AppPU".

### (c) Kategorien auflisten
Die angebundene Persistenzschicht ermöglicht uns nun, im Navigationsbereich die Kategorien des Artikelkatalogs vertikal auisten zu lassen (vgl. Abbildung 1). Bei der
Umsetzung wollen wir die Aufbereitung der Kategorien scharf von ihrer Darstellung trennen, indem die ermittelten Kategorieentitäten in einer geeigneten Datenstruktur
im Request-Scope an die Darstellungslogik zur Anzeige übergeben werden. Nutzen Sie, soweit es geht, bereits bestehende Logik des im Application-Scope abgelegten Datenzugriffsobjekts.

Das Aufinden des Datenzugriffsobjekts aus dem Application-Scopes kann in die Methode "getDao()" ausgelagert werden. Diese ist bereits in der Projektvorlage leer vordefiniert. Hier müssen Sie über den Servlet-Context Ihr Datenzugriffsobjekt aus der Objektablage lesen und zurückgeben. Für jeden Zugriff auf das Datenzugriffsobjekt reicht dann der Aufruf der "getDao()"-Methode.

Erweitern Sie die Geschäftslogik der "processRequest(...)"-Methode des Controller-Servlets in Ihrem Servlet-Projekt um die notwendige Logik zur Ermittlung aller verfügbaren Kategorien und speichern sie diese in eine Liste. Nutzen Sie den Request-Scope um die Liste der Kategorien an die Anzeigelogik weiterzureichen, indem Sie sie in die Objektablage des Request-Objekts als Attribut speichern.

Erweitern Sie die Anzeigelogik Ihrer "renderResponse(...)"-Methode um den nötigen Code zur Anzeige der Kategorieliste aus dem Request-Scope, indem Sie die Liste aus
der Objektablage des request-Objekts lesen. Ein Kategorie soll als Anker-Element <a> aufbereitet werden; eine Zieladresse unter dem Parameter "href" brauchen Sie noch
nicht zu berücksichtigen und kann leer bleiben (<a href="">Kategoriename</a>). Passen Sie Ihre Datenzugriffsklasse im Persistenz-Projekt, wenn nötig, entsprechend an.

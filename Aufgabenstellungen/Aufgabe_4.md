# Aufgabenblatt 4

## Aufgabe 4.1 (Grundlagen von Web-Applikationen Teil 2 (Servlets 2)).
Thema: Einstieg in die Entwicklung vonWeb-Applikationen. Umsetzung anwendungsgerechter Muster unter Verwendung von Servlets.

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
Eine Request-Weiterleitung ermöglicht die strikte Trennung von Geschäfts- und Anzeigelogik über zwei Servlets.

## Aufgabenstellung:
Im Rahmen dieses Aufgabenblattes möchten wir die Grundfunktion des Artikelbrowsers realisieren. In diesem Sinne sollen alle zu einer ausgewählten Kategorie zugehörigen Artikel so angezeigt werden, wie es die Abbildung 1 illustriert.

Neben dieser geschäftlichen Erweiterung des Artikelbrowsers wollen wir durch die Einführung eines View-Servlets die konsequente Trennung der Controller- und View-Logik weiter vorantreiben und das MVC-Architekturmuster nun strikt umsetzen.

![image](https://user-images.githubusercontent.com/63674539/192643519-d771280a-28a8-4005-977d-517ec8c367d8.png)

Abgrenzung und Vorgehen:
Wir verbleiben weiterhin in der Umgebung einer Servlet basierten Web-Applikation unter Verwendung der beiden Projekte aus dem vorherigen Aufgabenblatt (Persistenz- und
Servlet-Projekt).

## Aufgabe 4.2 (Arbeitsschritte zur Einrichtung der Entwicklungsumgebung).

(a) Herstellen des Startzustands Der Startzustand ergibt sich durch das Ergebnis des Aufgabenblatts 3. Das Herstellen einen gesonderten Startzustands ist also nicht nötig und wir können nahtlos an das letzte Aufgabenblatt anschlieÿen.

## Aufgabe 4.3 (Der Artikelbrowser).
Nun können wir den Artikelbrowser um die Ansicht der Artikel erweitern. Weiterhin wollen wir das MVC-Muster nun konsequent anwenden und die Geschäfts- von der Anzeigelogik durch die Hinzunahme eines View-Servlets ganz strikt trennen.

### (a) Trennung der Geschäfts- und Anzeigelogik 
Zunächst wollen wir nun die bisher nur über zwei Methoden innerhalb des einzigen

Servlets voneinander separierte Geschäfts- und Anzeigelogik ganz strikt durch die Einführung eines gesonderten Anzeige-Servlets trennen und so das MVC-Muster konsequenter umsetzen. Das Anzeige-Servlet soll unter Verwendung der Request-Weiterleitung vom Controller über Zustandsänderungen benachrichtigt werden. Die Projektvorlage stellt dafür bereits eine leer implementierte Servletklasse "ViewServlet" im Package "edu.whs.idb.praktikum.servlets" zur Verfügung.

Kopieren Sie den Code der renderResponse-Methode aus dem Controller-Servlet komplett in die "processRequest(...)"-Methode des Anzeige-Servlets. Passen Sie nun das
Controller-Servlet an, indem Sie die "renderResponse(...)"-Methode löschen und in der Geschäftslogik den Aufruf der "renderResponse(...)"-Methode durch eine Weiterleitung (Forwarding) an das Anzeige-Servlet ersetzen. Das Controller-Servlet bleibt das Ziel von Benutzer-Requests und soll also nach Abarbeitung der Geschäftslogik an das für die Anzeige zuständige Anzeige-Servlet weiterleiten, was die Request-Verarbeitung abschließt.

Wenn Sie das Servlet-Projekt nun ausführen, sollte die Darstellung im Browser genauso gestaltet sein wie zum Abschluss des Aufgabenblatts 3.

### (b) Anzeige der Artikel ausgewählter Kategorien
Wir möchten nun endlich die Funktionalität umsetzen, wofür ein Artikelbrowser eigentlich da ist: Artikel anzeigen. In diesem Sinne sollen nach Auswahl einer Kategorie
aus der Kategorieliste im Anzeigebereich alle zugehörigen Artikel in Form einer Tabelle dargestellt werden, wie es die Abbildung 1 illustriert. Die ausgewählte Kategorie soll entsprechend markiert werden.

Der tabellenartige Darstellung der Artikel können Sie das HTML-Gerüst aus dem Anhang zu Grunde legen.

Bevor die Artikel einer ausgewählten Kategorie ermittelt werden kann, müssen wir die HTML-Aufbereitung der Kategorieeinträge um passende GET-Requests im Parameter "href" des Anker-Tags dynamisch erweitern. Das Anklicken einer Kategorie soll einen GET-Request an das Controller-Servlet absetzen und den Kategorie-Schlüssel (KKUERZEL) als Parameter mitliefern. Auf Basis des Kategorieschlüssels kann die Geschäftslogik die zugehörigen Artikel selektieren.

Passen Sie im Anzeige-Servlet des Servlet-Projekts zunächst die Anker-Tags der Kategorieeinträge dahingehend an, dass die angezeigten Kategorien anklickbar werden und einen entsprechender GET-Request auf das ControllerServlet absetzen können. Ein solcher Request soll die jeweilige Kategorie-Identität als Parameter mitliefern und als Ergebnis dem folgende Muster entsprechen:

<a href= "ControllerServlet?katkuerzel=SCH">Schuhe<a>
  
Erweitern Sie das Controller-Servlet um die notwendige Geschäftslogik zur Verarbeitung dieser Requests und der Aufbereitung der zur ausgewählten Kategorie zugehörigen
Artikel im Request-Scope. Übergeben Sie die selektierten Artikel und die ausgewählte Kategorie über die Objektablage des Request-Scopes.
  
Erweitern Sie das Anzeige-Servlet um die Logik zur Anzeige der ausgewählten Kategorie und seine Artikel in Tabellenform. Die Anzeige der ausgewählten Kategorie soll
durch dem Kategorienamen vorgesetztes '>' dargestellt werden.
  
Passen Sie im Persistenzprojekt die Logik Ihrer Datenzugriffsklasse um die benötigte Funktionalität an.

# Aufgabenblatt 5

## Aufgabe 5.1 (Grundlagen von Web-Applikationen Teil 3 (Sessionverwaltung)).
Thema: Verwalten von Benutzer exklusiven Sitzungsinformationen in Web-Applikationen über beliebig viele Einzel-Requests hinweg.

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
Verwaltung von Objekten im Session-Scope.

### Aufgabenstellung:
Im Rahmen dieses Aufgabenblatts möchten wir den Artikelbrowser abschlieÿend fertigstellen. Dazu möchten wir ihn um die Möglichkeit erweitern, Artikel nicht nur für eine Kategorie anzeigen zu lassen, sondern für eine Auswahl mehrerer. In diesem Sinne soll es nun möglich sein, eine Kategorieauswahl zu schaen, indem nacheinander eine Kategorie aus aber auch wieder abgewählt werden kann. Nach jeder Aus- oder Abwahl sollen die Artikel aller ausgewählten Kategorien gesammelt in einer Tabelle angezeigt werden; gleichzeitig soll sichtbar werden, welche Kategorien aktuell ausgewählt sind. Das Aus- und Abwählen einer Kategorie soll wie gehabt durch einfaches Anklicken der gewünschten Kategorie erfolgen. Das in Abbildung 1 skizzierte Szenario liefert alle Artikel der Kategorien "Hosen" und "Socken"; werden z.B. die "Socken" wieder abgewählt, so bleiben nur Hosen übrig. Die Kategorieauswahl soll für die Dauer einer Benutzersitzung (Session) verwaltet werden können.

![image](https://user-images.githubusercontent.com/63674539/192648918-943f5214-ed70-4400-a5f9-cb494ff4c937.png)

### Abgrenzung und Vorgehen:
Die im Zuge des vorherigen Aufgabenblatts etablierte Projektstruktur (Persistenzprojekt und Servlet-Projekt) kann unverändert weiterverwendet werden. Dasselbe gilt auch für die Struktur der bisherigen Web-Applikation hinsichtlich Design und Architektur.

Dieses Aufgabenblatt befasst sich im Kern mit der Art und Weise, wie wir Informationen verwalten, um diese im Sinne einer Sitzung (Session) für einen Client aufzubereiten. Dazu möchten wir einen neuen "Scope", den Session-Scope, einführen, dessen Verwendung sich etwas aufwändiger gestaltet als der einfache Request-Scope.

Da wir nahtlos an das Ergebnis des vorherigen Aufgabenblatts anknüpfen können und das Resultat sich als direkte Grundlage der Arbeitsschritte dieses Aufgabenblatts gut eignet, ist die Herstellung eines gesonderten Startzustands nicht nötig.

## Aufgabe 5.2 (Arbeitsschritte zur Einrichtung der Entwicklungsumgebung).

### (a) Herstellen des Startzustands
Der Startzustand gestaltet sich durch das Ergebnis der Arbeitsschritte des vorherigen Aufgabenblatts.

## Aufgabe 5.3 (Der Artikelbrowser).
Der gegenwärtige Artikelbrowser versetzt uns in die Lage, zu einer einzeln ausgewählten Kategorie deren Artikel anzeigen zu lassen. Dieses Verhalten soll nun erweitert werden. Der Artikelbrowser soll uns zukünftig die Möglichkeit bieten, zu mehreren ausgewählten Kategorien alle Artikel anzeigen zu lassen; dabei soll die bestehende Ergonomie der Kategorieselektion beibehalten und dahingehend angepasst werden, dass die über einen Mausklick markierte Kategorie nun einer Auswahl je nach Situation entweder hinzugefügt oder entfernt wird. Die anzuzeigenden Artikel soll sich so ausschlieÿlich über die getätigte Kategorieauswahl ergeben. Die Selektion einer Kategorie soll wie bisher über einen einzelnen Request erfolgen; für die Auswahl mehrerer Kategorien sind folglich mehrere Requests nötig. Das "Merken" der ausgewählten Kategorien über mehrere Requests hinweg erfordert die Anwendung des Konzepts einer Session-Verwaltung. Damit möchten wir uns im Rahmen der folgenden Arbeitsschritte beschäftigen.

Vorüberlegungen:
Bisher haben wir Geschäftsdaten ausschlieÿlich über den Request-Scope mit Hilfe des Request-Objekts z.B. für das Rendern der View aufbereitet. Da innerhalb des Request-Scopes verwaltete Daten ihre Gültigkeit auch nur für die Dauer einer Request-Verarbeitung beibehalten, ist dieser Ansatz nun nicht mehr ausreichend. Stattdessen müssen wir diese Daten innerhalb des Session-Scopes organisieren. Dafür lässt sich ein von der Laufzeitumgebung verwaltetes Session-Objekt, anwenden, das den Session-Scope repräsentiert. Aufgabe des Entwicklers einer Web-Applikation ist es, die sitzungsrelevanten Daten im Session-Scope aufzubereiten und Beginn und Ende einer Benutzersitzung zu denieren und zu steuern.

Bei der Gestaltung einer Session-Verwaltung sollten Sie folgende Kriterien berücksichtigen:
- Entscheiden Sie, welche Geschäftsdaten für eine Benutzersitzung relevant sind. Nur diese müssen im Session-Objekt verwaltet werden. Bei uns ist es die Menge aktuell
ausgewählter Kategorien.
- Definieren Sie Struktur und Intention einer Session, also die Art und Weise, wie die Informationen über Session-Attribute repräsentiert werden. Diese Struktur muss einmalig für ein Session-Objekt von der Session-Verwaltung zu Beginn einer Sitzung initialisiert und für die Dauer einer laufenden Sitzung mit jedem Request verwaltet
werden. Die weitere Geschäfts- und Anzeigelogik kann diese Struktur dadurch stets voraussetzen.

- Das Session-Objekt kann neu oder nicht neu sein. Das muss am Anfang jeder Request-Verarbeitung vor der eigentlichen Geschäftslogik im Servlet immer geprüft werden.
Nur ein neues Session-Objekt muss initialisiert werden.

### (a) Einrichtung einer Sessionverwaltung
Bevor wir konkrete Sitzungsdaten über den Session-Scope verwalten können, möchten wir zunächst den Grundmechanismus einer Session-Verwaltung erstellen. Dieser soll
den Zugriff auf Attribute im Session-Scope möglich machen und sicherstellen, dass eine neue Session zunächst initialisiert wird. Dies soll die Grundlage bilden, dass die im Session-Scope aufbereiteten Informationen von der Geschäfts- und der Anzeigelogik sicher verwendet werden können.

Ergänzen Sie die Geschäftslogik im Controller-Servlet Ihres Servlet-Projekts um die folgenden Punkte:

(a) Ermitteln Sie das aktuelle Session-Objekt

(b) Prüfen Sie, ob es sich um eine neue Session handelt und Initialisieren Sie ggf. die Session-Struktur:

Die Struktur besteht aus nur einem einzigen Attribut, das in einer Kollektion alle ausgewählten Kategorien zusammenfasst. Da im Zuge seiner Initialisierung per Definition noch keine Kategorie ausgewählt wurde, verbleibt die Kollektion leer.

Sorgen Sie zudem für eine passende Log-Ausgabe (eine einfache Meldung über "System.out" reicht), wenn die Session neu initialisiert wurde, um die Initialisierung sichtbar zu dokumentieren.

Setzen Sie nun das Attribut "session-timeout" im Standard-Deployment-Descriptor (web.xml) temporär auf 1. Dies veranlasst die Laufzeitumgebung, die Lebenszeit einer Session bereits nach einer Minute zu beenden.

Bei der erstmaligen Ausführung des Servlets (also direkt wenn Ihr Web-Browser den Artikelbrowser erstmalig anzeigt) sollte Ihre Log-Ausgabe zur Session-Initialisierung erscheinen, bei weiteren Klicks auf die Kategorien allerdings nicht mehr. Wenn Sie nun mindestens eine Minute warten und wieder eine Kategorie anklicken, sollte die
Log-Ausgabe wieder auftreten.

### (b) Übertragung selektierter Kategorien in den Session-Scope
Da nun der Grundmechanismus der Session-Verwaltung steht, möchten wir nun die bestehende Logik zur Auswahl der Artikel einer Kategorie in den neuen Ansatz übertragen.
Diese erfordert, dass die selektierte Kategorie nicht mehr über den Request-Scope einzeln an die View übertragen wird, sondern als Teil der Kollektion über den Session-Scope. Weiterhin gilt es, die Artikel nun nicht mehr auf Basis, der einen selektierten Kategorie zu bestimmen, sondern über alle in der Kollektion zusammengefassten; die Liste der gefundenen Artikel kann dann wie gehabt über den Request-Scope an die View übergeben werden.

Passen Sie die Geschäftslogik Ihres Controller-Servlets in Ihrem Servlet-Projekt dahingehend an, dass nach Abarbeitung des in Arbeitsschritt 1 entstandenen Grundmechanismus der Session-Verwaltung die über den auslösenden Request selektierte Kategorie nicht mehr als Attribut des Request-Objekts hinterlegt wird; löschen Sie den entsprechenden Code. Diese Kategorie soll nun der im Attribut des Session-Objekts hinterlegten Kollektion hinzugefügt werden.

Da die Sesseion-Verwaltung bereits fertig ist, können wir davon ausgehen, dass das Set der bisher ausgewählten Kategorien in der dafür vorgesehenen lokalen Variable
vorliegt. Nun gilt es, dieses Set auf Basis der nun durch den aktuellen Request angestoßenen Kategorieauswahl anzupassen. Wir betrachten in diesem Arbeitsschritt nur
das Hinzufügen von Kategorien in die Kategorieauswahl, indem die neu ausgewählte Kategorie einfach diesem Set hinzugefügt wird. Ein explizites Hinzufügen des nun erweiterten Sets in den Session-Scope ist nicht erforderlich, da die Identität des dieser Auswahl zugrunde liegenden Set-Objekts (z.B. eine Instanz der Klasse HashSet) sich nicht verändert hat.

Weil wir streng nach dem MVC-Muster vorgehen wollen, müssen wird das Set der ausgewählten Kategorien dem Anzeige-Servlet über den Request-Scope aufbereiten.

Passen Sie das "ViewServlet" entsprechend an und markieren Sie in das Set aller Kategorien die aktuelle ausgewählten (vgl. Abbildung 1). Auch hier gilt, dass die Kategorieauswahl nun über den Session-Scope in der dort hinterlegten Kollektion zu finden ist. Die Logik zur Anzeige der Artikel muss nicht verändert werden.

Passen Sie ggf. im Persistenzprojekt die Logik Ihrer Datenzugriffsklasse um die benötigte Funktionalität an.

### (c) Selektierte Kategorien aus der Auswahl entfernen
Wir haben jetzt die Funktionalität geschaffen, selektierte Kategorien einer Auswahl hinzuzufügen. Was noch fehlt, ist eine bereits ausgewählte Kategorie zu selektieren
und aus der Auswahl zu entfernen.

Erweitern Sie die Geschäftslogik Ihres Controller-Servlets um eine Prüfung, ob die selektierte Kategorie bereits Bestandteil der im Session-Attribut hinterlegten Kollektion ist, und entfernen Sie ggf. diese aus der Kollektion.

Das Anzeige-Servlet muss nicht angepasst werden.

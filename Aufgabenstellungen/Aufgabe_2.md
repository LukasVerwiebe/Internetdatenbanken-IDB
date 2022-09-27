# Aufgabenblatt 2

## Aufgabe 2.1 (Persistenzschicht Teil 2 (Bestellung und Validierung)).

Thema: Erweiterung des Entitäten-Schemas um die für einen Bestellvorgang notwendigen Klassen und Einführung von Validierungsregeln sowie Ergänzung des Datenzugriffsobjekts um die dafür nötige Datenzugriffslogik unter Verwendung der Java Persistence API und Jakarta Bean Validation.

Technologien:
- Jakarta Persistence API (JPA) 3.0
- Jakarta Bean Validation 3.0

Entwicklungsumgebung:
- Projektvorlagen (Maven, erstellt mit Netbeans 12.6)
- IDB-Praktikum-JPA-Entities (Persistenzprojekt)

Abhängigkeiten:
- jakarta.jakartaee-api-9.1.0
- IDB-Praktikum-JPA-Phase (Ausführbares Projekt)

Abhängigkeiten:
- derbyclient-10.15.2, derbytools-10.15.2
- org.eclipse.persistence.jpa-3.0.2
- hibernate-validator-7.0.2, jakarta.el-4.0.2, hibernate-validator-cdi-7.0.2
- IDB-Praktikum-JPA-Entities

Laufzeitumgebungen:
- JDK 11 (Mindestens als Source-Format)
- Java-DB-Server 10.15.2

Besonderheiten:
Zusammengesetzter Schlüssel über Beziehungen, eine bidirektionale Beziehung und deklarative Denition von Validierungsregeln auf Entitätenebene.

### Aufgabenstellung
Im Rahmen des Aufgabenblatts 1 haben wir bereits das Entitäten-Schema des Artikelkatalogs samt zugehöriger Datenzugriffslogik entworfen. Dieses soll nun vervollständigt und mit Validierungsregeln versehen werden.

Das gegenwärtige Entitäten-Schema beschränkt sich auf die Denition eines Artikelkatalogs, es liefert aber noch nicht die Möglichkeit, Bestellungen über eine Artikelauswahl absetzen zu können. Das soll sich nun ändern. Dafür führen wir drei weitere Entitätsklassen ein, einen "Kunden", der Artikel ordern kann, eine "Bestellung", die sich als Bestellkopf verstehen lässt, und "Bestelldetails", die genauere Informationen über einen ausgewählten Artikel liefern. Um mit Bestellentitäten arbeiten zu können, muss das Datenzugriffsobjekt um entsprechende Methoden erweitert werden. Weiterhin wollen wir die Kundendaten mit Validierungsregeln versehen, um z.B. sicherzustellen, dass gültige Email-Formate verwendet werden.

Dies alles soll wie gehabt unter Anwendung des Konzepts der Jakarta Persistence API (JPA) realisiert werden. Dazu kommt nun der Einsatz des Konzepts der Jakarta-Bean-
Validation.

Zu Testzwecken lässt sich wieder die Hauptklasse des ausführbaren Projekts einsetzen. Die Projektvorlage liefert auch für die Arbeitsschritte dieses Aufgabenblatts ein passendes Methodenskelett; Sie können die bestehende Klasse Main also wie aus dem ersten Aufgabenblatt gewohnt ergänzen. Wenn Sie lieber mit dem JUnit-Framework testen möchten, können Sie das natürlich ebenfalls tun.

Abgrenzung und Vorgehen:
Wir verwenden zur Lösung dieser Aufgabe die beiden bereits im Rahmen des Aufgabenblatts 1 eingerichteten Netbeans-Projekte, also das Persistenzprojekt (IDB-Praktikum-
JPA-Entities) und das ausführbare Projekt (IDB-Praktikum-JPA-Phase) und nutzten auch weiterhin die bereits angelegte Datenbank des JavaDB-Servers.

Für den Einsatz der Jakarta-Bean-Validation sind die Abhängigkeiten zu den Bibliotheken "hibernate-validator-7.0.2", "jakarta.el-4.0.2", "hibernate-validator-cdi-7.0.2" im ausführbaren Projekt nötig. Dahinter verbirgt sich eine Implementierung der Jakarta-Spezifikation, was die Nutzung im Umfeld eine Java-SE-Applikation wie der unseren erst ermöglicht. Diese Abhängigkeiten werden nicht im Persistenzprojekt benötigt, da zur Kompilierung nur die Definition der zusätzlichen Annotationen zur Erstellung von Validierungsregeln innerhalb der Entitätslassen bekannt sein müssen und diese bereits über die Abhängigkeit zur Bibliothek "jakarta.jakartaee-api-9.0.1" mit abgedeckt sind.

![image](https://user-images.githubusercontent.com/63674539/192650699-5858f725-f308-4b68-a8b5-7f9af2ef92a4.png)

Einen expliziten Startzustand wie im ersten Aufgabenblatt herzustellen ist nun nicht mehr notwendig, da die nächsten Arbeitsschritte nahtlos an die vergangen anknüpfen. Dennoch sollte die Erweiterung des Entitäten-Schemas und der damit verbundenen Datenbankschema-Generierung als Grundlage der dann folgenden Schritte verstanden werden, die sich vor allem mit der Nutzung dieses Schemas beschäftigen. Es macht also Sinn, diese Arbeitsschritte erst zu behandeln, wenn die Schema-Erweiterung fehlerfrei implementiert ist.

Das Entitäten-Modell soll im weiteren Praktikum die Grundlage für die kommenden Aufgabenblättern bilden.

## Aufgabe 2.2 (Arbeitsschritte zur Einrichtung der Entwicklungsumgebung).

### (a) Erweiterung des Datenbankschemas
Als erstes wollen wir das bestehende Entitäten-Schema um die neuen Klassen erweitern und Validierungsregeln für Kunden denieren. Bevor wir die neuen Entitätsklassen
nutzen, sollte sichergestellt werden, dass die erweiterte Persistence-Unit fehlerfrei gestartet werden kann und die Datenbank das Entitäten-Schema richtig abbildet.

### (b) Persistenzprojekt
Erstellen Sie die Entitätsklassen "Bestellung", "Bestelldetails" und "Kunde" wie im folgenden Diagramm skizziert in einem eigenen Java-Package (siehe Abbildung 2).

![image](https://user-images.githubusercontent.com/63674539/192650914-27b02b3f-135f-415f-a802-8db32e0c4c37.png)

Hinweis: Beachten Sie, dass die Entität "Bestelldetails" über einen zusammengesetzten Schlüssel über die beiden Beziehungen zu "Bestellung" und "Artikel" verfügt und
die Beziehung zwischen Bestellung und Bestelldetails bidirektional ist! Im Kapitel 7.2.5 der freiwilligen Übungen gibt es einen Lösungsvorschlag zu diesem Problem.

Eine Besonderheit stellt das Attribut bestellDatum in der Bestellung dar. Das JPAKonzept erfordert bei Zeit-Typen eine Angabe, mit welcher Genauigkeit diese in der
Datenbank abgebildet werden sollen. Für uns reicht die Genauigkeit eines Date-Typen (TemporalType.DATE).

Definieren Sie für die Entitätsklasse Kunde die folgenden Validierungsregeln:
- email: Type "Email"
- vorname, name: Type "Size", keine Leerstrings

Stellen Sie noch einmal sicher, dass die Table-Generation-Strategy auf der Persistence-Unit auf "create" eingestellt ist und die neuen Entitätsklassen der Persistence-Unit angehören.

Die Datenzugriffsklasse muss nicht angepasst werden.

#### (c) Ausführbares Projekt
Präparieren Sie die Hauptklasse des ausführbaren Projekts so, dass der Artikelkatalog wieder angelegt wird (Arbeitsschritte 1 und 2 des ersten Aufgabenblatts) und versuchen Sie nun das ausführbare Projekt zu starten. Wenn es sich fehlerfrei starten lässt, die Datenbank das erweiterte Entitäten-Schema wie erwartet abbildet und der Artikelkatalog sich darin befindet, können wir mit der Bearbeitung der Aufgabenstellung fortfahren.

## Aufgabe 2.3 (Der Bestellvorgang).
Nun können wir wieder den Fokus auf die Verwendung der Entitätsklassen richten. Bei den nachfolgenden Arbeitsschritten geht es darum, einen Bestellprozess durchzuspielen und über die Nutzung von Validierungsregeln die Eingabe gültiger Kundendaten zu garantieren.

Dies soll auf Seiten der Main-Klasse des ausführbaren Projekts wieder über eigens dafür deklarierte statische Methoden erfolgen. Jeder Arbeitsschritt soll in eine neue Methodenimplementierung münden; die Main-Klasse der Projektvorlage ist wieder passend präpariert und liefert für jeden dieser Aufgabenschritte eine entsprechende, leere Methode. Auf Seiten der Datenzugriffsklasse soll die Datenzugriffsschnittstelle entsprechend erweitert werden, so dass sie der Geschäftslogik bestmöglich zuarbeitet.

### (a) Anlegen neuer Kunden
Das Anlegen eines neuen Kunden ist natürlich nichts neues mehr und erfolgt nach dem selben Prinzip, das bereits für Artikel und Kategorien angewandt wurde. Neu
dazu kommt allerdings, dass wir nun Validierungsregeln berücksichtigen wollen. Diese sind bereits in der Entitätsklasse Kunde deniert, müssen aber bei jedem Schreibprozess geprüft werden. Da die Prüfung selbst implizit vom Entity-Manager übernommen wird, besteht Ihre Aufgabe darin, im Rahmen der Transaktionsverwaltung mögliche Validierungsfehler abzufangen und richtig zu verarbeiten.

Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, beliebig viele Kunden mit gültigen und ungültigen Werten für die Attribute
"name", "vorname" und "email" in die Datenbank zu schreiben. Erweitern Sie dabei die Datenzugriffsklasse um die benötigte Daten-zugriffslogik. Diese muss im Rahmen der
bereits bekannten Transaktionsabwicklung nun zusätzlich prüfen, ob Validierungsfehler aufgetreten sind. Um der Geschäftslogik Validierungsfehler mitteilen zu können,
möchten wir eine eigens dafür konzipierte Exception (z.B. InputException, in der Projektvorlage bereits vordefiniert) einführen, die alle auftretenden Validierungsfehlermeldungen zusammenfasst und an die aufrufende Geschäftslogik geworfen wird. Innerhalb der Geschäftslogik muss diese Exception abfangen und die entsprechenden Fehlermeldungen in System.out ausgeben werden.

Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige Transaktionsverwaltung!

### (b) Bestellungen aufgeben
Wir haben nun die Voraussetzung geschaen, endlich die erste Bestellung aufgeben zu können. Dies klingt trivial, birgt aber eine nicht zu unterschätzende Komplexität.
Denn eine Bestellung im Ganzen verknüpft mehrere Entitäten über deren Beziehungen miteinander und es muss dringen die Datenintegrität sichergestellt sein. Diese wird konkret verletzt, wenn z.B. beim Persistieren einer Bestellung seine Bestelldetails selbst noch nicht persistent sind. Dies muss also berücksichtigt werden. Eine geeignete Lösung wäre z.B. die Nutzung von Persist-Kaskaden.

Schreiben Sie eine weitere Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, mindestens sechs Bestellungen über beliebige Artikel abzusetzen. Lassen Sie die Kunden bestellen, die Sie im vorangegangenen Arbeitsschritt eingepfügt haben. Erweitern Sie wieder die Datenzugriffsklasse um die benötigte  Datenzugriffslogik.

Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige Transaktionsverwaltung!

### (c) Bestellungen suchen
Nun wollen wir die soeben aufgenommenen Bestelldaten als Basis für Abfragen nutzen, wozu wieder das Konzept der Jakarta Persistence Query Language (JPQL) zum
Einsatz kommen soll. Das komplexere Entitätenmodell unserer Bestellung mit mehrstufigen Beziehungen eignet sich gut, um Abfrageformulierungen unter Einbezug von
Pfad-Navigation zu üben. Benutzen Sie Pfad-Navigation soweit es möglich ist. 

Hinweis: Pfad-Navigationen über Kollektionen sind nur über JOIN-Verknüpfungen in der FROM-Klausel möglich!

Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, die nachfolgenden Abfragen auszuführen und die Ergebnisse in System.out zu
schreiben. Erweitern Sie wie gehabt dabei die Datenzugriffsklasse um die benötigte Datenzugriffslogik.

- Finden Sie alle Bestellungen, über einen konkreten Artikel Ihrer Wahl.
- Finden Sie die Bestellung mit dem höchsten Gesamtpreis.
- Welche Bestellung oder Bestellungen verfügen über die meisten bestellten Artikelpositionen.

### (d) Löschen von Bestellungen
Zu guter Letzt möchten wir wieder alle Bestellungen aus der Datenbank löschen. Dabei müssen Sie - wie in Arbeitsschritt 2 auch - die Datenintegrität berücksichtigen. Eine Bestellung darf demnach erst gelöscht werden, wenn all seinen Bestelldetails bereits entfernt wurden. Auch in diesem Fall ist der Einsatz von Lösch-Kaskaden eine geeignete Lösung.

Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die Geschäftslogik liefert, alle Bestellungen inklusive der zugehörigen Bestelldetails zu löschen. Erweitern Sie dabei die Datenzugriffsklasse um die benötigte Datenzugriffslogik.

Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige Transaktionsverwaltung!

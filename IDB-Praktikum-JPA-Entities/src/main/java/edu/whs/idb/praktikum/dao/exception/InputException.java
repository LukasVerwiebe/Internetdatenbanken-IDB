
package edu.whs.idb.praktikum.dao.exception;

/**
 * Ausnahme, die dem Aufrufer des Datenzugriffsobnjekts Eingabefehler 
 * signalisieren soll. 
 * 
 * Das Datenzugriffsobjekt soll diese Ausnahme verwenden, um Aufrufer der 
 * dieser DAO-Schnittstelle ueber Validierungsfehler zu informieren.
 * 
 * Beispiel: 
 *    Innerhalb des Datenzugriffsobjekts wird eine spezifische Ausnahme 
 *    (z.B. ConstraintViolationException) per try-catch gefangen. Diese 
 *    spezifische Ausnahme soll nicht direkt an den Aufrufer weiter gegeben 
 *    sondern als InputException "ubersetzt" werden, um von der JPA zu 
 *    abstrahieren.
 * 
 *       ...
 *       } catch (ConstraintViolationException cve) {
 *          // Verarbeitung der Ausnahme
 *  
 *          // Uebersetzen in diese Ausnahme
 *          // z.B.:
 *          // InputException ie = new InputException("passende Fehlermeldung")
 * 
 *          throw ie;
 *       }
 */
public class InputException extends Exception {
    
    private String meldung;
    
    public InputException(String meldung) {
        this.meldung = meldung;
    }
    
    @Override
    public String getMessage() {        
        return meldung;        
    }
    
    public void setMeldung(String meldung) {
        this.meldung = meldung;
    }
}

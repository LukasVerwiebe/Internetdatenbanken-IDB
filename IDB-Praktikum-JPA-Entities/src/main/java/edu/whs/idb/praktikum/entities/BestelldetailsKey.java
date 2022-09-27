
package edu.whs.idb.praktikum.entities;

import java.io.Serializable;
import java.util.Objects;

public class BestelldetailsKey implements Serializable {
    
    public Long bestellNr;
    public Long artNr;

    public BestelldetailsKey(Long bestellNr, Long artNr) {
        this.bestellNr = bestellNr;
        this.artNr = artNr;
    }

    public BestelldetailsKey() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.bestellNr);
        hash = 59 * hash + Objects.hashCode(this.artNr);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BestelldetailsKey other = (BestelldetailsKey) obj;
        if (!Objects.equals(this.bestellNr, other.bestellNr)) {
            return false;
        }
        return Objects.equals(this.artNr, other.artNr);
    }

    @Override
    public String toString() {
        return "BestelldetailsKey{" + "bestellNr=" + bestellNr + ", artNr=" + artNr + '}';
    }
    
}

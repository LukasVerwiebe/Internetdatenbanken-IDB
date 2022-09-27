
package edu.whs.idb.praktikum.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "bestellung.findall", query = "SELECT b FROM Bestellung b WHERE b.bestellNr = :bestellNr")
})
public class Bestellung implements Serializable {
    
    private static final long serialVersionID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bestellNr;
    
    @Temporal(TemporalType.DATE)
    private Date bestellDatum;
    
    @ManyToOne
    private Kunde kunde;
    
    @OneToMany(mappedBy = "bestellung", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Bestelldetails> bestelldetails = new ArrayList<Bestelldetails>();

    public Bestellung(long bestellNr, Date bestellDatum, Kunde kunde) {
        this.bestellNr = bestellNr;
        this.bestellDatum = bestellDatum;
        this.kunde = kunde;
    }

    public Bestellung() {
    }

    public static long getSerialVersionID() {
        return serialVersionID;
    }

    public long getBestellNr() {
        return bestellNr;
    }

    public Date getBestellDatum() {
        return bestellDatum;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public List<Bestelldetails> getBestelldetails() {
        return bestelldetails;
    }

    public void setBestellNr(long bestellNr) {
        this.bestellNr = bestellNr;
    }

    public void setBestellDatum(Date bestellDatum) {
        this.bestellDatum = bestellDatum;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setBestelldetails(List<Bestelldetails> bestelldetails) {
        this.bestelldetails = bestelldetails;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.bestellNr ^ (this.bestellNr >>> 32));
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
        final Bestellung other = (Bestellung) obj;
        return this.bestellNr == other.bestellNr;
    }

    @Override
    public String toString() {
        return "Bestellung{" + "bestellNr=" + bestellNr + '}';
    }
    
}

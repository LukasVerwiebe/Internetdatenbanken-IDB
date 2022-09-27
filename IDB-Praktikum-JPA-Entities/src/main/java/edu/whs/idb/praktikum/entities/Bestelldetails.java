
package edu.whs.idb.praktikum.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(BestelldetailsKey.class)
public class Bestelldetails implements Serializable {
    
    @Id
    private Long bestellNr;    
    @Id
    private Long artNr;
    
    @ManyToOne
    private Artikel artikel;
    @ManyToOne
    private Bestellung bestellung;
    
    private Double preis;
    private Integer anzahl;

    public Bestelldetails(Artikel artikel, Double preis, Integer anzahl) {
        this.artikel = artikel;
        this.preis = preis;
        this.anzahl = anzahl;
    }

    public Bestelldetails() {
    }

    public Long getBestellNr() {
        return bestellNr;
    }

    public Long getArtNr() {
        return artNr;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public Double getPreis() {
        return preis;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setBestellNr(Long bestellNr) {
        this.bestellNr = bestellNr;
    }

    public void setArtNr(Long artNr) {
        this.artNr = artNr;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Bestelldetails other = (Bestelldetails) obj;
        if (!Objects.equals(this.bestellNr, other.bestellNr)) {
            return false;
        }
        return Objects.equals(this.artNr, other.artNr);
    }

    @Override
    public String toString() {
        return "Bestelldetails{" + "bestellNr=" + bestellNr + ", artNr=" + artNr + '}';
    }    
    
}

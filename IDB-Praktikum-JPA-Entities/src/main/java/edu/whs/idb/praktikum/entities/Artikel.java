
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
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "artikel.findall", query = "SELECT a FROM Artikel a WHERE a.artNr = :artNr")
})
public class Artikel implements Serializable{
    
    private static final long serialVersionID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long artNr;
    
    private String name;
    private String beschreibung;
    private String bild;
    private double preis;
    
    @ManyToOne
    private Kategorie kategorie;
    
    @OneToMany(mappedBy = "artikel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bestelldetails> bestelldetails;

    public Artikel(long artNr, String name, String beschreibung, String bild, double preis) {
        this.artNr = artNr;
        this.name = name;
        this.beschreibung = beschreibung;
        this.bild = bild;
        this.preis = preis;
    }

    public Artikel() {
    }

    public long getArtNr() {
        return artNr;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getBild() {
        return bild;
    }

    public double getPreis() {
        return preis;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public List<Bestelldetails> getBestelldetails() {
        return bestelldetails;
    }

    public void setArtNr(long artNr) {
        this.artNr = artNr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public void setBestelldetails(List<Bestelldetails> bestelldetails) {
        this.bestelldetails = bestelldetails;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.artNr ^ (this.artNr >>> 32));
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
        final Artikel other = (Artikel) obj;
        return this.artNr == other.artNr;
    }

    @Override
    public String toString() {
        return "Artikel{" + "artNr=" + artNr + '}';
    }
    
}

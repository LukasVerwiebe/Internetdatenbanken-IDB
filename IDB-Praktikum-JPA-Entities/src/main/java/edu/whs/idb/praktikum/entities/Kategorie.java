
package edu.whs.idb.praktikum.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = "kategorie.findall", query = "SELECT k FROM Kategorie k WHERE k.kurzel = :kurzel")
})
public class Kategorie implements Serializable {
    
    @Id
    private String kurzel;
    
    private String name;
    
    @OneToMany(mappedBy = "kategorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artikel> artikel;

    public Kategorie(String kurzel, String name) {
        this.kurzel = kurzel;
        this.name = name;
    }

    public Kategorie() {
    }

    public String getKurzel() {
        return kurzel;
    }

    public String getName() {
        return name;
    }

    public List<Artikel> getArtikel() {
        return artikel;
    }

    public void setKurzel(String kurzel) {
        this.kurzel = kurzel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtikel(List<Artikel> artikel) {
        this.artikel = artikel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.kurzel);
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
        final Kategorie other = (Kategorie) obj;
        return Objects.equals(this.kurzel, other.kurzel);
    }

    @Override
    public String toString() {
        return "Kategorie{" + "kurzel=" + kurzel + '}';
    }
    
}

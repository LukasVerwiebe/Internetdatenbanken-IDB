
package edu.whs.idb.praktikum.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = "kunde.findall", query = "SELECT k FROM Kunde k WHERE k.email = :email")
})
public class Kunde implements Serializable {
    
    @Id @Email
    private String email;
    
    @NotNull @Size(min = 1, message = "Bitte Namen angeben.")
    private String name;
    
    @NotNull @Size(min = 1, message = "Bitte Vornamen angeben.")
    private String vorname;
    
    private String adresse;
    
    @OneToMany(mappedBy = "kunde", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bestellung> bestellung;

    public Kunde(String email, String name, String vorname, String adresse) {
        this.email = email;
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
    }

    public Kunde() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Bestellung> getBestellung() {
        return bestellung;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setBestellung(List<Bestellung> bestellung) {
        this.bestellung = bestellung;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.email);
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
        final Kunde other = (Kunde) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return "Kunde{" + "email=" + email + '}';
    }
    
}


package edu.whs.idb.praktikum.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Vorlesung { //implements Serializable {
    @Id
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "vorlesung", 
                cascade = CascadeType.ALL, 
                orphanRemoval = true)
    private List<Student> participants;
    
    public Vorlesung(){
        
    }
    
    public Vorlesung(Long pk, String name) {
        this.id = pk;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setname(String n) {
        this.name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public List<Student> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Student> p) {
        this.participants = p;
    }
}


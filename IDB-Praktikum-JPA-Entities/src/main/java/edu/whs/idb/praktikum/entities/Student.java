
package edu.whs.idb.praktikum.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;


@Entity
@NamedQueries({
    @NamedQuery(name = "student.findall", 
                query = "select s from Student s where s.id = :id")
})
public class Student implements Serializable {
    @Id
    private Long id;
    private String name;
    
    @NotNull @Email
    private String email; 
    
    @ManyToOne
    private Vorlesung vorlesung;
    
    public Student(){
        
    }
    
    public Student(Long pk, Vorlesung v) {
        this.id = pk;
        this.vorlesung = v;
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
    
    public void setEmail(String n) {
        this.email = n;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setVorlesung(Vorlesung n) {
        this.vorlesung = n;
    }
    
    public Vorlesung getVorlesung(){
        return vorlesung;
    }
}


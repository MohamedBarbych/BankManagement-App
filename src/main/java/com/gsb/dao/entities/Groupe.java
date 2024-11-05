package com.gsb.dao.entities;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Groupe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeGroupe;
    private String nomGroupe;

    @ManyToMany(mappedBy = "groupes")
    private Collection<Employe> employe;

    public Groupe(String nomGroupe) {
        super();
        this.nomGroupe = nomGroupe;
    }

    public Groupe() {
        super();
// TODO Auto-generated constructor stub
    }

    public Long getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(Long codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Collection<Employe> getEmploye() {
        return employe;
    }

    public void setEmploye(Collection<Employe> employe) {
        this.employe = employe;
    }
}
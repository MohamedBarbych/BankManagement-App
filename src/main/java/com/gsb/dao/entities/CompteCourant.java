package com.gsb.dao.entities;

import jakarta.persistence.*;
import java.util.Date;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
    public double getDecouvert() {
        return decouvert;
    }
    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
    private double decouvert;
    public CompteCourant(String codeCompte, Date dateCreation, double solde, double decouvert) {
        super(codeCompte, dateCreation, solde);
        this.decouvert = decouvert;
    }
    public CompteCourant() {
        super();// TODO Auto-generated constructor stub
    }
}
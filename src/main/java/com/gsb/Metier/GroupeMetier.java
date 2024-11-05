package com.gsb.Metier;

import com.gsb.dao.entities.Groupe;

import java.util.List;

public interface GroupeMetier {
    public Groupe addGroupe(Groupe g);

    public List<Groupe> listGroupes();

    public void deleteGroupe(Long codeGroupe);
}

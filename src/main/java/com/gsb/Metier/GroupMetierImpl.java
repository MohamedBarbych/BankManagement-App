package com.gsb.Metier;

import com.gsb.dao.entities.Groupe;
import com.gsb.dao.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class GroupMetierImpl implements GroupeMetier {
    @Autowired
    GroupeRepository groupeRepository;

    @Override
    public Groupe addGroupe(Groupe g) {
        return groupeRepository.save(g);
    }

    @Override
    public List<Groupe> listGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public void deleteGroupe(Long codeGroupe) {
        groupeRepository.deleteById(codeGroupe);
    }


}

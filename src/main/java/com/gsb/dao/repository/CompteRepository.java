package com.gsb.dao.repository;
import com.gsb.dao.entities.Client;
import com.gsb.dao.entities.Compte;
import com.gsb.dao.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {
    Compte findByCodeCompte(String codeCmpt);

    List<Compte> findByClient(Client client);

    List<Compte> findByEmploye(Employe employe);
}
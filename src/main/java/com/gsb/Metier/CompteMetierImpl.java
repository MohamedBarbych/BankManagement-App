package com.gsb.Metier;

import com.gsb.dao.entities.Client;
import com.gsb.dao.entities.Compte;
import com.gsb.dao.entities.Employe;
import com.gsb.dao.repository.ClientRepository;
import com.gsb.dao.repository.CompteRepository;
import com.gsb.dao.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompteMetierImpl implements CompteMetier {
//-------- Inhection de dependance  (injection d'une instance de CompteRepository) ------
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeRepository employeRepository;


    //TODO: The update function in controller will declare the saveCompte() also
    @Override
    public Compte addCompte(Compte c, Long codeClient, Long codeEmploye, String codeCompte) {
        Client client = clientRepository.findByCodeClient(codeClient);
        Employe employe = employeRepository.findByCodeEmploye(codeEmploye);
        c.setCodeCompte(codeCompte);
        c.setClient(client);
        c.setEmploye(employe);
        return compteRepository.save(c);
    }

//    @Override
//    public Compte updateCompte(String code_cmpt, Compte c) {
//        Compte compte = compteRepository.findByCodeCompte(code_cmpt);
//        //TODO: Updating the fields of compte
////        return compteRepository.save();
//        return null;
//    }

    @Override
    public void deleteCompte(String code_cmpt) {
        compteRepository.deleteById(code_cmpt);
    }

    @Override
    public Compte getCompte(String code_cmpt) {
        Compte compte = compteRepository.findByCodeCompte(code_cmpt);
        System.out.println("Code Compte, " + compte.getCodeCompte());
        if (compte == null) return null;
        return compte;
    }

    @Override
    public List<Compte> comptesClient(Long code_client) {
        Client client = clientRepository.findByCodeClient(code_client);
        return compteRepository.findByClient(client);
    }

    @Override
    public List<Compte> comptesEmployees(Long code_emp) {
        Employe employe = employeRepository.findByCodeEmploye(code_emp);
        return compteRepository.findByEmploye(employe);
    }


    @Override
    public List<Compte> allComptes() {
        return compteRepository.findAll();
    }

    @Override
    public void verser(String code_cmpt, double montant) {
        Compte compte = compteRepository.findByCodeCompte(code_cmpt);
        compte.setSolde(compte.getSolde() + montant);
    }

    @Override
    public void retrait(String code_cmpt, double montant) {
        Compte compte = compteRepository.findByCodeCompte(code_cmpt);

//        double currentSolde = compte.getSolde();
        compte.setSolde(compte.getSolde() - montant);

//        System.out.println("Votre solde est insufisante!");
    }

    @Override
    public void virement(String code_sendCmpt, String code_receiveCmpt, double montant) {
        Compte compte1 = compteRepository.findByCodeCompte(code_sendCmpt);
        Compte compte2 = compteRepository.findByCodeCompte(code_receiveCmpt);

        if (compte1.getSolde() > 0) {
            verser(code_receiveCmpt, montant);
            retrait(code_sendCmpt, montant);
        }

        System.out.println("Votre solde est insufisante!");

    }


}

package com.gsb.Metier;

import com.gsb.dao.entities.Compte;
import com.gsb.dao.entities.Employe;
import com.gsb.dao.entities.Groupe;
import com.gsb.dao.entities.Operation;
import com.gsb.dao.repository.EmployeRepository;
import com.gsb.dao.repository.GroupeRepository;
import com.gsb.dao.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeMetierImpl implements EmployeMetier {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CompteMetier compteMetier;

    @Override
    public Employe saveEmploye(Employe e) {
    // TODO Auto-generated method stub

        Long supervisorId = e.getEmployeSup().getCodeEmploye();
        Employe supervisor = employeRepository.findById(supervisorId).orElse(null);

        if (supervisor != null) {
            return employeRepository.save(e);
        }

        return null;
    }

    @Override
    public List<Employe> listEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long codeEmploye) {
        employeRepository.deleteById(codeEmploye);
    }


    @Override
    public void addEmployeToGroupe(Long codeGroupe, Long codeEmploye) {
        Employe employe = employeRepository.findByCodeEmploye(codeEmploye);

        Groupe groupe = groupeRepository.findByCodeGroupe(codeGroupe);
        System.out.println("GroupeId, " + groupe.getCodeGroupe());


        employe.getGroupes().add(groupe);
        groupe.getEmploye().add(employe);
        employeRepository.save(employe);
        groupeRepository.save(groupe);
    }


//    @Override
//    public Groupe groupOfEmp(Long codeGroupe, Long codeEmploye) {
//        Groupe groupe = groupeRepository.findByCodeGroupe(codeGroupe);
//
//        return groupeRepository.save(groupe);
//    }

    @Override
    public List<Employe> getEmployeesByGroup(Long codeGroupe) {
        Groupe groupe = groupeRepository.findByCodeGroupe(codeGroupe);

        return (List<Employe>) groupe.getEmploye();
    }

    @Override
    public Operation addOperation(Operation operation, String codeCompte, Long codeEmploye, double montant) {
        Compte compte = compteMetier.getCompte(codeCompte);
        Employe employe = employeRepository.findByCodeEmploye(codeEmploye);
        operation.setCompte(compte);
        operation.setEmploye(employe);
        operation.setMontant(montant);

        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> listOperations(Compte compte) {
        return operationRepository.findByCompte(compte);
    }

    @Override
    public Employe authentifierEmploye(Employe employe1) {
        Employe employe = employeRepository.findEmployeByCodeEmployeAndNomEmploye(employe1.getCodeEmploye(),employe1.getNomEmploye());

        if (employe != null) {
            return employe;
        } else {
            // Affichez un message d'erreur ou gérez l'authentification comme nécessaire
            return null;
        }
    }
}

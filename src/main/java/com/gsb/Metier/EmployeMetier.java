package com.gsb.Metier;

import com.gsb.dao.entities.Compte;
import com.gsb.dao.entities.Employe;
import com.gsb.dao.entities.Operation;

import java.util.List;
public interface EmployeMetier {
    public Employe saveEmploye(Employe e);
    public List<Employe> listEmployes();
    public void deleteEmployee(Long codeEmploye);
//    Groupe groupOfEmp(Long codeGroupe, Long codeEmploye);
//
//    Operation addOperation(Operation operation, String codeCompte, Long codeEmploye);
//
//    List<Operation> listOperations(String codeCompte);
    public void addEmployeToGroupe(Long codeGroupe, Long codeEmploye);
//    public Groupe groupOfEmp(Long codeGroupe, Long codeEmploye);
    public List<Employe> getEmployeesByGroup(Long codeGroupe);
    // Operations
    public Operation addOperation(Operation operation, String codeCompte, Long codeEmploye, double montant);
    public List<Operation> listOperations(Compte compte);

    Employe authentifierEmploye(Employe employe);

}
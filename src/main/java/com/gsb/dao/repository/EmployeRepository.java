package com.gsb.dao.repository;

import com.gsb.dao.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Employe findEmployeByCodeEmployeAndNomEmploye(Long codeEmploye,String nomEmploye);
    Employe findByCodeEmploye(Long codeEmploye);
}
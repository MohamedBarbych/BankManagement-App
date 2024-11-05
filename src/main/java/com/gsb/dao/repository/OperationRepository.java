package com.gsb.dao.repository;
import com.gsb.dao.entities.Compte;
import com.gsb.dao.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByCompte(Compte compte);

}
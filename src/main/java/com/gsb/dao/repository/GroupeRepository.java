package com.gsb.dao.repository;
import com.gsb.dao.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    Groupe findByCodeGroupe(Long codeGroupe);
}
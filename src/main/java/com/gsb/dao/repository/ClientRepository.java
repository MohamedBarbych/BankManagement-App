package com.gsb.dao.repository;
import com.gsb.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCodeClient(Long codeClient);
    Client findByCodeClientAndNomClient(Long codeClient,String nomClient);
}
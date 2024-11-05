package com.gsb.Metier;

import com.gsb.dao.entities.Client;
import com.gsb.dao.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientMetierImpl implements ClientMetier {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client saveClient(Client c) {
// TODO Auto-generated method stub
        return clientRepository.save(c);
    }
    @Override
    public List<Client> listClient() {
// TODO Auto-generated method stub
        return clientRepository.findAll();
    }

    @Override
    public Client consulterClient(Long codeClient) {
        Client client = clientRepository.findByCodeClient(codeClient);
        if (client != null) {
            return client;
        } else {
            // You can throw an exception or handle null cases based on your application's requirements.
            throw new EntityNotFoundException("Client not found for code: " + codeClient);
        }
    }

    @Override
    public Client updateClient(Long code_client) {
        return null;
    }

    @Override
    public void deleteClient(Long code_client) {
        clientRepository.deleteById(code_client);
    }

    public Client authentifierClient( Client client1) {
        // Recherchez le client dans la base de données en utilisant le codeClient et le nomClient
        Client client = clientRepository.findByCodeClientAndNomClient(client1.getCodeClient(),client1.getNomClient());

        if (client != null) {
            System.out.println("client authentifie");
            // Le client a été authentifié avec succès
            return client;
        } else {
            System.out.println("client non authentifiee");
           return null;
        }
    }
}

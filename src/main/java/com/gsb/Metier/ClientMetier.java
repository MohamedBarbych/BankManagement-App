package com.gsb.Metier;

import com.gsb.dao.entities.Client;

import java.util.List;
public interface ClientMetier {
    public Client saveClient(Client c);
    public List<Client> listClient();

    public Client consulterClient(Long code_client);
    public Client updateClient(Long code_client);
    public void deleteClient(Long code_client);
    public Client authentifierClient( Client client);

}